package br.com.portoseguro.util.dbdynamodatahandler;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class DynamoDataHandlerTest {

    @Autowired
    private SaveRecordUseCase saveRecordUseCase;

    @Autowired
    private GetScanTableUseCase getScanTableUseCase;

    @Autowired
    private DeleteRecordUseCase deleteRecordUseCase;

    @Test
    @DisplayName("Deve salvar um registro na tabela e recuperar o registro")
    public void deveSalvarUmRegistroNaTabela() {
        String tableName = "order";
        Map<String, Object> records = new HashMap<>();
        records.put("order_id", "123");
        records.put("value", 100);

        DataRequest dataRequest = new DataRequest(tableName, records);

        saveRecordUseCase.execute(dataRequest);

        GetScanTableUseCase.OutputGetRecordsTableUseCase output = getScanTableUseCase.execute(tableName);

        assertEquals(2, output.records().size());
        assertEquals("123", output.records().get("order_id"));
        assertEquals(100L, output.records().get("value"));
    }

    @Test
    @DisplayName("Deve deletar um registro na tabela ")
    public void deveSalvarUmRegistroNaTabela1() {
        String tableName = "order";
        Map<String, Object> records = new HashMap<>();
        records.put("order_id", "123");

        DataRequest dataRequest = new DataRequest(tableName, records);

        deleteRecordUseCase.execute(dataRequest);
    }
}
