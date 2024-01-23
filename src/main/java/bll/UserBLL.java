package bll;

import java.util.List;

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
		
		//email
		// regexEmail = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
		if(email.length() > EMAIL_MAX_LENGTH)
		{
			throw new BLLException("Email is too big", null);
					
		}
		
		if(email.length() < MIN_LENGTH)
		{
			throw new BLLException("Email name is too small", null);
			
		}
		
		//password
		//regexMdp = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9])(?!.*\s).{6,15}$/;
	    // To check a password between 6 to 15 characters which contain at least one lowercase letter, one uppercase letter, one numeric digit, and one special character
		if(password.length() > PASSWORD_MAX_LENGTH)
		{
			throw new BLLException("Password invalid", null);
					
		}
		
		if(password.length() < MIN_LENGTH)
		{
			throw new BLLException("Password invalid", null);
			
		}
		
		
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
		
	/*
	 * Ajouter des super exceptions
	 */
		
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
		
		/*
		 * Ajouter des super exceptions
		 */
		
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
}
