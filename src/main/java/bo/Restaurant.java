package bo;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;	
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name= "Restaurants")
@NamedQueries
({
	@NamedQuery
	(
		name = "findRestaurantWithReservation",
		query = "select r from Restaurant r inner join r.tables t where t.id = :idTable" 
		//"select Restaurants.* from Restaurants inner join Tables on Restaurants.id = Tables.id_restaurant where Tables.id = ?;"
	)
})
public class Restaurant 
{
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	private String address;
	
	@Column(name = "postal_code")
	private String postalCode;
	
	private String town;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_card")
	private Card card;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_restaurant")
	private List<Schedule> schedules;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_restaurant")
	private List<RestaurantTable> tables;
	
	
	public Restaurant() 
	{
		this.schedules = new ArrayList<>();
		this.tables = new ArrayList<>();
	}
	
	public Restaurant(String name, String address, String postal, String town)
	{
		this.id =0;
		this.name = name;
		this.address = address;
		this.postalCode = postal;
		this.town = town;
		this.schedules = new ArrayList<>();
		this.tables = new ArrayList<>();
		
		
	}
	
	public Restaurant(String name, String address, String postal, String town, List<Schedule> schedules, List<RestaurantTable> tables)
	{
		this.id =0;
		this.name = name;
		this.address = address;
		this.postalCode = postal;
		this.town = town;
		this.schedules = schedules;
		this.tables = tables;
		
		
	}
	
	public void addSchedule(Schedule schedule)
	{
		this.schedules.add(schedule);
	}
	
	public void addTable(RestaurantTable table)
	{
		this.tables.add(table);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {

		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	
	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public List<Schedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<Schedule> schedules) {
		this.schedules = schedules;
	}

	public List<RestaurantTable> getTables() {
		return tables;
	}

	public void setTables(List<RestaurantTable> tables) {
		this.tables = tables;
	}
	
	

	
	
	
	
	
	
	
	
	

}
