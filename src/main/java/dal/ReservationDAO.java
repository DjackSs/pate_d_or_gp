package dal;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import bo.Reservation;
import dal.daointerface.GenericDAOInterface;
import jakarta.persistence.RollbackException;

public class ReservationDAO implements GenericDAOInterface<Reservation> {

	private SessionFactory factory;
	
	//===================================================
	
	public ReservationDAO() throws DALException {
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();
		
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        
        
        this.factory = meta.getSessionFactoryBuilder().build();
	}
	
	//=====================================================
	
	public List<Reservation> selectAll() throws DALException {

		Session session = factory.openSession();
		
		List<Reservation> result = session.createQuery("from Reservation", Reservation.class).list();
		
		session.close();
		
		return result;
	}
	
	//=======================================================

	public Reservation selectById(int id) throws DALException {

		Session session = factory.openSession();
		
		Reservation result = session.find(Reservation.class, id);
		
		session.close();
		
		return result;
	}
	
//==============================================================
	
	public void insert(Reservation reservation) throws DALException {
		Session session = factory.openSession();
		
		Transaction transaction = session.beginTransaction();
		
		try {
		session.persist(reservation);
		
		transaction.commit();
		
		} catch (RollbackException e) {
			
			transaction.rollback();
			
		}
		session.close();
	}
	
	//==========================================================
	
	public void update(Reservation reservation) throws DALException {
		Session session = factory.openSession();
		
		Transaction transaction = session.beginTransaction();
		
		try {
			session.merge(reservation);
			
			transaction.commit();
			
		} catch (RollbackException e) {
			
			transaction.rollback();

		}
		session.close();
	}
	
	public void delete(Reservation reservation) throws DALException {
		Session session = factory.openSession();
		
		Transaction transaction = session.beginTransaction();
		
		try {
			session.remove(reservation);
			
			transaction.commit();
			
		} catch (RollbackException e) {
			
			transaction.rollback();

		}
		session.close();
	}

}
