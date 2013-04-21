package restapi;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;
import com.sun.jersey.spi.container.ContainerResponseFilter;
import com.sun.jersey.spi.container.ResourceFilter;

public class JerseyFilter implements ResourceFilter, ContainerRequestFilter{

	@Override
	public ContainerRequest filter(ContainerRequest request) {
		System.out.println("Filtering new :: " + request.getPath(true));
		
		// TODO Auto-generated method stub
		return request;
	}

	@Override
	public ContainerRequestFilter getRequestFilter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ContainerResponseFilter getResponseFilter() {
		// TODO Auto-generated method stub
		return null;
	}

}
