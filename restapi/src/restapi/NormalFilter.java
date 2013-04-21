package restapi;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.jersey.api.core.PackagesResourceConfig;


public class NormalFilter implements Filter {
    
	@Override
    public void destroy() {
        // TODO Auto-generated method stub        
    }
    
	@SuppressWarnings("unused")
	@Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {     
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse httpResponse=(HttpServletResponse)res;
        //Get the IP address of client machine.
        String ipAddress = request.getRemoteAddr();

        //Log the IP address and current timestamp.
        System.out.println("IP "+ipAddress + ", Time "
                            + new Date().toString() +", URI " + request.getRequestURI() + ", URL " + request.getRequestURI());
        
        if (/*if not authanticated throw to login.jsp*/false) {
        	 httpResponse.sendRedirect("login.jsp");
        } else {
        
        	chain.doFilter(req, res);
        }
    }
    
	@Override
    public void init(FilterConfig config) throws ServletException {
        @SuppressWarnings("unused")
		PackagesResourceConfig resourceConfig = new PackagesResourceConfig(config.getInitParameter("package"));
        System.out.println("Initilizing Filter");
    } 
} 
