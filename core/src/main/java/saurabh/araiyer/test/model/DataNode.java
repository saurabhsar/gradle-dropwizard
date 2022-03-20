package saurabh.araiyer.test.model;

import com.fasterxml.jackson.databind.JsonNode;

public class DataNode {
    private JsonNode data;

    public JsonNode getData() {
        return this.data;
    }

    public void setData(JsonNode data) {
        this.data = data;
    }
    //placeholders for something like headers etc
}
