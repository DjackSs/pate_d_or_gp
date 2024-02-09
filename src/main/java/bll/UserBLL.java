package bll;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.tomcat.jakartaee.commons.lang3.StringUtils;

import bo.Message;
import bo.Reservation;
import bo.Schedule;
import bo.User;
import dal.DALException;
import dal.UserDAO;

public class UserBLL 
{
	
	private static final int MIN_LENGTH = 2;
	
	//------------------user constants
	private static final int USER_NAME_MAX_LENGTH = 40;
	private static final int USER_LASTNAME_MAX_LENGTH = 40;
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
	
	public List<User> selectAll() throws BLLException 
	{
		
		try 
		{
			List<User> users =  dao.selectAll();
			
			for(User item : users)
			{
				this.erasePassword(item);
			}
			
			return users;
			
		} catch (DALException error) {
			throw new BLLException("Echec de la recuperation des utilisateurs", error);
		}
	}
	
	//----------------------------------------
	
	public User selectById(int id) throws BLLException 
	{
		try 
		{
			User user = dao.selectById(id);
			this.erasePassword(user);
			return user;
			
		} catch (DALException e) {
			throw new BLLException("Echec de la recuperation de l'utilisateur d'id " + id, e);
		}
	}
	
	//----------------------------------------
	
	
	public User selectByEmailAndPassword(String email, String password) throws BLLException
	{
		BLLException bll = new BLLException();
		
		//email
		if(StringUtils.isBlank(email))
		{
			bll.addError("emailSize", "Veuillez saisir une adresse mail");
		}
		
		
		
		//password
		if(StringUtils.isBlank(password))
		{
			bll.addError("password", "Mot de passe invalide");
				
		}
		
		
		if(bll.getErrors().size() != 0)
		{
			throw bll;
		}

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
		catch (DALException e)
		{
			bll.addError("password", "Mot de passe invalide");
			bll.addError("emailMatch", "Adresse mail invalide");
			throw bll;
			
		}
	}
	
	
	//----------------------------------------
	
	public User insert(User user) throws BLLException 
	{
		BLLException bll = new BLLException ();
		
		//name
		if(!StringUtils.isBlank(user.getName()))
		{
			if(user.getName().trim().length() > USER_NAME_MAX_LENGTH)
			{
				bll.addError("nameSize", "Votre prénom est trop long");
						
			}
			
			if(user.getName().trim().length() < MIN_LENGTH)
			{
				bll.addError("nameSize", "Votre prénom est trop court");
				
			}
			
		}
		else
		{
			bll.addError("nameSize", "Veuillez saisir un prénom");
		}
		
		
		//lastname
		if(!StringUtils.isBlank(user.getLastname()))
		{
			if(user.getLastname().trim().length() > USER_LASTNAME_MAX_LENGTH)
			{
				bll.addError("lastnameSize", "Votre nom est trop long");
						
			}
			
			if(user.getLastname().trim().length() < MIN_LENGTH)
			{
				bll.addError("lastnameSize", "Votre nom est trop court");
				
			}
			
		}
		else
		{
			bll.addError("lastnameSize", "Veuillez saisir un nom");
		}
		
		
		
		//email
		if(!StringUtils.isBlank(user.getEmail()))
		{
			if(user.getEmail().trim().length() > USER_EMAIL_MAX_LENGTH)
			{
				bll.addError("emailSize", "Votre adresse mail est trop longue");
						
			}
			
			if(user.getEmail().trim().length() < MIN_LENGTH)
			{
				bll.addError("emailSize", "Votre adresse mail est trop courte");
				
			}
			
			if(!this.regexMatche(user.getEmail(), EMAIL_REGEX))
			{
				bll.addError("emailMatch", "Votre adresse est invalide");
			}
			
		}
		else
		{
			bll.addError("emailSize", "Veuillez saisir une adresse mail");
		}
		
		
		//password
		if(!StringUtils.isBlank(user.getPassword()))
		{
			if(user.getPassword().trim().length() > USER_PASSWORD_MAX_LENGTH)
			{
				bll.addError("password", "Mot de passe invalide");
						
			}
			
			if(user.getPassword().trim().length() < MIN_LENGTH)
			{
				bll.addError("password", "Mot de passe invalide");
				
			}
			
			if(!this.regexMatche(user.getPassword(), PASSWORD_REGEX))
			{
				bll.addError("password", "Mot de passe invalide");
			}
				
		}
		else
		{
			bll.addError("password", "Mot de passe invalide");
			
		}
		
		
		if(bll.getErrors().size() != 0)
		{
			throw bll;
		}
		
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
	
	public Message insertMessages(Message message) throws BLLException
	{
		BLLException bll = new BLLException ();
		
		//object
		if(!StringUtils.isBlank(message.getObject()))
		{
			if(message.getObject().trim().length() > MESSAGE_OBJECT_MAX_LENGTH)
			{
				bll.addError("messageObject", "L'objet de votre message est trop long");
						
			}
			
			if(message.getObject().trim().length() < MIN_LENGTH)
			{
				bll.addError("messageObject", "L'objet de votre message est trop court");
				
			}
		}
		else
		{
			bll.addError("messageObject", "Veuillez saisir un objet pour votre message");
			
		}
		
		
		
		//content
		if(!StringUtils.isBlank(message.getContent()))
		{
			if(message.getContent().trim().length() > MESSAGE_CONTENT_MAX_LENGTH)
			{
				bll.addError("messageContent", "Le contenus de votre message est trop long");
						
			}
			
			if(message.getContent().trim().length() < MIN_LENGTH)
			{
				bll.addError("messageContent", "Le contenus de votre message est trop court");
				
			}
			
		}
		else
		{
			bll.addError("messageContent", "Veuillez saisir un contenu pour votre message");
		}
		
		
		if(bll.getErrors().size() != 0)
		{
			throw bll;
		}
		
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
	
	public Reservation insertReservation(Reservation reservation, List<Schedule> schedules) throws BLLException
	{
		try
		{
			this.controleReservation(reservation, schedules);
			
			this.dao.insertReservation(reservation);
			
		}
		catch(DALException error)
		{
			throw new BLLException("Echec de l'insertion de la réservation", error);
			
		}
		
		
		return reservation;
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
			throw new BLLException("Echec du cryptage du mot de passe", error);
		}
		
		return message;

		
	}
	
	//======================================
	
	public void update(User user) throws BLLException 
	{
		
		BLLException bll = new BLLException ();
		
		User oldUser = this.selectById(user.getId());
		
		//name
		if(StringUtils.isBlank(user.getName()))		{
			user.setName(oldUser.getName());
		}
		
		//lastname
		if(StringUtils.isBlank(user.getLastname()))
		{
			user.setLastname(oldUser.getLastname());
		}
		
		//email
		if(StringUtils.isBlank(user.getEmail()))
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
		if(StringUtils.isBlank(user.getPassword()))
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
	
	private void controleReservation(Reservation reservation, List<Schedule> schedules) throws BLLException
	{
		boolean include = false;
		
		for(Schedule schedule : schedules)
		{
			if(reservation.getReservationTime().toLocalTime().isAfter(schedule.getOpenHour()) && reservation.getReservationTime().toLocalTime().isBefore(schedule.getCloseHour()))
			{
				include = true;
			}
			
		}
		
		if(include != true)
		{
			throw new BLLException("Reservation time is invalide", null);
		}
			
		
	}
}
