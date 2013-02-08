package restapi;

import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.model.AbstractMethod;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;
import com.sun.jersey.spi.container.ContainerResponseFilter;
import com.sun.jersey.spi.container.ResourceFilter;
import com.sun.jersey.spi.container.ResourceFilterFactory;

public class PermissionsResourceFilterFactory implements ResourceFilterFactory {
	@Context ServletContext context;
	@Context HttpServletRequest httpReq;
	
	private class ResFilter implements ResourceFilter, ContainerRequestFilter {
	       private final boolean denyAll;
	       private final String[] rolesAllowed;
	       
	       
	       protected ResFilter() {
	           this.denyAll = true;
	           this.rolesAllowed = null;
	       }
	 
	       protected ResFilter(String[] rolesAllowed) {
	           this.denyAll = false;
	           this.rolesAllowed = (rolesAllowed != null) ? rolesAllowed : new String[] {};
	       }
	 
	       // ResourceFilter
	 
	       @Override
	       public ContainerRequestFilter getRequestFilter() {
	           return this;
	       }
	 
	       @Override
	       public ContainerResponseFilter getResponseFilter() {
	           return null;
	       }
	 
	       @SuppressWarnings("unused")
		@Override
	       public ContainerRequest filter(ContainerRequest request) {
	    	   HttpSession session= httpReq.getSession(true);
	    	   
	    	   if (session.getAttribute("test") == null) {
	    		   session.setAttribute("test", httpReq.getParameter("p"));
	    	   } else {
	   	    	   System.out.println(session.getAttribute("test"));
	    	   }
	    	   
	    	   
	    	   String authHeader = request.getHeaderValue("Authorization");
	    	   System.out.println(authHeader);
	    	   Enumeration enames;
	    	   
	    	   enames = httpReq.getHeaderNames();
	    	   while (enames.hasMoreElements()) {
	    	      String name = (String) enames.nextElement();
	    	      String value = httpReq.getHeader(name);
	    	      System.out.println(String.format("%s = %s", name, value));
	    	   }
	    		   
	    	   
	    	   System.out.println(httpReq.getRemoteAddr() + ":" + httpReq.getRemotePort() + ":" + httpReq.getRemoteUser() + ":" + httpReq.getRemoteHost());
	    	   if (context.getAttribute("test") == null) {
	    		   System.out.println("set context parameter");
	    		   context.setAttribute("test", request.getBaseUri());
	    	   } else {
	    		   System.out.println("get context parameter " + context.getAttribute("test"));
	    	   }
	    	   
	    	   return request;
	       }
	   }
	  
	@Override
	public List<ResourceFilter> create(AbstractMethod am) {
		System.out.println("Checking methods");
       if (am.isAnnotationPresent(DenyAll.class))
           return Collections.<ResourceFilter>singletonList(new ResFilter());
 
       System.out.println("Checking allowed roles " + am.getMethod().getName());
       // RolesAllowed on the method takes precedence over PermitAll
       AllowedRoles ra = am.getAnnotation(AllowedRoles.class);
       if (ra != null) {
    	   System.out.println("Setting filter");
           return Collections.<ResourceFilter>singletonList(new ResFilter(ra.values()));
       }
       // PermitAll takes precedence over RolesAllowed on the class
       if (am.isAnnotationPresent(PermitAll.class))
           return null;
 
       // RolesAllowed on the class takes precedence over PermitAll
       ra = am.getResource().getAnnotation(AllowedRoles.class);
       if (ra != null)
           return Collections.<ResourceFilter>singletonList(new ResFilter(ra.values()));
 
       // No need to check whether PermitAll is present.
       return null;
	}

}
