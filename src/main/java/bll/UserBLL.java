package bll;

import java.util.List;
import java.util.regex.Pattern;

import bo.User;
import dal.DALException;
import dal.UserDAO;

public class UserBLL 
{
	private static final int EMAIL_MAX_LENGTH = 60;
	private static final int PASSWORD_MAX_LENGTH = 60;
	private static final int MIN_LENGTH = 2;
	
	private UserDAO dao;
	
	public UserBLL() throws BLLException {
		try {
			dao = new UserDAO();
		} catch (DALException error) {
			throw new BLLException("Echec de la connexion", error);
		}
	}
	
	//======================================
	
	public List<User> selectAll() throws BLLException {
		/*
		 * Ajouter des super exceptions
		 */
		
		try {
			return dao.selectAll();
		} catch (DALException error) {
			throw new BLLException("Echec de la recuperation des utilisateurs", error);
		}
	}
	
	//======================================
	
	public User selectById(int id) throws BLLException {
		try {
			return dao.selectById(id);
		} catch (DALException e) {
			throw new BLLException("Echec de la recuperation de l'utilisateur d'id " + id, e);
		}
	}
	
	//======================================
	
	public User selectByEmailAndPassword(String email, String password) throws BLLException 
	{
		//xss security check
		
		try 
		{
			return dao.selectByEmailAndPassword(email, password);
		} 
		catch (DALException e) 
		{
			throw new BLLException("Echec de l'autentification ", e);
		}
	}
	
	//======================================
	
	public User insert(String name, String lastname, String email, String password, String role) throws BLLException {
//		BLLException bllException = new BLLException();
		
		//xss security check
		
		//email
		if(email.length() > EMAIL_MAX_LENGTH)
		{
			throw new BLLException("Email is too big", null);
					
		}
		
		if(email.length() < MIN_LENGTH)
		{
			throw new BLLException("Email name is too small", null);
			
		}
		
		String regex1 = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$";
		
		if(!this.regexMatche(email, regex1))
		{
			throw new BLLException("email is invalid", null);
		}
		
		
		
		//password
		if(password.length() > PASSWORD_MAX_LENGTH)
		{
			throw new BLLException("Password is invalid", null);
					
		}
		
		if(password.length() < MIN_LENGTH)
		{
			throw new BLLException("Password is invalid", null);
			
		}
		
		//The password must contain at least one lowercase character, one uppercase character, one digit, one special character, and a length between 4 to 20.
		//https://mkyong.com/regular-expressions/how-to-validate-password-with-regular-expression/
		String regex2 = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{4,20}$";
		
		if(!this.regexMatche(password, regex2))
		{
			throw new BLLException("Password is invalid", null);
		}
		
		
		
		User user = new User(name, lastname, email, password, role);
		
		try {
			dao.insert(user);
		} catch (DALException error) {
			throw new BLLException("Echec de l'insertion", error);
		}
		return user;
	}
	
	//======================================
	
	public void update(User user) throws BLLException {
//		BLLException bllException = new BLLException();
		
		//xss security check
		
		
		//email
		if(user.getEmail().length() > EMAIL_MAX_LENGTH)
		{
			throw new BLLException("Email is too big", null);
					
		}
		
		if(user.getEmail().length() < MIN_LENGTH)
		{
			throw new BLLException("Email name is too small", null);
			
		}
		
		String regex1 = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$";
		
		if(!this.regexMatche(user.getEmail(), regex1))
		{
			throw new BLLException("email is invalid", null);
		}
		
		
		
		//password
		if(user.getPassword().length() > PASSWORD_MAX_LENGTH)
		{
			throw new BLLException("Password is invalid", null);
					
		}
		
		if(user.getPassword().length() < MIN_LENGTH)
		{
			throw new BLLException("Password is invalid", null);
			
		}
		
		//The password must contain at least one lowercase character, one uppercase character, one digit, one special character, and a length between 4 to 20.
		//https://mkyong.com/regular-expressions/how-to-validate-password-with-regular-expression/
		String regex2 = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{4,20}$";
		
		if(!this.regexMatche(user.getPassword(), regex2))
		{
			throw new BLLException("Password is invalid", null);
		}
				
		
		try {
			dao.update(user);
		} catch (DALException error) {
			throw new BLLException("Echec de la mise a jour", error);
		}
	}
	
	//======================================
	
	public void delete(int id) throws BLLException {
		try {
			dao.delete(id);
		} catch (DALException e) {
			throw new BLLException("Echec de la suppression", e);
		}
	}
	
	//======================================
	
	//regex test method
	public boolean regexMatche(String test, String regexPattern)
	{
		return Pattern.compile(regexPattern).matcher(test).matches();
	}
}
