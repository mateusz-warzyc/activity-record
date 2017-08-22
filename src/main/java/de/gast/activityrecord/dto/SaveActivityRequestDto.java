package de.gast.activityrecord.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created by mateusz-warzyc.
 */
public class SaveActivityRequestDto {

    @NotNull
    @NotEmpty
    private String sessionId;

    @NotNull
    @NotEmpty
    private String clientIp;

    @NotNull
    @NotEmpty
    private String domain;

    @NotNull
    @NotEmpty
    private String path;

    @NotNull
    @NotEmpty
    private String hostName;

    @NotNull
    @NotEmpty
    private String hostIp;

    public SaveActivityRequestDto() {
        // default constructor for json binding
    }

    public SaveActivityRequestDto(String sessionId, String clientIp, String domain, String path, String hostName, String hostIp) {
         this.sessionId = sessionId;
         this.clientIp = clientIp;
         this.domain = domain;
         this.path = path;
         this.hostName = hostName;
         this.hostIp = hostIp;
     }

     public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SaveActivityRequestDto that = (SaveActivityRequestDto) o;
        return new EqualsBuilder()
                .append(sessionId, that.sessionId).append(clientIp, that.clientIp).append(domain, that.domain)
                .append(path, that.path).append(hostName, that.hostName).append(hostIp, that.hostIp).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(sessionId)
                .append(clientIp).append(domain).append(path).append(hostName).append(hostIp).toHashCode();
    }

    @Override
    public String toString() {
        return "SaveActivityRequestDto{" +
                "sessionId='" + sessionId + '\'' +
                ", clientIp='" + clientIp + '\'' +
                ", domain='" + domain + '\'' +
                ", path='" + path + '\'' +
                ", hostName='" + hostName + '\'' +
                ", hostIp='" + hostIp + '\'' +
                '}';
    }
}
