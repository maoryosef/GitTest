package com.angularspringtest.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
public class Ticket {
	String name;
	String refID;
    String description;
	Integer id;
    Date openDate;

    public Ticket(String name, String refID, String description, Date openDate) {
        this.name = name;
        this.refID = refID;
        this.description = description;
        this.openDate = openDate;
    }

    public Ticket() {
    }

    public String getRefID() {
        return refID;
    }

    public void setRefID(String refID) {
        this.refID = refID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

    @Override
    public String toString() {
        return "Ticket{" +
                "name='" + name + '\'' +
                ", refID='" + refID + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", openDate=" + openDate +
                '}';
    }
}
