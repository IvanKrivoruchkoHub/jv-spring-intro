package spring.intro.dao;

import spring.intro.model.User;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User add(User user) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Long userId = (Long) session.save(user);
            transaction.commit();
            user.setId(userId);
            return user;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't insert user entity", e);
        }

    }

    @Override
    public User findByEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery
                    = criteriaBuilder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);

            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("email"), email));
            return session.createQuery(criteriaQuery).uniqueResult();
        } catch (Exception e) {
            throw new RuntimeException("Can't find user by email", e);
        }
    }

    @Override
    public User getById(Long userId) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery
                    = criteriaBuilder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"), userId));
            return session.createQuery(criteriaQuery).uniqueResult();
        } catch (Exception e) {
            throw new RuntimeException("Can't find user by id = " + userId, e);
        }
    }

    @Override
    public List<User> getAll(){
        try (Session session = sessionFactory.openSession()) {
            CriteriaQuery<User> criteriaQuery = session.getCriteriaBuilder()
                    .createQuery(User.class);
            criteriaQuery.from(User.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving all movies", e);
        }
    }
}
