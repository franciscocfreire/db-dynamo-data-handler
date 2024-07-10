package br.com.portoseguro.util.dbdynamodatahandler;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.*;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class DynamoDbRepository {

    private final AmazonDynamoDB amazonDynamoDB;

    public DynamoDbRepository(AmazonDynamoDB amazonDynamoDB) {
        this.amazonDynamoDB = amazonDynamoDB;
    }

    public void save(DataRequest dataRequest) {
        Map<String, AttributeValue> dynamoData = convertToDynamoAttributes(dataRequest.getData());
        PutItemRequest request = new PutItemRequest()
                .withTableName(dataRequest.getTableName())
                .withItem(dynamoData);
        amazonDynamoDB.putItem(request);
    }

    public DataResponse retrieve(String tableName, String key, String keyValue) {
        GetItemRequest request = new GetItemRequest()
                .withTableName(tableName)
                .withKey(Map.of(key, new AttributeValue().withS(keyValue)));
        GetItemResult result = amazonDynamoDB.getItem(request);
        Map<String, Object> data = convertFromDynamoAttributes(result.getItem());
        DataResponse response = new DataResponse();
        response.setRecords(data);
        return response;
    }

    public void deleteItem(DataRequest dataRequest) {
        Map<String, AttributeValue> keyToDelete = new HashMap<>();
        dataRequest.getData().forEach((key, value) -> {
            if (value instanceof String) {
                keyToDelete.put(key, new AttributeValue().withS((String) value));
            } else if (value instanceof Number) {
                keyToDelete.put(key, new AttributeValue().withN(value.toString()));
            } else if (value instanceof Boolean) {
                keyToDelete.put(key, new AttributeValue().withBOOL((Boolean) value));
            }
        });

        if (keyToDelete.size() != 1) {
            throw new IllegalArgumentException("The deleteItem method requires exactly one key.");
        }

        DeleteItemRequest deleteRequest = new DeleteItemRequest()
                .withTableName(dataRequest.getTableName())
                .withKey(keyToDelete);
        amazonDynamoDB.deleteItem(deleteRequest);
    }

    public Map<String, Object> scanTable(String tableName) {
        ScanRequest scanRequest = new ScanRequest()
                .withTableName(tableName);
        ScanResult result = amazonDynamoDB.scan(scanRequest);

        Map<String, Object> records = new HashMap<>();
        result.getItems().forEach(item -> {
            item.forEach((key, value) -> {
                Object objectValue = convertAttributeValue(value);
                records.put(key, objectValue);
            });
        });
        return records;
    }

    private Object convertAttributeValue(AttributeValue value) {
        if (value.getS() != null) {
            return value.getS();
        } else if (value.getN() != null) {
            return Long.valueOf(value.getN());
        } else if (value.getBOOL() != null) {
            return value.getBOOL();
        }
        return null;
    }

    private Map<String, AttributeValue> convertToDynamoAttributes(Map<String, Object> data) {
        Map<String, AttributeValue> dynamoData = new HashMap<>();
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            Object value = entry.getValue();
            AttributeValue attributeValue = new AttributeValue();
            if (value instanceof String) {
                attributeValue.setS((String) value);
            } else if (value instanceof Number) {
                attributeValue.setN(value.toString());
            } else if (value instanceof Boolean) {
                attributeValue.setBOOL((Boolean) value);
            }
            dynamoData.put(entry.getKey(), attributeValue);
        }
        return dynamoData;
    }

    private Map<String, Object> convertFromDynamoAttributes(Map<String, AttributeValue> dynamoData) {
        Map<String, Object> data = new HashMap<>();
        for (Map.Entry<String, AttributeValue> entry : dynamoData.entrySet()) {
            AttributeValue value = entry.getValue();
            if (value.getS() != null) {
                data.put(entry.getKey(), value.getS());
            } else if (value.getN() != null) {
                data.put(entry.getKey(), Long.valueOf(value.getN()));
            } else if (value.getBOOL() != null) {
                data.put(entry.getKey(), value.getBOOL());
            }
        }
        return data;
    }
}
