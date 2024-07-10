package br.com.portoseguro.util.dbdynamodatahandler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class GetScanTableUseCaseTest {
    @Mock
    private DynamoDbRepository dynamoDbRepository;

    @InjectMocks
    private GetScanTableUseCase getScanTableUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve recuperar os registros de uma tabela")
    public void deveRecuperarOsRegistroDeUmaTabela() {
        String tableName = "order";
        Map<String, Object> records = new HashMap<>();
        records.put("order_id", "123");
        records.put("value", 100);

        when(dynamoDbRepository.scanTable(tableName)).thenReturn(records);

        GetScanTableUseCase.OutputGetRecordsTableUseCase output = getScanTableUseCase.execute(tableName);

        assertEquals(2, output.records().size());
        assertEquals("123", output.records().get("order_id"));
        assertEquals(100, output.records().get("value"));
    }
}