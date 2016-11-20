package bg.softuni.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import bg.softuni.entity.ProjectModel;

@Stateless
public class ProjectServiceImpl implements ProjectService{

	@PersistenceContext
	protected EntityManager entityManager;
	
	public ProjectServiceImpl() {
		super();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ProjectModel> findAllProjects() {
		String query = "SELECT model FROM ProjectModel model ORDER BY UPPER(model.name) ASC";
        Query q = entityManager.createQuery(query);
        return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ProjectModel> findAllProjectsOfSpecUser(Long userId) {
		String query = "SELECT model FROM ProjectModel model WHERE model.user.id = " + userId + " ORDER BY UPPER(model.name) ASC";
        Query q = entityManager.createQuery(query);
        return q.getResultList();
	}

	@Override
	public ProjectModel save(ProjectModel entity) {
		entityManager.persist(entity);
		return entity;
	}

	@Override
	public ProjectModel update(ProjectModel entity) {
		entityManager.merge(entity);
        entityManager.flush();
        return entity;
	}

	@Override
	public void delete(ProjectModel entity) {
		entityManager.remove(entity);
	}

	@Override
	public ProjectModel findById(Long id) {
		try {
            ProjectModel instance = entityManager.find(ProjectModel.class, id);
            return instance;
        } catch (RuntimeException re) {
            throw re;
        }
	}

	@Override
	public ProjectModel checkIfPorjectExists(String name, Long id) {
		StringBuffer query = new StringBuffer(
                "SELECT model FROM ProjectModel model WHERE UPPER(model.name) = UPPER(:em)");
        if (id != null) {
            query.append(" and model.id <> ").append(id);
        }

        Query q = entityManager.createQuery(query.toString());
        q.setParameter("em", name);

        try {
            return (ProjectModel) q.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
	}
}