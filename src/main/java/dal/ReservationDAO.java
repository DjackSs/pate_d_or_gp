package dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bo.Reservation;

public class ReservationDAO {
	private static final String SELECT = "SELECT * FROM reservations";
	private static final String SELECT_BY_ID = "SELECT * FROM reservations WHERE id = ?";
	private static final String INSERT_INTO_RESERVATIONS = "INSERT INTO reservations (reservation_time, state, id_table, id_user) VALUES (?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE reservations SET reservation_time = ?, state = ?, id_table = ?, id_user = ? WHERE id = ?";
	private static final String DELETE = "DELETE FROM reservations WHERE id = ?";
	//	
	private Connection cnx;
	
	public ReservationDAO() throws DALException {
		cnx = ConnectionProvider.getConnection();
	}

	//======================================

	public List<Reservation> selectAll() throws DALException {
		List<Reservation> reservations = new ArrayList<>();

		try {
			PreparedStatement ps = cnx.prepareStatement(SELECT);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				Reservation reservation = new Reservation();
				reservation.setId(rs.getInt("id"));
				reservation.setReservationTime(rs.getDate("reservation_time").toLocalDate());
				reservation.setState(rs.getString("state"));
				reservation.setIdTable(rs.getInt("id_table"));
				reservation.setIdUser(rs.getInt("id_user"));

				reservations.add(reservation);
			}
		} catch (SQLException error) {

			throw new DALException("Unable to recover datas", error);
		}
		return reservations;
	}

	//======================================

	public Reservation selectById(int id) throws DALException {

		Reservation reservation = null;

		try {

			PreparedStatement ps = cnx.prepareStatement(SELECT_BY_ID);

			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if(rs.next()) {
				reservation = new Reservation();
				reservation.setId(rs.getInt("id"));
				reservation.setReservationTime(rs.getDate("reservation_time").toLocalDate());
				reservation.setState(rs.getString("state"));
				reservation.setIdTable(rs.getInt("id_table"));
				reservation.setIdUser(rs.getInt("id_user"));
			}
		} 
		catch (SQLException error) {
			throw new DALException("Unable to recover the data", error);
		}

		return reservation;
	}

	//======================================

	public void insert(Reservation reservation) throws DALException {

		try {

			PreparedStatement ps = cnx.prepareStatement(INSERT_INTO_RESERVATIONS,PreparedStatement.RETURN_GENERATED_KEYS);

			ps.setDate(1, Date.valueOf(reservation.getReservationTime()));
			ps.setString(2, reservation.getState());
			ps.setInt(3, reservation.getIdTable());
			ps.setInt(4, reservation.getIdUser());

			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) {
				int id = rs.getInt(1);
				reservation.setId(id);
			}
		} 
		catch (SQLException error) 
		{
			throw new DALException("data to insert invalid", error);
		}
	}
	
	//======================================
	
	public void update(Reservation reservation) throws DALException {
		try {
			PreparedStatement ps = cnx.prepareStatement(UPDATE);
			ps.setDate(1, Date.valueOf(reservation.getReservationTime()));
			ps.setString(2, reservation.getState());
			ps.setInt(3, reservation.getIdTable());
			ps.setInt(4, reservation.getIdUser());
			ps.setInt(6, reservation.getId());
			ps.executeUpdate();
			
		} catch (SQLException error) {
			throw new DALException("Impossible de mettre a jour les informations pour l'id "+ reservation.getId(), error);
		}
	}

	//======================================
	
	public void delete(int id) throws DALException {
		try {
			PreparedStatement ps = cnx.prepareStatement(DELETE);
			ps.setInt(1, id);
			int nbLignesSupprimees = ps.executeUpdate();
			if (nbLignesSupprimees == 0) {
				throw new DALException("Echec de suppression de la reservation d'id " + id, null);
			}
		} catch (SQLException e) {
			throw new DALException("Impossible de supprimer la reservation d'id "+ id, e);
		}
	}
}
