package restapi;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "TestBean")
public class TestBean {
	String stringField;
	TestEnum enumField;
	
	
	public TestBean() {
		stringField = "asd";
		enumField = TestEnum.VALUE_1;
	}

	public String getStringField() {
		return stringField;
	}
	
	public void setStringField(String stringField) {
		this.stringField = stringField;
	}
	
	public TestEnum getEnumField() {
		return enumField;
	}
	
	public void setEnumField(TestEnum enumField) {
		this.enumField = enumField;
	}
	
	
	
}
