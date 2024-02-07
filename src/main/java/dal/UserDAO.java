package dal;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import bo.User;
import jakarta.persistence.RollbackException;
import jakarta.persistence.TypedQuery;

public class UserDAO 
{
	private SessionFactory factory;
	

	public UserDAO() throws DALException 
	{
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();
		
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        
        
        this.factory = meta.getSessionFactoryBuilder().build();
	}

	//======================================

	public List<User> selectAll() throws DALException 
	{
		Session session = this.factory.openSession();
		
		List<User> result = session.createQuery("from User", User.class).list();
		
		session.close();
		
		return result;
	}

	//======================================

	public User selectById(int id) throws DALException 
	{

		Session session = this.factory.openSession();
		
		User result = session.find(User.class, id);
		
		session.close();
		
		return result;
	}
	
	//======================================
	
	public User selectByEmailAndPassword(String email, String password)
	{
		Session session = factory.openSession();
		
		TypedQuery<User> query = session.createNamedQuery("findUser", User.class);
		
		User user = query.setParameter("email", email).setParameter("password", password).getSingleResult();
		
		session.close();
		
		return user;
	}
	
	/*
	
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
	
	*/
	//======================================

	public void insert(User user) throws DALException 
	{

		Session session = this.factory.openSession();
		
		
		Transaction transaction = session.beginTransaction();
		
		try
		{

			session.persist(user);
			
			transaction.commit();
			
		}
		catch (RollbackException error)
		{
			transaction.rollback();
		}
		
		session.close();
	}
	
	//======================================
	
	public void update(User user) throws DALException 
	{
		Session session = this.factory.openSession();
		
		
		Transaction transaction = session.beginTransaction();
		
		try
		{

			session.persist(user);
			
			transaction.commit();
			
		}
		catch (RollbackException error)
		{
			transaction.rollback();
		}
		
		session.close();
	}

	//======================================
	
	public void delete(User user) throws DALException
	{
		Session session = this.factory.openSession();
		
		Transaction transaction = session.beginTransaction();
		
		try
		{

			session.remove(user);
			
			transaction.commit();
			
		}
		catch (RollbackException error)
		{
			transaction.rollback();
		}
		
		session.close();

	}
}
