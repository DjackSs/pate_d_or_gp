package bll;

import java.time.LocalDate;
import java.util.List;

import bo.Message;
import dal.DALException;
import dal.MessageDAO;

public class MessageBLL {
	private MessageDAO dao;
	
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
	
	public Message insert(String object, String content, int idUser) throws BLLException {
//		BLLException bllException = new BLLException();
		
	/*
	 * Ajouter des super exceptions
	 */
		
		Message message = new Message(object, content, idUser);
		try {
			dao.insert(message);
		} catch (DALException error) {
			throw new BLLException("Echec de l'insertion", error);
		}
		return message;
	}
	
	//======================================
	
	public void update(Message message) throws BLLException {
//		BLLException bllException = new BLLException();
		
		/*
		 * Ajouter des super exceptions
		 */
		
		try {
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
