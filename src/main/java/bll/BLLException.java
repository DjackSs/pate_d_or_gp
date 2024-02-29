package bll;

import java.util.HashMap;
import java.util.Map;

public class BLLException extends Exception 
{
	private static final long serialVersionUID = -8402728185430838053L;
	
	//user error keys
	private final String USER_EMAIL_SIZE_ERROR_KEY = "emailSize";
	private final String USER_EMAIL_MATCH_ERROR_KEY = "emailMatch";
	private final String USER_PASSWORD_ERROR_KEY = "password";
	private final String USER_NAME_SIZE_ERROR_KEY = "nameSize";
	private final String USER_LASTNAME_SIZE_ERROR_KEY = "lastnameSize";
	private final String USER_DUPLICATE_ERROR_KEY = "duplicate";
	
	//messsage error keys
	private final String MESSAGE_OBJECT_ERROR_KEY = "messageObject";
	private final String MESSAGE_CONTENT_ERROR_KEY = "messageContent";
	
	//reservation error key
	private final String RESERVATION_DATE_ERROR_KEY = "date";
	private final String RESERVATION_HOUR_ERROR_KEY = "hour";
	private final String RESERVATION_DAY_ERROR_KEY = "dateDay";
	private final String RESERVATION_PARSE_ERROR_KEY = "dateTimeParse";
	private final String RESERVATION_TIME_ERROR_KEY = "reservationTime";
  
	private final Map<String, String> errors;
	
	//====================================================

	public BLLException() 
	{
		this.errors = new HashMap<>();
	}

	public BLLException(String message, Throwable cause) 
	{
		super(message, cause);
		this.errors = new HashMap<>();
	}
	
	//====================================================
	
	public void addError(String key, String value) 
	{
		this.errors.put(key, value);
	}
	
	public Map<String, String> getErrors()
	{
		return this.errors;
	}
	
	//-----------------------------------------------------

	

	public String getMESSAGE_OBJECT_ERROR_KEY() {
		return MESSAGE_OBJECT_ERROR_KEY;
	}

	public String getUSER_EMAIL_SIZE_ERROR_KEY() {
		return USER_EMAIL_SIZE_ERROR_KEY;
	}

	public String getUSER_EMAIL_MATCH_ERROR_KEY() {
		return USER_EMAIL_MATCH_ERROR_KEY;
	}

	public String getUSER_PASSWORD_ERROR_KEY() {
		return USER_PASSWORD_ERROR_KEY;
	}

	public String getUSER_NAME_SIZE_ERROR_KEY() {
		return USER_NAME_SIZE_ERROR_KEY;
	}

	public String getUSER_LASTNAME_SIZE_ERROR_KEY() {
		return USER_LASTNAME_SIZE_ERROR_KEY;
	}
	
	

	public String getUSER_DUPLICATE_ERROR_KEY() {
		return USER_DUPLICATE_ERROR_KEY;
	}

	public String getMESSAGE_CONTENT_ERROR_KEY() {
		return MESSAGE_CONTENT_ERROR_KEY;
	}

	public String getRESERVATION_DATE_ERROR_KEY() {
		return RESERVATION_DATE_ERROR_KEY;
	}

	public String getRESERVATION_HOUR_ERROR_KEY() {
		return RESERVATION_HOUR_ERROR_KEY;
	}

	public String getRESERVATION_DAY_ERROR_KEY() {
		return RESERVATION_DAY_ERROR_KEY;
	}

	public String getRESERVATION_PARSE_ERROR_KEY() {
		return RESERVATION_PARSE_ERROR_KEY;
	}

	public String getRESERVATION_TIME_ERROR_KEY() {
		return RESERVATION_TIME_ERROR_KEY;
	}
	
	
	
	
	
}
