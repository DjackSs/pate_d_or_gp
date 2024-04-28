package bll;

public class BLLFactory 
{
	private static UserBLL userBLL;
	private static CardBLL cardBLL;
	private static RestaurantBLL restaurantBLL;
	
	private BLLFactory() {}
	
	public static UserBLL getUserBLL() throws BLLException
	{
		if(userBLL == null) userBLL = new UserBLL();
		
		return userBLL;
	}
	
	public static CardBLL getCardBLL() throws BLLException
	{
		if(cardBLL == null) cardBLL = new CardBLL();
		
		return cardBLL;
	}
	
	public static RestaurantBLL getRestaurantBLL() throws BLLException
	{
		if(restaurantBLL == null) restaurantBLL = new RestaurantBLL();
		
		return restaurantBLL;
	}

}
