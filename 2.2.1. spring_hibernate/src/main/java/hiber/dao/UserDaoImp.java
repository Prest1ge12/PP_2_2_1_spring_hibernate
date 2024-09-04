package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public User getUserByCarModel(String model) {
        String hql = "FROM User user WHERE user.userCar.model = :model";
        return sessionFactory.getCurrentSession()
                .createQuery(hql, User.class)
                .setParameter("model", model)
                .uniqueResult();
    }

    @Override
    public User getUserByCarSeries(int series) {
        String HQL = "FROM User user WHERE user.userCar.series = :series";
        return sessionFactory.getCurrentSession()
                .createQuery(HQL, User.class)
                .setParameter("series", series)
                .uniqueResult();
    }


}
