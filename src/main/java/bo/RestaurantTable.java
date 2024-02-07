package bo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Table(name= "Tables")
public class RestaurantTable 
{
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "number_place", nullable = false)
	private int numberPlace;
	private String state;
	
	
	public RestaurantTable() {}


	public RestaurantTable(int numberPlace, String state) 
	{
		this.numberPlace = numberPlace;
		this.state = state;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getNumberPlace() {
		return numberPlace;
	}


	public void setNumberPlace(int numberPlace) {
		this.numberPlace = numberPlace;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	
	
	
}
