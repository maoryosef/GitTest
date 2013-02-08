package restapi;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Response")
@XmlType(propOrder={"errorCode", "errorMessage", "entity"}, name="Response")
public class response {
	private List<AbstractEntity> entities;
	private int errorCode = 1;
	private String errorMessage = "bla";
	
	
	
	public response() {
		entities = new ArrayList<AbstractEntity>();
	}

	@XmlElementWrapper(name="Data")
	
	public List<AbstractEntity> getEntity() {
		return entities;
	}
	
	@XmlElement(name="ErrorCode")
	public int getErrorCode() {
		return errorCode;
	}
	
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	
	@XmlElement(name="ErrorName")
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	

	public void setEntity(List<AbstractEntity> entity) {
		this.entities = entity;
	}
	
	public void addEntity(Entity entity) {
		entities.add(entity);
	}
	
	
}
