package model;

import Constants.Status;
import Constants.Type;

import java.util.Objects;

public class Notification {
    private String notificationId;
    private Type type;
    private Status status;
    private User user;
    private String message;

    public Notification(String notificationId, Type type, Status status, User user, String message) {
        this.notificationId = notificationId;
        this.type = type;
        this.status = status;
        this.user = user;
        this.message = message;
    }

    public String getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "notificationId='" + notificationId + '\'' +
                ", type=" + type +
                ", status=" + status +
                ", user=" + user +
                ", message='" + message + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notification that = (Notification) o;
        return notificationId.equals(that.notificationId) && user.equals(that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(notificationId, user);
    }
}
