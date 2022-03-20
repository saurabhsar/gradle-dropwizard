package saurabh.araiyer.test.managers;

import com.fasterxml.jackson.databind.JsonNode;
import saurabh.araiyer.test.model.DataBucket;
import saurabh.araiyer.test.model.QueueDescriptor;
import saurabh.araiyer.test.model.SubscriptionDescriptor;
import saurabh.araiyer.test.repo.Queue;
import saurabh.araiyer.test.repo.Subscription;

import javax.inject.Inject;

public class QueueService {
    private final QueueManager queueManager;
    private final SubscriptionManager subscriptionManager;

    @Inject
    public QueueService(QueueManager queueManager, SubscriptionManager subscriptionManager) {
        this.queueManager = queueManager;
        this.subscriptionManager = subscriptionManager;
    }

    public boolean registerQueue (String name) {
        Queue queue = new Queue(new DataBucket());
        queue.setQueueDescriptor(new QueueDescriptor());
        queue.getQueueDescriptor().setName(name);
        return queueManager.register(queue);
    }

    public boolean registerSubscription(String queueName, String subscriptionName, String httpEndpoint) {
        Queue queue = queueManager.getQueueByName(queueName);
        if (queue == null) {
            return false;
        }
        SubscriptionDescriptor subscriptionDescriptor = new SubscriptionDescriptor();
        subscriptionDescriptor.setName(subscriptionName);
        subscriptionDescriptor.setDestinationHttpEndpoint(httpEndpoint);
        Subscription subscription = new Subscription(queue, subscriptionDescriptor);
        return subscriptionManager.register(subscription);
    }

    public boolean deleteSubscription(String queueName, String subscriptionName) {
        Queue queue = queueManager.getQueueByName(queueName);
        return subscriptionManager.unregister(queue, subscriptionName);
    }

    public void produceMessage(String queueName, JsonNode data) {
        queueManager.produce(queueName, data);
    }

    public void startConsumer() {
        subscriptionManager.startConsumer();
    }
}
