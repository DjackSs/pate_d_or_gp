package dal;

public class DAOFactory 
{
	private static UserDAO userDAO;
	private static CardDAO cardDAO;
	private static RestaurantDAO restaurantDAO;
	
	public static UserDAO getUserDAO() throws DALException
	{
		if(userDAO == null) userDAO = new UserDAO();
		
		return userDAO;
	}
	
	public static CardDAO getCardDAO() throws DALException
	{
		if(cardDAO == null) cardDAO = new CardDAO();
		
		return cardDAO;
	}
	
	public static RestaurantDAO getRestaurantDAO() throws DALException
	{
		if(restaurantDAO == null) restaurantDAO = new RestaurantDAO();
		
		return restaurantDAO;
	}

}
