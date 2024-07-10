package br.com.portoseguro.util.dbdynamodatahandler;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class GetScanTableUseCase {

    private final DynamoDbRepository dynamoDbRepository;

    public GetScanTableUseCase(DynamoDbRepository dynamoDbRepository) {
        this.dynamoDbRepository = dynamoDbRepository;
    }

    public OutputGetRecordsTableUseCase execute(String tableName) {
        Map<String, Object> records = dynamoDbRepository.scanTable(tableName);
        return new OutputGetRecordsTableUseCase(records);
    }

    public record OutputGetRecordsTableUseCase(Map<String, Object> records) {
    }


}
