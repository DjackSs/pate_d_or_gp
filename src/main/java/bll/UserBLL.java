package bll;

import java.util.List;

import bo.User;
import dal.DALException;
import dal.UserDAO;

public class UserBLL {
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
