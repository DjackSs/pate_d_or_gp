package vo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import bo.Reservation;

public class ReservationVO {
	private int id;
	private String reservationTime;
	private String state;
	
	private int idTable;
	private int idUser;
	
	private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm");

	public ReservationVO() {}
	
	public ReservationVO(Reservation reservation) {
		this.reservationTime = reservation.getReservationTime().format(TIME_FORMAT);
		this.state = reservation.getState();
		this.idTable = reservation.getIdTable();
		this.idUser = reservation.getIdUser();
		
	}

	public ReservationVO(String reservationTime, String state, int idTable, int idUser) {
		this.reservationTime = reservationTime;
		this.state = state;
		this.idTable = idTable;
		this.idUser = idUser;
	}
	
	public Reservation toReservation() {
		Reservation reservation = new Reservation();
		reservation.setId(id);
		reservation.setReservationTime(LocalDateTime.parse(reservationTime));
		reservation.setState(state);
		reservation.setIdTable(idTable);
		reservation.setIdUser(idUser);
		return reservation;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReservationTime() {
		return reservationTime;
	}

	public void setReservationTime(String reservationTime) {
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
