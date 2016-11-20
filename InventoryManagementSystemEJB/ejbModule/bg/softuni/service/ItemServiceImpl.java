package bg.softuni.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
		entityManager.persist(entity);
		return entity;
	}

	@Override
	public ItemModel update(ItemModel entity) {
		entityManager.merge(entity);
        entityManager.flush();
        return entity;
	}

	@Override
	public void delete(ItemModel entity) {
		Query query = entityManager.createQuery("DELETE FROM ItemModel item WHERE id = :id");
		query.setParameter("id", entity.getId()).executeUpdate();
		if (entityManager.contains(entity)){
			entityManager.remove(entity);
		}
	}
	
	@Override
	public void deleteAllItemsOfSpecProject(Long projectId) {
        Query query = entityManager.createNativeQuery(
        	      "DELETE FROM items WHERE project_id = :id");
        query.setParameter("id", projectId).executeUpdate();
	}

	@Override
	public ItemModel findById(Long id) {
		try {
            ItemModel instance = entityManager.find(ItemModel.class, id);
            return instance;
        } catch (RuntimeException re) {
            throw re;
        }
	}

	@Override
	public ItemModel checkIfItemExists(String name, Long id) {
		StringBuffer query = new StringBuffer(
                "SELECT model FROM ItemModel model WHERE UPPER(model.name) = UPPER(:em)");
        if (id != null) {
            query.append(" and model.id <> ").append(id);
        }

        Query q = entityManager.createQuery(query.toString());
        q.setParameter("em", name);

        try {
            return (ItemModel) q.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
	}
}
