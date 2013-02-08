package restapi;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBException;

@Path("/entity")
public class EntityResource {
	// This method is called if XMLis request
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("{entityId}")
	public Entity getXML(@PathParam("entityId") String id) throws Exception {
		Entity entity = new Entity(Integer.parseInt(id),"This is my first entity");
		entity.setDescription("This is my first entity");
	
		return entity;
	}
	// This can be used to test the integration with the browser
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@AllowedRoles(values = {"role1", "role2"})
	public response getHTML() throws JAXBException {
		Entity entity = new Entity(1, "This is my first entit123123y");
		Entity entity2 = new Entity(2, "This is my second entity");
		entity.setDescription("This is my first entity");
		response res = new response();
		
		res.addEntity(entity);
		res.addEntity(entity2);
		
		
		return res;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("/test")
	public TestBean test() {
		return new TestBean(); 
	}
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/post")
	@Consumes(MediaType.APPLICATION_XML)
	public String testing(TestBean tb) {
		String res = tb.getStringField();
		res += " " + tb.getEnumField();
		
		return res;
	}
}
