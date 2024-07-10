package br.com.portoseguro.util.dbdynamodatahandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteRecordUseCase {

    private final DynamoDbRepository dynamoDbRepository;

    @Autowired
    public DeleteRecordUseCase(DynamoDbRepository dynamoDbRepository) {
        this.dynamoDbRepository = dynamoDbRepository;
    }

    public void execute(DataRequest dataRequest) {
        dynamoDbRepository.deleteItem(dataRequest);
    }
}
