package bll;

import java.util.List;

import bo.Restaurant;
import dal.DALException;
import dal.RestaurantDAO;

public class RestaurantBLL 
{
	private RestaurantDAO dao;
	
	//==============================================================
	
	public RestaurantBLL() throws BLLException
	{
		
		try
		{
			this.dao = new RestaurantDAO();
			
		}
		catch (DALException error)
		{
			throw new BLLException("BLL failed to connect to DAO",error);
			
		}
		
	}
	
	//==============================================================
	
	public List<Restaurant> selectAll() throws BLLException
	{

		try 
		{
			return dao.selectAll();
			
		} 
		catch (DALException error)
		{

			throw new BLLException("Unable to recover datas from DAO",error);
		}
		
	}
	
	//--------------------------------------------------------------
	
	public Restaurant selectById(int id) throws BLLException
	{
		
		try 
		{
			return dao.selectById(id);
		} 
		catch (DALException error)
		{
			
			throw new BLLException("Unable to recover the data from the DAO",error);
		}
		
	}
	
	//--------------------------------------------------------------
	
	public Restaurant selectByReservation(int id) throws BLLException
	{
		
		try
		{
				
			return dao.selectByReservation(id);
			
		}
		catch (DALException e) 
		{
			throw new BLLException("Echec de la recuperation des restaurant associé à la reservation ", e);
		}
		
	
	}
	


}
