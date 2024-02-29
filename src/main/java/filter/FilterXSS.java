package filter;

import java.io.IOException;

import jakarta.servlet.DispatcherType;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


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

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException 
	{
		//https://koor.fr/Java/TutorialJEE/jee_attaques_xss.wp
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		req = new XssWrapper(req);
		
		chain.doFilter(req , res );
	}
	
	

}
