package bll;

import java.util.List;

import bo.Card;
import dal.CardDAO;
import dal.DALException;
import dal.DAOFactory;

public class CardBLL 
{
	private CardDAO dao;
	
	//======================================
	
	public CardBLL() throws BLLException 
	{
		try 
		{
			dao = DAOFactory.getCardDAO();
		} 
		catch (DALException e) 
		{
			throw new BLLException("Echec de la connexion", e);
		}
	}
	
	//======================================
	
	public List<Card> selectAll() throws BLLException 
	{
		
		try 
		{
			return dao.selectAll();
		} 
		catch (DALException e) 
		{
			throw new BLLException("Echec de la recuperation des cartes", e);
		}
	}
	
	//----------------------------------------
	
	public Card selectById(int id) throws BLLException {
		try {
			return dao.selectById(id);
		} catch (DALException e) {
			throw new BLLException("Echec de la recuperation de la carte d'id " + id, e);
		}
		
	}
	
	//----------------------------------------
	
	
	
}