package filter;

import jakarta.servlet.DispatcherType;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import java.io.IOException;


@WebFilter(dispatcherTypes= 
{
		DispatcherType.REQUEST,
		DispatcherType.FORWARD,
		DispatcherType.INCLUDE,
		DispatcherType.ERROR,
		
}, urlPatterns = {"/*"})
public class FilterXSS extends HttpFilter implements Filter 
{
	
	private static final long serialVersionUID = 5469743367551988020L;

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
       
   
	
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException 
	{
		
		chain.doFilter(request, response);
	}
	
	public void destroy() {
		// TODO Auto-generated method stub
	}


	
	

}
