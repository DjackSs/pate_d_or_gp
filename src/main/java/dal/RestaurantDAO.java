package dal;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import bo.Restaurant;
import dal.daointerface.GenericDAOInterface;
import jakarta.persistence.RollbackException;

public class RestaurantDAO implements GenericDAOInterface<Restaurant>
{
	private SessionFactory factory;
		
		
	//==============================================================
	
	
	public RestaurantDAO() throws DALException 
	{
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();
		
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        
        
        this.factory = meta.getSessionFactoryBuilder().build();
		

	}
	
	
	//==============================================================
	

	public List<Restaurant> selectAll() throws DALException
	{
		Session session = this.factory.openSession();
		
		List<Restaurant> result = session.createQuery("from Restaurant", Restaurant.class).list();
		
		session.close();
		
		return result;
		
	}
	
	//--------------------------------------------------------------
	
	public Restaurant selectById(int id) throws DALException
	{
		
		Session session = this.factory.openSession();
		
		Restaurant result = session.find(Restaurant.class, id);
		
		session.close();
		
		return result;
		
		
	}
	
	//--------------------------------------------------------------
	
	/*
		public Restaurant selectByReservation(int id) throws DALException
		{
			Session session = factory.openSession();
			
			TypedQuery<Restaurant> query = session.createNamedQuery("findRestaurantWithReservation", Restaurant.class); 
			
			Restaurant restaurant = query.setParameter("idReservation", id).getSingleResult();
			
			session.close();
			
			return restaurant;
				
		}
		
	*/
	//--------------------------------------------------------------
	
	public void insert(Restaurant restaurant) throws DALException
	{
		
		Session session = this.factory.openSession();
		
		
		Transaction transaction = session.beginTransaction();
		
		try
		{

			session.persist(restaurant);
			
			transaction.commit();
			
		}
		catch (RollbackException error)
		{
			transaction.rollback();
		}
		
		session.close();
		
		
	}
	
	//--------------------------------------------------------------
	
	public void update(Restaurant restaurant) throws DALException
	{
		
		Session session = this.factory.openSession();
		
		
		Transaction transaction = session.beginTransaction();
		
		try
		{
			//update
			session.merge(restaurant);
			
			transaction.commit();
			
		}
		catch (RollbackException error)
		{
			transaction.rollback();
		}
		
		session.close();
		
		
	
	}
	
	//--------------------------------------------------------------
	
	public void delete(Restaurant restaurant) throws DALException
	{
		
		Session session = this.factory.openSession();
		
		Transaction transaction = session.beginTransaction();
		
		try
		{
			//delete
			session.remove(restaurant);
			
			transaction.commit();
			
		}
		catch (RollbackException error)
		{
			transaction.rollback();
		}
		
		session.close();


	}
}
