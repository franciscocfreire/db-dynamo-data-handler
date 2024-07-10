package br.com.portoseguro.util.dbdynamodatahandler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.verify;

public class SaveRecordUseCaseTest {

    @Mock
    private DynamoDbRepository dynamoDbRepository;

    @InjectMocks
    private SaveRecordUseCase saveRecordUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve salvar um registro na tabela")
    public void deveSalvarUmRegistroNaTabela() {
        String tableName = "order";
        Map<String, Object> records = new HashMap<>();
        records.put("order_id", "123");
        records.put("value", 100);

        DataRequest dataRequest = new DataRequest(tableName, records);

        saveRecordUseCase.execute(dataRequest);

        verify(dynamoDbRepository).save(dataRequest);
    }
}
