package br.com.portoseguro.util.dbdynamodatahandler;

import java.util.Map;

public class DataRequest {

    private String tableName;
    private Map<String, Object> data;

    public DataRequest() {}

    public DataRequest(String tableName, Map<String, Object> data) {
        this.tableName = tableName;
        this.data = data;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DataRequest{" +
                "tableName='" + tableName + '\'' +
                ", data=" + data +
                '}';
    }
}