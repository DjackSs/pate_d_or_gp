package filter;

import java.util.regex.Pattern;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

public class XssWrapper extends HttpServletRequestWrapper 
{
	
	private static final Pattern[] XSS_PATERNS = 
		{ 
				Pattern.compile("<.*?>"),	//detect html balise patern
				Pattern.compile("&.*?;"),	//detect html symbole (&#8249; = <)
				Pattern.compile("%[0-9a-fA-F]*") //detect hexadecimal digit character (%3c = <)	
		}; 

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
	
	//body sanitation
	@Override
	public String getParameter(String paramater)
	{
		return removeTags(super.getParameter(paramater));
		
	}
	
	//header sanitation
	@Override
    public String getHeader( String name ) 
	{
        return removeTags( super.getHeader(name) );
    }
	
	

}
