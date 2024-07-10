package br.com.portoseguro.util.dbdynamodatahandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveRecordUseCase {

    private final DynamoDbRepository dynamoDbRepository;

    @Autowired
    public SaveRecordUseCase(DynamoDbRepository dynamoDbRepository) {
        this.dynamoDbRepository = dynamoDbRepository;
    }

    public void execute(DataRequest dataRequest) {
        dynamoDbRepository.save(dataRequest);
    }
}
