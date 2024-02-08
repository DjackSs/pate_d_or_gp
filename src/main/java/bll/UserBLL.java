package bll;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.regex.Pattern;

import bo.Message;
import bo.Reservation;
import bo.User;
import dal.DALException;
import dal.UserDAO;

public class UserBLL 
{
	
	private static final int MIN_LENGTH = 2;
	
	//------------------user constants
	private static final int USER_EMAIL_MAX_LENGTH = 60;
	private static final int USER_PASSWORD_MAX_LENGTH = 60;
	private static final String EMAIL_REGEX = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$";
	//The password must contain at least one lowercase character, one uppercase character, one digit, one special character, and a length between 4 to 20.
	//https://mkyong.com/regular-expressions/how-to-validate-password-with-regular-expression/
	private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{4,20}$";
	
	//------------------message constants
	private static final int MESSAGE_OBJECT_MAX_LENGTH = 100;
	private static final int MESSAGE_CONTENT_MAX_LENGTH = 250;
	
	private UserDAO dao;
	
	//======================================
	
	public UserBLL() throws BLLException 
	{
		try {
			dao = new UserDAO();
		} catch (DALException error) {
			throw new BLLException("Echec de la connexion", error);
		}
	}
	
	//======================================
	
	public List<User> selectAll() throws BLLException {
		
		try {
			return dao.selectAll();
		} catch (DALException error) {
			throw new BLLException("Echec de la recuperation des utilisateurs", error);
		}
	}
	
	//----------------------------------------
	
	public User selectById(int id) throws BLLException {
		try {
			return dao.selectById(id);
		} catch (DALException e) {
			throw new BLLException("Echec de la recuperation de l'utilisateur d'id " + id, e);
		}
	}
	
	//----------------------------------------
	
	
	public User selectByEmailAndPassword(String email, String password) throws BLLException 
	{
		
		try 
		{
			//hashing the password
			byte[] salt = this.getSalt(email);
			String hashedPassword = this.toHash(password, salt);
			
			User user = dao.selectByEmailAndPassword(email, hashedPassword);
			
			this.erasePassword(user);
	
			return user;
		} 
		catch (NoSuchAlgorithmException e) 
		{
			throw new BLLException("Echec du cryptage du mot de passe", e);
		}
	}
	
	
	//----------------------------------------
	
	public User insert(User user) throws BLLException 
	{
		
		
		//email
		if(user.getEmail().trim().length() > USER_EMAIL_MAX_LENGTH)
		{
			throw new BLLException("Email is too big", null);
					
		}
		
		if(user.getEmail().trim().length() < MIN_LENGTH)
		{
			throw new BLLException("Email name is too small", null);
			
		}
		
		
		
		if(!this.regexMatche(user.getEmail(), EMAIL_REGEX))
		{
			throw new BLLException("email is invalid", null);
		}
		
		
		
		//password
		if(user.getPassword().trim().length() > USER_PASSWORD_MAX_LENGTH)
		{
			throw new BLLException("Password is invalid", null);
					
		}
		
		if(user.getPassword().trim().length() < MIN_LENGTH)
		{
			throw new BLLException("Password is invalid", null);
			
		}
		
		if(!this.regexMatche(user.getPassword(), PASSWORD_REGEX))
		{
			throw new BLLException("Password is invalid", null);
		}
		
		//messages
		this.controleMessages(user.getMessages());
		
		//reservations
		this.controleReservations(user.getReservations());
		
		//role
		user.setRole("cust");
		
		try 
		{
			//hashing the password
			byte[] salt = this.getSalt(user.getEmail());
			String hashedPassword = this.toHash(user.getPassword(), salt);
			
			user.setPassword(hashedPassword);
			
			
			dao.insert(user);
			
		} 
		catch (DALException error) 
		{
			throw new BLLException("Echec de l'insertion", error);
		}
		catch (NoSuchAlgorithmException e) 
		{
			throw new BLLException("Echec du cryptage du mot de passe", e);
		}
		
		this.erasePassword(user);
		
		return user;
	}
	
	//----------------------------------------
	
	public Message insertMessage(Message message) throws BLLException
	{
		try
		{
			this.dao.insertMessage(message);
		}
		catch(DALException error)
		{
			throw new BLLException("Echec de l'insertion du message", error);
		}
		
		return message;
		
	}
	
	//----------------------------------------
	
	public Reservation insertReservation(Reservation reservation) throws BLLException
	{
		try
		{
			this.dao.insertReservation(reservation);
			
		}
		catch(DALException error)
		{
			throw new BLLException("Echec de l'insertion de la réservation", error);
			
		}
		
		
		return reservation;
	}
	
	//----------------------------------------
	
