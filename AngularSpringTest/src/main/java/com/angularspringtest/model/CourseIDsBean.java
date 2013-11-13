package com.angularspringtest.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Maor
 * Date: 13/11/13
 * Time: 23:17
 */

@XmlRootElement
public class CourseIDsBean {
    List<Integer> ids;

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    @Override
    public String toString() {
        return "CourseIDsBean{" +
                "ids=" + ids +
                '}';
    }
}
