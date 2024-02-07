package bll;

import java.util.Arrays;
import java.util.List;

import bo.RestaurantTable;
import dal.DALException;
import dal.GenericDAOInterface;
import dal.TableDAO;

public class TableBLL {
	private GenericDAOInterface<RestaurantTable> dao;
	
	public TableBLL() throws BLLException {
		try {
			dao = new TableDAO();
		} catch (DALException e) {
			throw new BLLException("Echec de la connexion", e);
		}
	}
	
	public List<RestaurantTable> selectAll() throws BLLException {
		
		try {
			return dao.selectAll();
		} catch (DALException e) {
			throw new BLLException("Echec de la recuperation des tables", e);
		}
	}
	
	public RestaurantTable selectById(int id) throws BLLException {
		try {
			return dao.selectById(id);
		} catch (DALException e) {
			throw new BLLException("Echec de la recuperation de la table d'id " + id, e);
		}
		
	}
	
    public List<RestaurantTable> selectTablesByRestaurantId(int restaurantId) throws BLLException {
        try {
            return ((TableDAO) dao).selectByForeignKey("id_restaurant", restaurantId);
        } catch (DALException e) {
            throw new BLLException("Echec de la récupération des tables par id_restaurant", e);
        }
    }
    
    public List<RestaurantTable> selectAllByIdRestaurantOrderBy(int restaurantId, String orderRule) throws BLLException {
    	try {
    		return ((TableDAO) dao).selectAllByIdRestaurantOrderBy("id_restaurant", restaurantId, orderRule);
    	} catch (DALException e) {
    		throw new BLLException("Echec de la récupération des tables par id_restaurant triés par " + orderRule, e);
    	}
    }
	
	public RestaurantTable insert(int numberPlace, String state, int idRestaurant) throws BLLException {
		
		BLLException bllException = new BLLException();
		
		if (numberPlace < 2) {
			bllException.addError("Le nombre de place d'une table doit être au minimum de 2.");
		}
		
		List<String> checkState = Arrays.asList(null, "PRES");
		if (!checkState.contains(state)) {
			bllException.addError("Le statut de la table est soit nul soit PRES");
		}
		
		if (bllException.getErrors().size() > 0) {
			throw bllException;
		}
		
		RestaurantTable table = new RestaurantTable(numberPlace, state, idRestaurant);
		try {
			dao.insert(table);
		} catch (DALException e) {
			throw new BLLException("Echec de l'insertion", e);
		}
		return table;
	}
	
	public void update(RestaurantTable table) throws BLLException {
		
		try {
			dao.update(table);
		} catch (DALException e) {
			throw new BLLException("Echec de la mise a jour", e);
		}
	}
	
    public void updateTable(int id, int newNumberPlace, String newState, int restaurantId) throws BLLException {
        BLLException bllException = new BLLException();

        if (newNumberPlace < 2) {
            bllException.addError("Le nombre de place d'une table doit être au minimum de 2.");
        }

        List<String> checkState = Arrays.asList(null, "PRES");
        if (!checkState.contains(newState)) {
            bllException.addError("Le statut de la table est soit nul soit PRES");
        }

        if (bllException.getErrors().size() > 0) {
            throw bllException;
        }

        RestaurantTable updatedTable = new RestaurantTable();
        updatedTable.setId(id);
        updatedTable.setNumberPlace(newNumberPlace);
        updatedTable.setState(newState);
        updatedTable.setIdRestaurant(restaurantId);

        try {
            dao.update(updatedTable);
        } catch (DALException e) {
            throw new BLLException("Echec de la mise a jour de la table d'id " + id, e);
        }
    }
	
	public void delete(int id) throws BLLException {
		try {
			dao.delete(id);
		} catch (DALException e) {
			throw new BLLException("Echec de la suppression", e);
		}
	}
	
}
