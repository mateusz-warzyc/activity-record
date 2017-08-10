package de.gast.activityrecord.entity;

import de.gast.activityrecord.listener.DeleteActivityListener;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="activity")
@EntityListeners(DeleteActivityListener.class)
public class Activity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    @Column(name = "client_ip")
    private String clientIp;
    
    @Column(name = "session_id")
    private String sessionId;
    
    @Column(name = "counter")
    private int counter;
    
    @Column(name = "begin")
    private Date begin;
    
    @Column(name = "last")
    private Date last;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getLast() {
        return last;
    }

    public void setLast(Date last) {
        this.last = last;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", clientIp='" + clientIp + '\'' +
                ", sessionId='" + sessionId + '\'' +
                ", counter=" + counter +
                ", begin=" + begin +
                ", last=" + last +
                '}';
    }
}
