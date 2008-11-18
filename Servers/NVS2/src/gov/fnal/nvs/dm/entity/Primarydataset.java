package gov.fnal.nvs.dm.entity;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;

public class Primarydataset implements Serializable {
    private String annotation;
    private Long createdby;
    private Long creationdate;
    private Long description;
    private String enddate;
    private Long id;
    private Long lastmodificationdate;
    private Long lastmodifiedby;
    private String name;
    private String startdate;
    private Long type;
    private Set<Processeddataset> processedDatasetSet;

    public Primarydataset() {
	    this.processedDatasetSet = new HashSet<Processeddataset>();
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
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

    public Long getDescription() {
        return description;
    }

    public void setDescription(Long description) {
        this.description = description;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }
    
    /*public List<Processeddataset> getProcessedDatasetList() {
	    return processedDatasetList;
    }
    public void setProcessedDatasetList(List<Processeddataset> processedDatasetList) {
	    this.processedDatasetList = processedDatasetList;
    }*/
	
    public Set<Processeddataset> getProcessedDatasetSet() {
	    return processedDatasetSet;
    }
    public void setProcessedDatasetSet(Set<Processeddataset> processedDatasetSet) {
	    this.processedDatasetSet = processedDatasetSet;
    }
  
}
