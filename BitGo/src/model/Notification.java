package model;

import Constants.Status;
import Constants.Type;

import java.util.Objects;
import java.util.UUID;

public class Notification {
    private UUID notificationId;
    private Type type;
    private Status status;
    private User user;
    private CryptoData cryptoData;

    public Notification(UUID notificationId, Type type, Status status, User user, CryptoData cryptoData) {
        this.notificationId = notificationId;
        this.type = type;
        this.status = status;
        this.user = user;
        this.cryptoData = cryptoData;
    }

    public UUID getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(UUID notificationId) {
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

    public CryptoData getCryptoData() {
        return cryptoData;
    }

    public void setCryptoData(CryptoData cryptoData) {
        this.cryptoData = cryptoData;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "notificationId='" + notificationId + '\'' +
                ", type=" + type +
                ", status=" + status +
                ", user=" + user +
                ", CryptoData='" + cryptoData + '\'' +
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
