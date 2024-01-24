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

import bo.User;

public class UserDAO {
	private static final String SELECT = "SELECT * FROM users";
	private static final String SELECT_BY_ID = "SELECT * FROM users WHERE id = ?";
	private static final String SELECT_BY_EMAIL_AND_PASSWORD = "SELECT * FROM users WHERE Users.email = ? and Users.password = ?";	
	private static final String INSERT_INTO_USERS = "INSERT INTO users (name, lastname, email, password, role) VALUES (?, ?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE users SET name = ?, lastname = ?, email = ?, password = ?, role = ? WHERE id = ?";
	private static final String DELETE = "DELETE FROM users WHERE id = ?";
	//	
	private Connection cnx;

	public UserDAO() throws DALException {
		try
		{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/patedor");

			cnx = dataSource.getConnection();
			if(!cnx.isClosed()) {
				System.out.println("La connexion est ouverte");

			}
		
		
		} 
		catch (SQLException error) 
		{
			
			throw new DALException("erreur de conexion à la base de donnée", error);
		}
		catch (NamingException e) 
		{
			e.printStackTrace();
		}
	}

	//======================================

	public List<User> selectAll() throws DALException {
		List<User> users = new ArrayList<>();

		try {
			PreparedStatement ps = cnx.prepareStatement(SELECT);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setLastname(rs.getString("lastname"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setRole(rs.getString("role"));

				users.add(user);
			}
		} catch (SQLException error) {

			throw new DALException("Unable to recover datas", error);
		}
		return users;
	}

	//======================================

	public User selectById(int id) throws DALException {

		User user = null;

		try {

			PreparedStatement ps = cnx.prepareStatement(SELECT_BY_ID);

			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if(rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setLastname(rs.getString("lastname"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setRole(rs.getString("role"));
			}
		} 
		catch (SQLException error) {
			throw new DALException("Unable to recover the data", error);
		}

		return user;
	}
	
	//======================================

		public User selectByEmailAndPassword(String email, String password) throws DALException {

			User user = null;

			try {

				PreparedStatement ps = cnx.prepareStatement(SELECT_BY_EMAIL_AND_PASSWORD);

				ps.setString(1, email);
				ps.setString(2, password);

				ResultSet rs = ps.executeQuery();

				if(rs.next()) {
					user = new User();
					user.setId(rs.getInt("id"));
					user.setName(rs.getString("name"));
					user.setLastname(rs.getString("lastname"));
					user.setEmail(rs.getString("email"));
					user.setPassword(rs.getString("password"));
					user.setRole(rs.getString("role"));
				}
			} 
			catch (SQLException error) 
			{
				throw new DALException("Unable to recover the data", error);
			}

			return user;
		}

	//======================================

	public void insert(User user) throws DALException {

		try {

			PreparedStatement ps = cnx.prepareStatement(INSERT_INTO_USERS,PreparedStatement.RETURN_GENERATED_KEYS);

			ps.setString(1, user.getName());
			ps.setString(2, user.getLastname());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getPassword());
			ps.setString(5, user.getRole());
			
			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			
			if(rs.next()) {
				int id = rs.getInt(1);
				user.setId(id);
			}
		} 
		catch (SQLException error) 
		{
			throw new DALException("data to insert invalid", error);
		}
	}
	
	//======================================
	
	public void update(User user) throws DALException {
		try {
			PreparedStatement ps = cnx.prepareStatement(UPDATE);
			ps.setString(1, user.getName());
			ps.setString(2, user.getLastname());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getPassword());
			ps.setString(5, user.getRole());
			ps.setInt(6, user.getId());
			ps.executeUpdate();
			
		} catch (SQLException error) {
			throw new DALException("Impossible de mettre a jour les informations pour l'id "+ user.getId(), error);
		}
	}

	//======================================
	
	public void delete(int id) throws DALException {
		try {
			PreparedStatement ps = cnx.prepareStatement(DELETE);
			ps.setInt(1, id);
			int nbLignesSupprimees = ps.executeUpdate();
			if (nbLignesSupprimees == 0) {
				throw new DALException("Echec de suppression de l'utilisateur d'id " + id, null);
			}
		} catch (SQLException e) {
			throw new DALException("Impossible de supprimer l'utilisateur d'id "+ id, e);
		}
	}
}
