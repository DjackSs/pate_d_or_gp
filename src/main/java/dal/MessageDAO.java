package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bo.Message;

public class MessageDAO {
	private static final String SELECT = "SELECT * FROM messages";
	private static final String SELECT_BY_ID = "SELECT * FROM messages WHERE id = ?";
	private static final String INSERT_INTO_MESSAGES = "INSERT INTO messages (object, content, id_user) VALUES (?, ?, ?)";
	private static final String UPDATE = "UPDATE messages SET object = ?, content = ?, id_user = ? WHERE id = ?";
	private static final String DELETE = "DELETE FROM messages WHERE id = ?";
	//	
	private Connection cnx;
	
	public MessageDAO() throws DALException {
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/patedor");
			cnx = dataSource.getConnection();
			if(!cnx.isClosed()) {
				System.out.println("La connexion est ouverte");
			}
		} catch (SQLException e) {
			throw new DALException("Erreur de connexion a la base de donnees", e);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	//======================================

	public List<Message> selectAll() throws DALException {
		List<Message> messages = new ArrayList<>();

		try {
			PreparedStatement ps = cnx.prepareStatement(SELECT);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				Message message = new Message();
				message.setId(rs.getInt("id"));
				message.setObject(rs.getString("object"));
				message.setContent(rs.getString("content"));
				message.setIdUser(rs.getInt("id_user"));

				messages.add(message);
			}
		} catch (SQLException error) {

			throw new DALException("Unable to recover datas", error);
		}
		return messages;
	}

	//======================================

	public Message selectById(int id) throws DALException {

		Message message = null;

		try {

			PreparedStatement ps = cnx.prepareStatement(SELECT_BY_ID);

			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if(rs.next()) {
				message = new Message();
				message.setId(rs.getInt("id"));
				message.setObject(rs.getString("object"));
				message.setContent(rs.getString("content"));
				message.setIdUser(rs.getInt("id_user"));
			}
		} 
		catch (SQLException error) {
			throw new DALException("Unable to recover the data", error);
		}

		return message;
	}

	//======================================

	public void insert(Message message) throws DALException {

		try {

			PreparedStatement ps = cnx.prepareStatement(INSERT_INTO_MESSAGES,PreparedStatement.RETURN_GENERATED_KEYS);

			ps.setString(1, message.getObject());
			ps.setString(2, message.getContent());
			ps.setInt(3, message.getIdUser());

			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) {
				int id = rs.getInt(1);
				message.setId(id);
			}
		} 
		catch (SQLException error) 
		{
			throw new DALException("data to insert invalid", error);
		}
	}
	
	//======================================
	
	public void update(Message message) throws DALException {
		try {
			PreparedStatement ps = cnx.prepareStatement(UPDATE);
			ps.setString(1, message.getObject());
			ps.setString(2, message.getContent());
			ps.setInt(3, message.getIdUser());
			ps.setInt(6, message.getId());
			ps.executeUpdate();
			
		} catch (SQLException error) {
			throw new DALException("Impossible de mettre a jour les informations pour l'id "+ message.getId(), error);
		}
	}

	//======================================
	
	public void delete(int id) throws DALException {
		try {
			PreparedStatement ps = cnx.prepareStatement(DELETE);
			ps.setInt(1, id);
			int nbLignesSupprimees = ps.executeUpdate();
			if (nbLignesSupprimees == 0) {
				throw new DALException("Echec de suppression du message d'id " + id, null);
			}
		} catch (SQLException e) {
			throw new DALException("Impossible de supprimer le message d'id "+ id, e);
		}
	}
}