	public void update(User user) throws BLLException 
	{
		
		User oldUser = this.selectById(user.getId());
		
		//name
		if(user.getName().isBlank() || user.getName() == null)
		{
			user.setName(oldUser.getName());
		}
		
		//lastname
		if(user.getLastname().isBlank() || user.getLastname() == null )
		{
			user.setLastname(oldUser.getLastname());
		}
		
		//email
		if(user.getEmail().isBlank() || user.getEmail() == null)
		{
			user.setEmail(oldUser.getEmail());
		}
		else
		{
			if(user.getEmail().trim().length() > USER_EMAIL_MAX_LENGTH)
			{
				throw new BLLException("Email is too big", null);
						
			}
			
			if(user.getEmail().trim().length() < MIN_LENGTH)
			{
				throw new BLLException("Email name is too small", null);
				
			}
			if(!this.regexMatche(user.getEmail(), EMAIL_REGEX))
			{
				throw new BLLException("email is invalid", null);
			}		
		}
		
		//password
		if(user.getPassword().isBlank() || user.getPassword() == null)
		{
			user.setPassword(oldUser.getPassword());
		}
		else
		{
			if(user.getPassword().trim().length() > USER_PASSWORD_MAX_LENGTH)
			{
				throw new BLLException("Password is invalid", null);
						
			}
			
			if(user.getPassword().trim().length() < MIN_LENGTH)
			{
				throw new BLLException("Password is invalid", null);
				
			}
			
			if(!this.regexMatche(user.getPassword(), PASSWORD_REGEX))
			{
				throw new BLLException("Password is invalid", null);
			}
			
		}
		
		//messages
		this.controleMessages(user.getMessages());
		
		//reservations
		this.controleReservations(user.getReservations());
		
			
		
		try 
		{
			if(!oldUser.getEmail().equals(user.getEmail()) || !user.getPassword().equals(oldUser.getPassword()))
			{
				//hashing the password
				byte[] salt = this.getSalt(user.getEmail());
				String hashedPassword = this.toHash(user.getPassword(), salt);
				
				user.setPassword(hashedPassword);
			}
			
			dao.update(user);
			
			this.erasePassword(user);
			
		} 
		catch (DALException error) 
		{
			throw new BLLException("Echec de la mise a jour", error);
		}
		catch (NoSuchAlgorithmException e) 
		{
			throw new BLLException("Echec du cryptage du mot de passe", e);
		}
	}
	
	//----------------------------------------
	
	public void delete(User user) throws BLLException 
	{
		try 
		{
			dao.delete(user);
		} 
		catch (DALException e) 
		{
			throw new BLLException("Echec de la suppression", e);
		}
	}
	
	//----------------------------------------
	
	//regex test method
	private boolean regexMatche(String test, String regexPattern)
	{
		return Pattern.compile(regexPattern).matcher(test).matches();
	}
	
	//----------------------------------------
	
	//salt generator with a key
	private byte[] getSalt(String key)
	{
		//SecureRandom rand = new SecureRandom();
		byte[] salt = key.getBytes();
		//rand.nextBytes(salt);
		
		return salt;
		
	}
	
	//----------------------------------------
	
	
	private String toHash(String mdp, byte[] salt) throws NoSuchAlgorithmException
	{
		try
		{
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			 md.update(salt);
			 
			 byte[] bytes = md.digest(mdp.getBytes(StandardCharsets.UTF_8));
			 
			 StringBuilder sb = new StringBuilder();
	            
            for (int i = 0; i < bytes.length; i++) 
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            
            mdp = sb.toString();
 	
		}
		catch (NoSuchAlgorithmException e) 
		{
			
			e.printStackTrace();
		}
		
		return mdp;	

	}
	
	//----------------------------------------
	
	private void erasePassword(User user)
	{
		//erase the password so it's never send to clientside
		 user.setPassword("");
	}
	
	//----------------------------------------
	
	private void controleMessages(List<Message> messages) throws BLLException
	{
		for(Message message : messages)
		{
			//object
			if(message.getObject().trim().length() > MESSAGE_OBJECT_MAX_LENGTH)
			{
				throw new BLLException("message's object is too big", null);
						
			}
			
			if(message.getObject().trim().length() < MIN_LENGTH)
			{
				throw new BLLException("message's object name is too small", null);
				
			}
			
			
			//content
			if(message.getContent().trim().length() > MESSAGE_CONTENT_MAX_LENGTH)
			{
				throw new BLLException("message's content is too big", null);
						
			}
			
			if(message.getContent().trim().length() < MIN_LENGTH)
			{
				throw new BLLException("message's content is too small", null);
				
			}
		}
	}
	
	//----------------------------------------
	
	private void controleReservations(List<Reservation> reservations) throws BLLException
	{
		for(Reservation reservation : reservations)
		{
			//ajouter des controles
		}
	}
}
