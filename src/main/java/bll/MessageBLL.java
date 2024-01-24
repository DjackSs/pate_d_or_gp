package bll;

import java.util.List;

import bo.Message;
import bo.Reservation;
import dal.DALException;
import dal.MessageDAO;

public class MessageBLL {
	private MessageDAO dao;
	
	private static final int OBJECT_MAX_LENGTH = 100;
	private static final int CONTENT_MAX_LENGTH = 250;
	private static final int MIN_LENGTH = 2;
	
	public MessageBLL() throws BLLException {
		try {
			dao = new MessageDAO();
		} catch (DALException error) {
			throw new BLLException("Echec de la connexion", error);
		}
	}
	
	//======================================
	
	public List<Message> selectAll() throws BLLException {
		/*
		 * Ajouter des super exceptions
		 */
		
		try {
			return dao.selectAll();
		} catch (DALException error) {
			throw new BLLException("Echec de la recuperation des messages", error);
		}
	}
	
	//======================================
	
	public Message selectById(int id) throws BLLException {
		try {
			return dao.selectById(id);
		} catch (DALException e) {
			throw new BLLException("Echec de la recuperation du message d'id " + id, e);
		}
	}
	
	//======================================
	
	public List<Message> selectMessageByIdUser(int userId) throws BLLException {
		
		try {
			return dao.selectMessageByKeyIdUser(userId);
		} catch (DALException error) {
			throw new BLLException("Echec de la recuperation des messages associés à l'utilisateur n°" + userId, error);
		}
	}
	
	//======================================
	
	public Message insert(String object, String content, int idUser) throws BLLException 
	{
//		BLLException bllException = new BLLException();
		
		//object
		if(object.trim().length() > OBJECT_MAX_LENGTH)
		{
			throw new BLLException("Email is too big", null);
					
		}
		
		if(object.trim().length() < MIN_LENGTH)
		{
			throw new BLLException("Email name is too small", null);
			
		}
		
		
		//content
		if(content.trim().length() > CONTENT_MAX_LENGTH)
		{
			throw new BLLException("Password is invalid", null);
					
		}
		
		if(content.trim().length() < MIN_LENGTH)
		{
			throw new BLLException("Password is invalid", null);
			
		}
		
		Message message = new Message(object, content, idUser);
		
		try 
		{
			dao.insert(message);
		} catch (DALException error) 
		{
			throw new BLLException("Echec de l'insertion", error);
		}
		return message;
	}
	
	//======================================
	
	public void update(Message message) throws BLLException 
	{
//		BLLException bllException = new BLLException();
		
		//object
		if(message.getObject().trim().length() > OBJECT_MAX_LENGTH)
		{
			throw new BLLException("Email is too big", null);
					
		}
		
		if(message.getObject().trim().length() < MIN_LENGTH)
		{
			throw new BLLException("Email name is too small", null);
			
		}
		
		
		//content
		if(message.getContent().trim().length() > CONTENT_MAX_LENGTH)
		{
			throw new BLLException("Password is invalid", null);
					
		}
		
		if(message.getContent().trim().length() < MIN_LENGTH)
		{
			throw new BLLException("Password is invalid", null);
			
		}
		
		try 
		{
			dao.update(message);
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
