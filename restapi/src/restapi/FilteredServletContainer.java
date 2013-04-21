package restapi;

import java.io.IOException;
import java.net.URI;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.spi.container.WebApplication;
import com.sun.jersey.spi.container.servlet.ServletContainer;

public class FilteredServletContainer extends ServletContainer {

	private static final long serialVersionUID = -1996858182384107962L;

	@SuppressWarnings("unused")
	private WebApplication application;
	
	@Override
	protected void initiate(ResourceConfig rc, WebApplication wa) {
		// TODO Auto-generated method stub
		application = wa;
		super.initiate(rc, wa);
	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		super.service(request, response);
	}

	@Override
	public int service(URI baseUri, URI requestUri, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		return super.service(baseUri, requestUri, request, response);
	}
	
}
