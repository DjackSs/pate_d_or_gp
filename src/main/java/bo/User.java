package bo;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name= "Users")
@NamedQueries
({
	@NamedQuery
	(
		name = "findUser",
		query = "select u from User u where u.email like :email and u.password like :password" //"SELECT * FROM users WHERE Users.email = ? and Users.password = ?"
		
	)
})
public class User 
{
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	private String lastname;
	private String email;
	private String password;
	private String role;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_user")
	private List<Message> messages;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_user")
	private List<Reservation> reservations;
	
	public User() 
	{
		this.messages = new ArrayList<>();
		this.reservations = new ArrayList<>();
	}

	public User(String name, String lastname, String email, String password, String role) 
	{
		this.name = name;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.role = role;
		this.messages = new ArrayList<>();
		this.reservations = new ArrayList<>();
	}
	
	public User(String name, String lastname, String email, String password, String role, List<Message> messages, List<Reservation> reservations) 
	{
		this.name = name;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.role = role;
		this.messages = messages;
		this.reservations = reservations;
	}
	
	public void addMessage(Message message)
	{
		this.messages.add(message);
	}
	
	public void addReservation(Reservation reservation)
	{
		this.reservations.add(reservation);
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

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	@Override
	public String toString() 
	{
		return "User [id=" + id + ", name=" + name + ", lastname=" + lastname + ", email=" + email + ", password="
				+ password + ", role=" + role + ", messages=" + messages.size() + ", reservations=" + reservations.size() + "]";
	}
	
	

	
	
	
	
	
}
