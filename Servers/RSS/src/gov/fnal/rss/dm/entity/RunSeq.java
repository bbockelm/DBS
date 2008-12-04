package gov.fnal.rss.dm.entity;
import java.io.Serializable;


public class RunSeq implements Serializable {
	static final long serialVersionUID = 46543431;

 
    private Long startNumber;
    private Long endNumber;
    private Long id;
    private Long currentNumber;
    private String name;

    public RunSeq() {
    }

    public Long getStartNumber() {
        return startNumber;
    }

    public void setStartNumber(Long startNumber) {
        this.startNumber = startNumber;
    }

    public Long getEndNumber() {
        return endNumber;
    }

    public void setEndNumber(Long endNumber) {
        this.endNumber = endNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCurrentNumber() {
        return currentNumber;
    }

    public void setCurrentNumber(Long currentNumber) {
        this.currentNumber = currentNumber;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
