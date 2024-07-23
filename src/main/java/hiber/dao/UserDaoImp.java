package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public List<Car> listCars() {
      TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery("from Car");
      return query.getResultList();
   }

   @Transactional
   @Override
   public User carList(String model, int series) {
      User user = new User();
      Query query = sessionFactory.getCurrentSession().createQuery("select c.user from Car c where c.model=:model and c.series=:series");
      query.setParameter("model",model);
      query.setParameter("series",series);
      user = (User) query.getSingleResult();
      return user;
   }
}
