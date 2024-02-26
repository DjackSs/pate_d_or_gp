package dal;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import bo.Message;
import bo.Reservation;
import bo.User;
import dal.daointerface.UserDAOInterface;
import jakarta.persistence.NoResultException;
import jakarta.persistence.RollbackException;
import jakarta.persistence.TypedQuery;

public class UserDAO implements UserDAOInterface<User>
{
	private SessionFactory factory;
	
	//======================================

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

	//----------------------------------------

	public User selectById(int id) throws DALException 
	{

		Session session = this.factory.openSession();
		
		User result = session.find(User.class, id);
		
		session.close();
		
		return result;
	}
	
	//----------------------------------------
	
	public User selectByEmailAndPassword(String email, String password) throws DALException 
	{
		Session session = factory.openSession();
		
		TypedQuery<User> query = session.createNamedQuery("findUser", User.class); 
		
		try
		{
			User user = query.setParameter("email", email).setParameter("password", password).getSingleResult();
			
			session.close();
			
			return user;
		}
		catch (NoResultException e)
		{
			session.close();
			
			throw new DALException("No match for this query", e);
			
		}
		
		
		
		
		
	}
	
	
	//----------------------------------------

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
	
	//----------------------------------------


		public void insertMessage(Message message) throws DALException 
		{

			Session session = this.factory.openSession();
			
			
			Transaction transaction = session.beginTransaction();
			
			try
			{

				session.persist(message);
				
				transaction.commit();
				
			}
			catch (RollbackException error)
			{
				transaction.rollback();
			}
			
			session.close();
		}
	
	//----------------------------------------

		public void insertReservation(Reservation reservation) throws DALException
		{
			
			Session session = this.factory.openSession();
			
			
			Transaction transaction = session.beginTransaction();
			
			try
			{

				session.persist(reservation);
				
				transaction.commit();
				
			}
			catch (RollbackException error)
			{
				transaction.rollback();
			}
			
			session.close();


		}
	
	//----------------------------------------
	
	public void update(User user) throws DALException 
	{
		Session session = this.factory.openSession();
		
		
		Transaction transaction = session.beginTransaction();
		
		try
		{

			session.merge(user);
			
			transaction.commit();
			
		}
		catch (RollbackException error)
		{
			transaction.rollback();
		}
		
		session.close();
	}

	//----------------------------------------
	
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
