package bo;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name= "Reservations")
public class Reservation 
{
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "reservation_time")
	private LocalDateTime reservationTime;
	private String state;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_table")
	private RestaurantTable tables;
	

	public Reservation() {}

	public Reservation(LocalDateTime reservationTime, String state) 
	{
		this.reservationTime = reservationTime;
		this.state = state;
		
	}
	
	public Reservation(LocalDateTime reservationTime, String state, RestaurantTable table) 
	{
		this.reservationTime = reservationTime;
		this.state = state;
		this.tables = table;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getReservationTime() {
		return reservationTime;
	}

	public void setReservationTime(LocalDateTime reservationTime) {
		this.reservationTime = reservationTime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public RestaurantTable getTables() {
		return tables;
	}

	public void setTables(RestaurantTable tables) {
		this.tables = tables;
	}

	
	
	

	
	

}
