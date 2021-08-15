package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Component
public class UserDaoImpl implements UserDao {
    private EntityManager entityManager;

    @Autowired
    public void setEntityManager(EntityManager entityManager){
        this.entityManager = entityManager;
    }


    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void removeUser(Long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public void updateUser(User user, Long id) {

        User existUser = entityManager.find(User.class, id);
        existUser.setFirstName(user.getFirstName());
        existUser.setLastName(user.getLastName());
        existUser.setAge(user.getAge());

        entityManager.merge(existUser);
    }

    @Override
    public User getUser(Long id) {
        return entityManager
                .find(User.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        return entityManager
                .createQuery("FROM User")
                .getResultList();
    }
}
