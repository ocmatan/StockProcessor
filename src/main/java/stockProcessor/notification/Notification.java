package stockProcessor.notification;

public class Notification {

    private String message;

    private long timestamp;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "message='" + message + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }


}
