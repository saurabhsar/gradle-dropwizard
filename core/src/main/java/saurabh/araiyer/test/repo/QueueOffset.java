package saurabh.araiyer.test.repo;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class QueueOffset {
    private Map<Integer, Integer> queueIdToLastOffset = new HashMap<>();

    @Inject
    public QueueOffset() {}

    public Map<Integer, Integer> getQueueIdToLastOffset() {
        return this.queueIdToLastOffset;
    }

    public void setQueueIdToLastOffset(Map<Integer, Integer> queueIdToLastOffset) {
        this.queueIdToLastOffset = queueIdToLastOffset;
    }
}
