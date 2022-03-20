package saurabh.araiyer.test.managers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Maps;
import saurabh.araiyer.test.repo.Queue;
import saurabh.araiyer.test.repo.QueueOffset;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Map;

@Singleton
public class QueueManager {
    private Map<Integer, Queue> queues = Maps.newHashMap();
    private Map<String, Integer> qNameToId = Maps.newHashMap();
    private final QueueOffset queueOffset;

    @Inject
    public QueueManager(QueueOffset queueOffset) {
        this.queueOffset = queueOffset;
    }

    public Queue getQueueByName(String name) {
        return queues.get(qNameToId.get(name));
    }

    public boolean register(Queue queue) {
        if (qNameToId.containsKey(queue.getQueueDescriptor().getName())) {
            System.out.println("Already Exist");
            return false;
        }

        queues.put(queue.getQueueDescriptor().getId(), queue);
        qNameToId.put(queue.getQueueDescriptor().getName(), queue.getQueueDescriptor().getId());

        return true; //todo false for already existing
    }

    public void produce (String name, JsonNode message) {
        queues.get(qNameToId.get(name)).addMessage(message);
        int lastProduceOffset = queueOffset.getQueueIdToLastOffset().getOrDefault(qNameToId.get(name), 0);
        queueOffset.getQueueIdToLastOffset().put(qNameToId.get(name), ++lastProduceOffset);
    }
}
