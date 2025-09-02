package uz.freyzan.user;

import org.hibernate.Session;
import org.hibernate.Transaction;
import uz.freyzan.common.config.HibernateConfig;

import java.util.List;

public class UserRepository {

    public List<UserEntity> findAll() {
        Session session = HibernateConfig.getSessionFactory().openSession();
        List<UserEntity> users = session.createQuery("from UserEntity", UserEntity.class).list();
        session.close();
        return users;
    }

    public UserEntity save(UserEntity user) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.persist(user);
        tx.commit();
        session.close();
        return user;
    }

    public UserEntity findById(Long id) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        UserEntity user = session.get(UserEntity.class, id);
        session.close();
        return user;
    }
}
