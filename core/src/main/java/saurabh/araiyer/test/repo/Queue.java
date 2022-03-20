package saurabh.araiyer.test.repo;

import com.fasterxml.jackson.databind.JsonNode;
import saurabh.araiyer.test.model.DataBucket;
import saurabh.araiyer.test.model.DataNode;
import saurabh.araiyer.test.model.QueueDescriptor;

public class Queue {
    private QueueDescriptor queueDescriptor;
    private final DataBucket dataBucket;

    public Queue(DataBucket dataBucket) {
        this.dataBucket = dataBucket;
    }

    public void initialize(QueueDescriptor queueDescriptor) {
        this.queueDescriptor = queueDescriptor;
    }

    public void addMessage(JsonNode message) {
        DataNode dataNode = new DataNode();
        dataNode.setData(message);
        dataBucket.addMessage(dataNode);
    }

    //packagePrivate
    public void remove(int offset) {
        dataBucket.remove(offset);
    }

    public QueueDescriptor getQueueDescriptor() {
        return this.queueDescriptor;
    }

    public DataBucket getDataBucket() {
        return this.dataBucket;
    }

    public void setQueueDescriptor(QueueDescriptor queueDescriptor) {
        this.queueDescriptor = queueDescriptor;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Queue)) return false;
        final Queue other = (Queue) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$queueDescriptor = this.getQueueDescriptor();
        final Object other$queueDescriptor = other.getQueueDescriptor();
        if (this$queueDescriptor == null ? other$queueDescriptor != null : !this$queueDescriptor.equals(other$queueDescriptor))
            return false;
        final Object this$dataBucket = this.getDataBucket();
        final Object other$dataBucket = other.getDataBucket();
        if (this$dataBucket == null ? other$dataBucket != null : !this$dataBucket.equals(other$dataBucket))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Queue;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $queueDescriptor = this.getQueueDescriptor();
        result = result * PRIME + ($queueDescriptor == null ? 43 : $queueDescriptor.hashCode());
        final Object $dataBucket = this.getDataBucket();
        result = result * PRIME + ($dataBucket == null ? 43 : $dataBucket.hashCode());
        return result;
    }
}
