package bll;

import java.util.List;

import bo.Reservation;
import dal.DALException;
import dal.ReservationDAO;

public class ReservationBLL {

	private ReservationDAO dao;
	
	//==============================================================
	
	public ReservationBLL() throws BLLException
	{
		
		try {
			this.dao = new ReservationDAO();
			
		} catch (DALException e) {
			
			throw new BLLException("BLL failed to connect to DAO", e);
		}
	}
	
	//==============================================================
	
	public List<Reservation> selectAll() throws BLLException {
		
		
		try {
			return dao.selectAll();
			
		} catch (DALException e) {
			throw new BLLException("Unable to recover datas from DAO", e)
		}
	}
	
	public Reservation selectById(int id) throws BLLException {
		try {
			return dao.selectById(id);
			
		} catch (DALException e) {

			throw new BLLException("Unable to recover the data from DAO", e);
		
		}
	}
	
	public Reservation insert(Reservation reservation) throws BLLException {
		// reservationTime pdt 
			
		
		// state
		
		
		// table
		
		
	
	}
	
}
