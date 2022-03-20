package saurabh.araiyer.test.model;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class QueueDescriptor {
    private static final AtomicInteger id = new AtomicInteger();
    private String name;
    private String ownerName;
    private Map<String, String> metadata = Maps.newHashMap();

    public QueueDescriptor() {
        id.incrementAndGet();
    }

    public int getId () {
        return id.get();
    }

    public String getName() {
        return this.name;
    }

    public String getOwnerName() {
        return this.ownerName;
    }

    public Map<String, String> getMetadata() {
        return this.metadata;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }
}
