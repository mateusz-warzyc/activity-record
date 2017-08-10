package de.gast.activityrecord.entity;

import de.gast.activityrecord.listener.DeleteRouteListener;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="route")
@EntityListeners(DeleteRouteListener.class)
public class Route {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    @ManyToOne
    @JoinColumn(name="activity_id")
    private Activity activity;
    
    @Column(name = "domain")
    private String domain;
    
    @Column(name = "path")
    private String path;
    
    @Column(name = "host_name")
    private String hostName;
    
    @Column(name = "host_ip")
    private String hostIp;
    
    @Column(name = "time_stamp")
    private Date timeStamp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", activity=" + activity +
                ", domain='" + domain + '\'' +
                ", path='" + path + '\'' +
                ", hostName='" + hostName + '\'' +
                ", hostIp='" + hostIp + '\'' +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
