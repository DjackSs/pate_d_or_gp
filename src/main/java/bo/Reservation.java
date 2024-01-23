package bo;

import java.time.LocalDate;

public class Reservation {
	private int id;
	private LocalDate reservationTime;
	private String state;
	
	private int idTable;
	private int idUser;
	
	

	public Reservation() {}

	public Reservation(LocalDate reservationTime, String state, int idTable, int idUser) {
		this.reservationTime = reservationTime;
		this.state = state;
		this.idTable = idTable;
		this.idUser = idUser;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getReservationTime() {
		return reservationTime;
	}

	public void setReservationTime(LocalDate reservationTime) {
		this.reservationTime = reservationTime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getIdTable() {
		return idTable;
	}

	public void setIdTable(int idTable) {
		this.idTable = idTable;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	
	

}
