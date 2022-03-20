package saurabh.araiyer.test.repo;

import lombok.SneakyThrows;
import saurabh.araiyer.test.client.ClientImpl;
import saurabh.araiyer.test.model.ServerResponse;
import saurabh.araiyer.test.model.SubscriptionDescriptor;

import java.util.concurrent.atomic.AtomicInteger;

public class Subscription {
    private static final AtomicInteger id = new AtomicInteger();
    private final Queue queue;
    private final SubscriptionDescriptor subscriptionDescriptor;
    private int offset;

    public Subscription(Queue queue, SubscriptionDescriptor subscriptionDescriptor) {
        this.subscriptionDescriptor = subscriptionDescriptor;
        id.incrementAndGet();
        this.queue = queue;
    }

    @SneakyThrows
    public void consume() {
        System.out.println("Consuming for "+ subscriptionDescriptor.getName() + offset);
        ServerResponse serverResponse = ClientImpl.getResponse(subscriptionDescriptor.getDestinationHttpEndpoint(),
                queue.getDataBucket().consume(offset).getData(),
                subscriptionDescriptor.getTimeout());

        if (ServerResponse.ResponseStatus.SUCCESS.equals(serverResponse.getResponseStatus())) {
            offset ++;
        }
    }

    public Queue getQueue() {
        return this.queue;
    }

    public SubscriptionDescriptor getSubscriptionDescriptor() {
        return this.subscriptionDescriptor;
    }

    public int getOffset() {
        return this.offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}
