package saurabh.araiyer.test.model;

public class ServerResponse {
    private int status;
    private String response;
    private ResponseStatus responseStatus;

    public ServerResponse() {
    }

    public int getStatus() {
        return this.status;
    }

    public String getResponse() {
        return this.response;
    }

    public ResponseStatus getResponseStatus() {
        return this.responseStatus;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public void setResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ServerResponse)) return false;
        final ServerResponse other = (ServerResponse) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getStatus() != other.getStatus()) return false;
        final Object this$response = this.getResponse();
        final Object other$response = other.getResponse();
        if (this$response == null ? other$response != null : !this$response.equals(other$response)) return false;
        final Object this$responseStatus = this.getResponseStatus();
        final Object other$responseStatus = other.getResponseStatus();
        if (this$responseStatus == null ? other$responseStatus != null : !this$responseStatus.equals(other$responseStatus))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ServerResponse;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getStatus();
        final Object $response = this.getResponse();
        result = result * PRIME + ($response == null ? 43 : $response.hashCode());
        final Object $responseStatus = this.getResponseStatus();
        result = result * PRIME + ($responseStatus == null ? 43 : $responseStatus.hashCode());
        return result;
    }

    public String toString() {
        return "ServerResponse(status=" + this.getStatus() + ", response=" + this.getResponse() + ", responseStatus=" + this.getResponseStatus() + ")";
    }

    public enum ResponseStatus {
        FAIL,
        SUCCESS,
    }
}