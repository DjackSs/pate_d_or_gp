package bll;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

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
			throw new BLLException("Echec de la récuperation des utilisateurs", error);
		}
	}
	
	//----------------------------------------
	
	public User selectById(int id) throws BLLException 
	{
		try 
		{
			return dao.selectById(id);
			
		} catch (DALException e) {
			throw new BLLException("Echec de la récuperation de l'utilisateur d'id " + id, e);
		}
	}
	
	//----------------------------------------
	
	
	public User selectByEmailAndPassword(String email, String password) throws BLLException
	{
		BLLException bll = new BLLException();
		
		//email
		if(StringUtils.isBlank(email))
		{
			bll.addError(bll.getUSER_EMAIL_SIZE_ERROR_KEY(), "Veuillez saisir une adresse mail");
		}
		

		//password
		if(StringUtils.isBlank(password))
		{
			bll.addError(bll.getUSER_PASSWORD_ERROR_KEY(), "Mot de passe invalide");
				
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
				bll.addError(bll.getUSER_NAME_SIZE_ERROR_KEY(), "Votre prénom est trop long");
						
			}
			
			if(user.getName().trim().length() < MIN_LENGTH)
			{
				bll.addError(bll.getUSER_NAME_SIZE_ERROR_KEY(), "Votre prénom est trop court");
				
			}
			
		}
		else
		{
			bll.addError(bll.getUSER_NAME_SIZE_ERROR_KEY(), "Veuillez saisir un prénom");
		}
		
		
		//lastname
		if(!StringUtils.isBlank(user.getLastname()))
		{
			if(user.getLastname().trim().length() > USER_LASTNAME_MAX_LENGTH)
			{
				bll.addError(bll.getUSER_LASTNAME_SIZE_ERROR_KEY(), "Votre nom est trop long");
						
			}
			
			if(user.getLastname().trim().length() < MIN_LENGTH)
			{
				bll.addError(bll.getUSER_LASTNAME_SIZE_ERROR_KEY(), "Votre nom est trop court");
				
			}
			
		}
		else
		{
			bll.addError(bll.getUSER_LASTNAME_SIZE_ERROR_KEY(), "Veuillez saisir un nom");
		}
		
		
		
		//email
		if(!StringUtils.isBlank(user.getEmail()))
		{
			if(user.getEmail().trim().length() > USER_EMAIL_MAX_LENGTH)
			{
				bll.addError(bll.getUSER_EMAIL_SIZE_ERROR_KEY(), "Votre adresse mail est trop longue");
						
			}
			
			if(user.getEmail().trim().length() < MIN_LENGTH)
			{
				bll.addError(bll.getUSER_EMAIL_SIZE_ERROR_KEY(), "Votre adresse mail est trop courte");
				
			}
			
			if(!this.regexMatche(user.getEmail(), EMAIL_REGEX))
			{
				bll.addError(bll.getUSER_EMAIL_SIZE_ERROR_KEY(), "Votre adresse est invalide");
			}
			
		}
		else
		{
			bll.addError(bll.getUSER_EMAIL_SIZE_ERROR_KEY(), "Veuillez saisir une adresse mail");
		}
		
		
		//password
		if(!StringUtils.isBlank(user.getPassword()))
		{
			if(user.getPassword().trim().length() > USER_PASSWORD_MAX_LENGTH)
			{
				bll.addError(bll.getUSER_PASSWORD_ERROR_KEY(), "Mot de passe invalide");
						
			}
			
			if(user.getPassword().trim().length() < MIN_LENGTH)
			{
				bll.addError(bll.getUSER_PASSWORD_ERROR_KEY(), "Mot de passe invalide");
				
			}
			
			if(!this.regexMatche(user.getPassword(), PASSWORD_REGEX))
			{
				bll.addError(bll.getUSER_PASSWORD_ERROR_KEY(), "Mot de passe invalide");
			}
				
		}
		else
		{
			bll.addError(bll.getUSER_PASSWORD_ERROR_KEY(), "Mot de passe invalide");
			
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
				
				//gestion du doublon
				try
				{
					this.dao.selectByEmailAndPassword(user.getEmail(), hashedPassword);
					bll.addError(bll.getUSER_EMAIL_SIZE_ERROR_KEY(), "Un utilisateur possède déja ces identifiants. Changer votre adresse mail ou votre mots de passe");
					throw bll;
				}
				catch (DALException e)
				{
					user.setPassword(hashedPassword);	
					dao.insert(user);
					
				}
	
			
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
		BLLException bll = new BLLException ();
		
		//object
		if(!StringUtils.isBlank(message.getObject()))
		{
			if(message.getObject().trim().length() > MESSAGE_OBJECT_MAX_LENGTH)
			{
				bll.addError(bll.getMESSAGE_OBJECT_ERROR_KEY(), "L'objet de votre message est trop long");
						
			}
			
			if(message.getObject().trim().length() < MIN_LENGTH)
			{
				bll.addError(bll.getMESSAGE_OBJECT_ERROR_KEY(), "L'objet de votre message est trop court");
				
			}
		}
		else
		{
			bll.addError(bll.getMESSAGE_OBJECT_ERROR_KEY(), "Veuillez saisir un objet pour votre message");
			
		}
		
		
		
		//content
		if(!StringUtils.isBlank(message.getContent()))
		{
			if(message.getContent().trim().length() > MESSAGE_CONTENT_MAX_LENGTH)
			{
				bll.addError(bll.getMESSAGE_CONTENT_ERROR_KEY(), "Le contenu de votre message est trop long");
						
			}
			
			if(message.getContent().trim().length() < MIN_LENGTH)
			{
				bll.addError(bll.getMESSAGE_CONTENT_ERROR_KEY(), "Le contenu de votre message est trop court");
				
			}
			
		}
		else
		{
			bll.addError(bll.getMESSAGE_CONTENT_ERROR_KEY(), "Veuillez saisir un contenu pour votre message");
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
	
	public Reservation insertReservation(String date, String time, List<Schedule> schedules) throws BLLException 
	{
		BLLException bll = new BLLException();
		
		Reservation reservation = null;	

		if(StringUtils.isBlank(date))
		{
			
			bll.addError(bll.getRESERVATION_DATE_ERROR_KEY(), "Veuillez saisir une date de réservation");
		}
		
		if(StringUtils.isBlank(time))
		{
			bll.addError(bll.getRESERVATION_HOUR_ERROR_KEY(), "Veuillez saisir une heure de réservation");
		}
		
		
		if(!StringUtils.isBlank(date) && !StringUtils.isBlank(time))
		{
			
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
				String ReservationDateTimeString = date + "T" + time + ":00";
				
			try
			{
				LocalDateTime ReservationDateTime = LocalDateTime.parse(ReservationDateTimeString, formatter);
				
				if(!this.controleReservation(ReservationDateTime, schedules))
				{
					bll.addError(bll.getRESERVATION_TIME_ERROR_KEY(), "Veuillez respectez le(s) creneau(x) horaire(s) du restaurant");
				}
					
				if(ReservationDateTime.toLocalDate().isBefore(LocalDate.now()))
				{
					bll.addError(bll.getRESERVATION_DAY_ERROR_KEY(), "Veuillez choisir une date qui n'est pas passée");
				}
				

				if(bll.getErrors().size() != 0)
				{
					throw bll;
				}
				
				reservation = new Reservation(ReservationDateTime, "hold");
				
			
				this.dao.insertReservation(reservation);
					
	
				return reservation;
				
			}
			catch(DALException error)
			{
				throw new BLLException("Echec de l'insertion de la réservation", error);
				
			}
			catch(DateTimeParseException e)
			{
				bll.addError(bll.getRESERVATION_PARSE_ERROR_KEY(), "Mauvais formas de la date ou de l'heure");
			}
			
			
			
		}
		
		if(bll.getErrors().size() != 0)
		{
			throw bll;
		}
			
		
		return reservation;
		
	}
	
	
	
	//======================================
	
	public User update(User user) throws BLLException 
	{
		
		BLLException bll = new BLLException();
		User oldUser = this.selectById(user.getId());
		
		
		//name
		if(StringUtils.isBlank(user.getName()))		
		{
			user.setName(oldUser.getName());
		}
		else
		{
			if(user.getName().trim().length() > USER_NAME_MAX_LENGTH)
			{
				bll.addError(bll.getUSER_NAME_SIZE_ERROR_KEY(), "Votre prénom est trop long");
						
			}
			
			if(user.getName().trim().length() < MIN_LENGTH)
			{
				bll.addError(bll.getUSER_NAME_SIZE_ERROR_KEY(), "Votre prénom est trop court");
				
			}
			
		}
		
		//lastname
		if(StringUtils.isBlank(user.getLastname()))
		{
			user.setLastname(oldUser.getLastname());
		}
		else
		{
			if(user.getLastname().trim().length() > USER_LASTNAME_MAX_LENGTH)
			{
				bll.addError(bll.getUSER_LASTNAME_SIZE_ERROR_KEY(), "Votre nom est trop long");
						
			}
			
			if(user.getLastname().trim().length() < MIN_LENGTH)
			{
				bll.addError(bll.getUSER_LASTNAME_SIZE_ERROR_KEY(), "Votre nom est trop court");
				
			}
			
		}
		
		//email
		if(StringUtils.isBlank(user.getEmail()))
		{
			user.setEmail(oldUser.getEmail());
		}
		else
		{
			if(StringUtils.isBlank(user.getPassword()) && !user.getEmail().equals(oldUser.getEmail()))
			{
				bll.addError(bll.getUSER_EMAIL_MATCH_ERROR_KEY(), "Entrez votre mot de passe pour modifier votre adresse mail");
				
			}
			
			if(user.getEmail().trim().length() > USER_EMAIL_MAX_LENGTH)
			{
				bll.addError(bll.getUSER_EMAIL_SIZE_ERROR_KEY(), "Votre adresse mail est trop longue");
						
			}
			
			if(user.getEmail().trim().length() < MIN_LENGTH)
			{
				bll.addError(bll.getUSER_EMAIL_SIZE_ERROR_KEY(), "Votre adresse mail est trop courte");
				
			}
			
			if(!this.regexMatche(user.getEmail(), EMAIL_REGEX))
			{
				bll.addError(bll.getUSER_EMAIL_MATCH_ERROR_KEY(), "Votre adresse est invalide");
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
				bll.addError(bll.getUSER_PASSWORD_ERROR_KEY(), "Mot de passe invalide");
						
			}
			
			if(user.getPassword().trim().length() < MIN_LENGTH)
			{
				bll.addError(bll.getUSER_PASSWORD_ERROR_KEY(), "Mot de passe invalide");
				
			}
			
			if(!this.regexMatche(user.getPassword(), PASSWORD_REGEX))
			{
				bll.addError(bll.getUSER_PASSWORD_ERROR_KEY(), "Mot de passe invalide");
			}
		}
		
		
		if(bll.getErrors().size() != 0)
		{
			throw bll;
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
		
		this.erasePassword(user);
		
		return user;
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
		return key.getBytes();
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
	
	private boolean controleReservation(LocalDateTime reservationTime, List<Schedule> schedules)
	{
		boolean include = false;
		
		for(Schedule schedule : schedules)
		{
			if(reservationTime.toLocalTime().isAfter(schedule.getOpenHour()) && reservationTime.toLocalTime().isBefore(schedule.getCloseHour()))
			{
				include = true;
			}
			
		}
		
		return include;
			
	}
}
