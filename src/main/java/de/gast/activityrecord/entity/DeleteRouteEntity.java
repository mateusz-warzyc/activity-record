package de.gast.activityrecord.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by mateusz-warzyc.
 */
@Entity
@Table(name = "RouteDeletionLog")
public class DeleteRouteEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private String description;

    private Date timestamp;

    private int activityId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        DeleteRouteEntity that = (DeleteRouteEntity) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .append(activityId, that.activityId)
                .append(description, that.description)
                .append(timestamp, that.timestamp)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(description)
                .append(timestamp)
                .append(activityId)
                .toHashCode();
    }
}
