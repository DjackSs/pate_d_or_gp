package dal;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import bo.Card;
import jakarta.persistence.RollbackException;

public class CardDAO 
{
	private SessionFactory factory;

	//======================================
	
	public CardDAO() throws DALException 
	{
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();
		
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        
        
        this.factory = meta.getSessionFactoryBuilder().build();
			
	}
	
	//======================================

	public List<Card> selectAll() throws DALException 
	{
		Session session = this.factory.openSession();
		
		List<Card> result = session.createQuery("from Card", Card.class).list();
		
		session.close();
		
		return result;
		
	}
	
	//----------------------------------------
	
	public Card selectById(int id) throws DALException 
	{
		Session session = this.factory.openSession();
		
		Card result = session.find(Card.class, id);
		
		session.close();
		
		return result;
	}
	
	
	//----------------------------------------

	public void insert(Card card) throws DALException 
	{
		Session session = this.factory.openSession();
		
		Transaction transaction = session.beginTransaction();
		
		try
		{

			session.persist(card);
			
			transaction.commit();
			
		}
		catch (RollbackException error)
		{
			transaction.rollback();
		}
		
		session.close();
	}
	
	//----------------------------------------
	
	public void update(Card card) throws DALException 
	{
		Session session = this.factory.openSession();
		
		Transaction transaction = session.beginTransaction();
		
		try
		{

			session.merge(card);
			
			transaction.commit();
			
		}
		catch (RollbackException error)
		{
			transaction.rollback();
		}
		
		session.close();
	}
	
	//----------------------------------------
	
	public void delete(Card card) throws DALException 
	{
		Session session = this.factory.openSession();
		
		Transaction transaction = session.beginTransaction();
		
		try
		{

			session.remove(card);
			
			transaction.commit();
			
		}
		catch (RollbackException error)
		{
			transaction.rollback();
		}
		
		session.close();

	}
}
