package gov.fnal.nvs.dm.entity;
import java.io.Serializable;

public class Physicsgroup implements Serializable {
    private Long createdby;
    private Long creationdate;
    private Long id;
    private Long lastmodificationdate;
    private Long lastmodifiedby;
    private Long physicsgroupconvener;
    private String physicsgroupname;

    public Physicsgroup() {
    }

    public Long getCreatedby() {
        return createdby;
    }

    public void setCreatedby(Long createdby) {
        this.createdby = createdby;
    }

    public Long getCreationdate() {
        return creationdate;
    }

    public void setCreationdate(Long creationdate) {
        this.creationdate = creationdate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLastmodificationdate() {
        return lastmodificationdate;
    }

    public void setLastmodificationdate(Long lastmodificationdate) {
        this.lastmodificationdate = lastmodificationdate;
    }

    public Long getLastmodifiedby() {
        return lastmodifiedby;
    }

    public void setLastmodifiedby(Long lastmodifiedby) {
        this.lastmodifiedby = lastmodifiedby;
    }

    public Long getPhysicsgroupconvener() {
        return physicsgroupconvener;
    }

    public void setPhysicsgroupconvener(Long physicsgroupconvener) {
        this.physicsgroupconvener = physicsgroupconvener;
    }

    public String getPhysicsgroupname() {
        return physicsgroupname;
    }

    public void setPhysicsgroupname(String physicsgroupname) {
        this.physicsgroupname = physicsgroupname;
    }
}
