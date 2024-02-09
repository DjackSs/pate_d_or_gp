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
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebFilter(dispatcherTypes= 
{
		DispatcherType.REQUEST,
		DispatcherType.FORWARD,
		DispatcherType.INCLUDE,
		DispatcherType.ERROR,
		
}, urlPatterns = {"/user", "/contact","/updateUser/*", "/reservation/*", "/contact/*"})
public class FilterSession extends HttpFilter implements Filter 
{
	private static final long serialVersionUID = 3736531170359901374L;

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException 
	{
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		
		if(req.getSession().getAttribute("user") != null)
		{
			chain.doFilter(request, response);
			
		}
		else
		{
			res.sendRedirect(req.getContextPath()+"/connection");
			
		}
		
		
		
	}

	
	public void init(FilterConfig fConfig) throws ServletException 
	{
		
	}

}
