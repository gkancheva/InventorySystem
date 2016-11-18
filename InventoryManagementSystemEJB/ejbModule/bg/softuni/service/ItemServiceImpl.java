package bg.softuni.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import bg.softuni.entity.ItemModel;

@Stateless
public class ItemServiceImpl implements ItemService{

	@PersistenceContext
	protected EntityManager entityManager;
	
	public ItemServiceImpl() {
		super();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ItemModel> findAllItemsByProjectId(Long projectId) {
		String query = "SELECT model FROM ItemModel model WHERE model.project = " + projectId + "ORDER BY UPPER(model.name) ASC";
        Query q = entityManager.createQuery(query);
        //q.setParameter("projectId", projectId);
        return q.getResultList();
	}

	@Override
	public ItemModel save(ItemModel entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemModel update(ItemModel entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(ItemModel entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ItemModel findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemModel checkIfItemExists(String name, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
