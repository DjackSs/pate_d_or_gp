package dal.daointerface;

import bo.Message;
import bo.Reservation;
import dal.DALException;

public interface UserDAOInterface<T> extends GenericDAOInterface<T>
{
	T selectByEmailAndPassword(String email, String password) throws DALException;
	
	void insert(T objet) throws DALException;
	
	void insertMessage(Message objet) throws DALException;
	
	void insertReservation(Reservation objet) throws DALException;
	
	void update(T objet) throws DALException;
	
	void delete(T objet) throws DALException;

}
