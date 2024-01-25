package filter;

import java.util.regex.Pattern;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

public class XssWrapper extends HttpServletRequestWrapper 
{
	
	private static final Pattern[] XSS_PATERNS = { Pattern.compile("<.*?>"),Pattern.compile("&.*?;"),Pattern.compile("%[0-9a-fA-F]*")}; 

	public XssWrapper(HttpServletRequest request) 
	{
		super(request);
	}
	
	public String[] getParameterValue (String parameter)
	{
		String [] values = super.getParameterValues(parameter);
		
		if(values == null) return null;
		
		int count = values.length;
		
		for(int i = 0; i<count; i++)
		{
			values[i] = removeTags(values[i]);
		}
		
		return values;
	}
	
	@Override
	public String getParameter(String paramater)
	{
		return removeTags(super.getParameter(paramater));
		
	}
	
	private String removeTags(String value)
	{
		if(value != null)
		{
			
			for (Pattern pattern : XSS_PATERNS)
			{
				value = pattern.matcher(value).replaceAll("");
			}
			
			value = value.replaceAll( "<", "" );
            value = value.replaceAll( ">", "" );
			
		}
		
		return value;
	}

}
