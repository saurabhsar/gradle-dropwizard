package saurabh.araiyer.test.model;

import com.google.common.collect.Maps;

import java.util.Map;

public class SubscriptionDescriptor {
    private Integer id;
    private String name;
    private String ownerName;
    private String destinationHttpEndpoint;
    private Integer timeout = 1000;
    private Map<String, String> metadata = Maps.newHashMap();

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getOwnerName() {
        return this.ownerName;
    }

    public String getDestinationHttpEndpoint() {
        return this.destinationHttpEndpoint;
    }

    public Integer getTimeout() {
        return this.timeout;
    }

    public Map<String, String> getMetadata() {
        return this.metadata;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setDestinationHttpEndpoint(String destinationHttpEndpoint) {
        this.destinationHttpEndpoint = destinationHttpEndpoint;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }
}
