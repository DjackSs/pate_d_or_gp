package bo;

import java.time.LocalDate;

public class ReservationWithRestaurant extends Reservation {
    private String restaurantName;

    public ReservationWithRestaurant(int id, LocalDate reservationTime, String state, int idTable, int idUser, String restaurantName) {
        super(reservationTime, state, idTable, idUser);
        this.restaurantName = restaurantName;
    }
    
	public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }
}
