package br.com.portoseguro.util.dbdynamodatahandler;

import java.util.Map;

public class DataResponse {

    private Map<String, Object> records;

    public Map<String, Object> getRecords() {
        return records;
    }

    public void setRecords(Map<String, Object> records) {
        this.records = records;
    }

    @Override
    public String toString() {
        return "DataResponse{" +
                "records=" + records +
                '}';
    }
}
