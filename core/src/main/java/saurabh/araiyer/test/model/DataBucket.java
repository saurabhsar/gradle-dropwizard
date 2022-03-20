package saurabh.araiyer.test.model;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;


public class DataBucket {
    //for concurrent production
    private final Map<Integer, DataNode> queuedData = Maps.newConcurrentMap();
    private static final AtomicInteger messageCounter = new AtomicInteger();

    public void addMessage(DataNode data) {
        queuedData.put(messageCounter.getAndIncrement(), data);
    }

    public DataNode consume(int offset) {
        return queuedData.get(offset);
    }

    public void remove(int offset) {
        queuedData.remove(offset);
    }

    public int getLatestOffset() {
        return messageCounter.get();
    }
}
