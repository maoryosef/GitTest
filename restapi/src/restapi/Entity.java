package restapi;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Entity")
// JAX-RS supports an automatic mapping from JAXB annotated class to XML and JSON
// Isn't that cool?

public class Entity extends AbstractEntity{
	private int m_Id;
	private String m_Summarya;
	private String m_Description;
	
	public Entity() {}
	
	@XmlElement(name="Summary")
	public String getSummarya() {
		return m_Summarya;
	}
	
	
	public int getId() {
		return m_Id;
	}


	public void setId(int id) {
		this.m_Id = id;
	}


	public void setSummary(String summary) {
		this.m_Summarya = summary;
	}
	
	public String getDescription() {
		return m_Description;
	}
	
	public void setDescription(String description) {
		this.m_Description = description;
	}

	public Entity(int id, String summary) {
		this.m_Summarya = summary;
		this.m_Id = id;
	}
	
	
}
