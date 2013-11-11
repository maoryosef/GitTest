package com.angularspringtest.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
public class Course {
	String name;
	Integer id;
    Date startDate;

    public Course(String name, Date startDate) {
        this.name = name;
        this.startDate = startDate;
    }

    public Course() {
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
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
        return "Course{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", startDate=" + startDate +
                '}';
    }
}
