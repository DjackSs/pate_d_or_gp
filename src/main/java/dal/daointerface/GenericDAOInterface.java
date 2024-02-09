package dal.daointerface;

import java.util.List;

import dal.DALException;

public interface GenericDAOInterface<T> 
{
	
	List<T> selectAll() throws DALException;
	
	T selectById(int id) throws DALException;
	

}
