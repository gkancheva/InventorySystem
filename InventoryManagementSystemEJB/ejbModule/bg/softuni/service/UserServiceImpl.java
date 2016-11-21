package bg.softuni.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import bg.softuni.entity.UserModel;

@Stateless
public class UserServiceImpl implements UserService {

    @PersistenceContext
    protected EntityManager entityManager;

    public UserServiceImpl() {
        super();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<UserModel> findAllUsers() {
        String query = "SELECT model FROM UserModel model ORDER BY UPPER(model.username) ASC";
        Query q = entityManager.createQuery(query);
        return q.getResultList();
    }

    @Override
    public UserModel save(UserModel entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public UserModel update(UserModel entity) {
        entityManager.merge(entity);
        entityManager.flush();
        return entity;
    }

    @Override
    public void delete(UserModel entity) {
        entityManager.remove(entity);
    }

    @Override
    public UserModel findById(Long id) {
        try {
            UserModel instance = entityManager.find(UserModel.class, id);
            return instance;
        } catch (RuntimeException re) {
            throw re;
        }
    }

    @Override
    public UserModel loginUser(String aUsername, String aPassword) {
        StringBuffer query = new StringBuffer(
                "SELECT model FROM UserModel model WHERE model.username = :em and model.password = :p");

        Query q = entityManager.createQuery(query.toString());
        q.setParameter("em", aUsername);
        q.setParameter("p", aPassword);
        try {
            return (UserModel) q.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public UserModel checkUserExists(String username, Long id) {
        StringBuffer query = new StringBuffer(
                "SELECT model FROM UserModel model WHERE UPPER(model.username) = UPPER(:em)");

        if (id != null) {
            query.append(" and model.id <> :id");//.append(id);
        }

        Query q = entityManager.createQuery(query.toString());
        q.setParameter("em", username);

        try {
            return (UserModel) q.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }
}
