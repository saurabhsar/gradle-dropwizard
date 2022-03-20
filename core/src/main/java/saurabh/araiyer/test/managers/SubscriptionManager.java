package saurabh.araiyer.test.managers;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import saurabh.araiyer.test.repo.Queue;
import saurabh.araiyer.test.repo.QueueOffset;
import saurabh.araiyer.test.repo.Subscription;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Singleton
public class SubscriptionManager {
    private final Map<Subscription, Integer> subscriptionToOffset = Maps.newHashMap();
    private final Map<Integer, List<Subscription>> queueToSubscriptions = Maps.newHashMap();
    private final QueueManager queueManager;
    private final QueueOffset queueOffset;

    @Inject
    public SubscriptionManager(QueueManager queueManager, QueueOffset queueOffset) {
        this.queueManager = queueManager;
        this.queueOffset = queueOffset;
    }

    public boolean register(Subscription subscription) {
        List<Subscription> existing = queueToSubscriptions.getOrDefault(subscription.getQueue().getQueueDescriptor().getId(),
                Lists.newArrayList());

        if (existing.stream().anyMatch(e -> e.getSubscriptionDescriptor().getName()
                .equals(subscription.getSubscriptionDescriptor().getName()))) {
            return false;
        }

        existing.add(subscription);
        subscriptionToOffset.put(subscription, subscription.getQueue().getDataBucket().getLatestOffset());
        subscription.setOffset(subscription.getQueue().getDataBucket().getLatestOffset());
        queueToSubscriptions.put(subscription.getQueue().getQueueDescriptor().getId(), existing);

        return true; //todo false for already existing
    }

    public boolean unregister(Queue queue, String subscriptionName) {
        int queueId = queue.getQueueDescriptor().getId();
        Optional<Subscription> subToRemove =
                queueToSubscriptions.get(queueId).stream().filter(e ->
                                subscriptionName.equals(e.getSubscriptionDescriptor().getName())).findFirst();

        if (subToRemove.isPresent()) {
            subscriptionToOffset.remove(subToRemove.get());
            queueToSubscriptions.get(queueId).remove(subToRemove.get());
        }

        return true; //todo false for already existing
    }

    public void startConsumer() {
        CompletableFuture.runAsync(() -> {
            for (Map.Entry<Integer, Integer> entry : queueOffset.getQueueIdToLastOffset().entrySet()) {
                for (Subscription subscription : queueToSubscriptions.get(entry.getKey())) {
                    while (entry.getValue() > subscription.getOffset()) {
                        try {
                            subscription.consume();
                        } catch (Exception e) {
                            e.printStackTrace();
                            //ignore for now
                        }

                    }
                }
            }
        });
    }
}
