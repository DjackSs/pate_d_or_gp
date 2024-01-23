package bll;

import java.time.LocalDate;
import java.util.List;

import bo.Reservation;
import dal.DALException;
import dal.ReservationDAO;

public class ReservationBLL {
	private ReservationDAO dao;
	
	public ReservationBLL() throws BLLException {
		try {
			dao = new ReservationDAO();
		} catch (DALException error) {
			throw new BLLException("Echec de la connexion", error);
		}
	}
	
	//======================================
	
	public List<Reservation> selectAll() throws BLLException {
		/*
		 * Ajouter des super exceptions
		 */
		
		try {
			return dao.selectAll();
		} catch (DALException error) {
			throw new BLLException("Echec de la recuperation des reservations", error);
		}
	}
	
	//======================================
	
	public Reservation selectById(int id) throws BLLException {
		try {
			return dao.selectById(id);
		} catch (DALException e) {
			throw new BLLException("Echec de la recuperation de la reservation d'id " + id, e);
		}
	}
	
	//======================================
	
	public List<Reservation> selectReservationByIdUser(int idUser) throws BLLException {
		
		try {
			return dao.selectByKeyIdUser(idUser);
		} catch (DALException error) {
			throw new BLLException("Echec de la recuperation des utilisateurs associés aux réservations n°" + idUser, error);
		}
	}
	//======================================
	
	public List<Reservation> selectReservationByIdTable(int idTable) throws BLLException {
		
		try {
			return dao.selectByKeyIdTable(idTable);
		} catch (DALException error) {
			throw new BLLException("Echec de la recuperation des tables associés aux réservations" + idTable, error);
		}
	}
	
	//======================================
	
	public Reservation insert(LocalDate reservationTime, String state, int idTable, int idUser) throws BLLException {
//		BLLException bllException = new BLLException();
		
	/*
	 * Ajouter des super exceptions
	 */
		
		Reservation reservation = new Reservation(reservationTime, state, idTable, idUser);
		try {
			dao.insert(reservation);
		} catch (DALException error) {
			throw new BLLException("Echec de l'insertion", error);
		}
		return reservation;
	}
	
	//======================================
	
	public void update(Reservation reservation) throws BLLException {
//		BLLException bllException = new BLLException();
		
		/*
		 * Ajouter des super exceptions
		 */
		
		try {
			dao.update(reservation);
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
