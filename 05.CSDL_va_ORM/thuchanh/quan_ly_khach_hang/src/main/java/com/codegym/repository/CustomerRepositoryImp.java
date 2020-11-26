//package com.codegym.repository;
//
//import com.codegym.entity.Customer;
//import org.hibernate.HibernateException;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//import org.hibernate.cfg.Configuration;
//import org.springframework.stereotype.Repository;
//import org.springframework.stereotype.Service;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityTransaction;
//import javax.persistence.TypedQuery;
//import java.util.List;
//
//@Repository
//@Service
//public class CustomerRepositoryImp implements CustomerRepository {
//    public static SessionFactory sessionFactory;
//    public static EntityManager entityManager;
//
//    static {
//        try {
//            SessionFactory sessionFactory = new Configuration()
//                    .configure("hibernate.conf.xml")
//                    .buildSessionFactory();
//            sessionFactory.close();
//        } catch (HibernateException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    @Override
//    public List<Customer> findAll() {
//        TypedQuery<Customer> typedQuery = entityManager.createQuery("select c from customer c", Customer.class);
//        return typedQuery.getResultList();
//    }
//
//    @Override
//    public Customer findById(int id) {
//        String queryStr = "SELECT c FROM Customer AS c WHERE c.id = :id";
//        TypedQuery<Customer> query = entityManager.createQuery(queryStr, Customer.class);
//        query.setParameter("id", id);
//        return query.getSingleResult();
//    }
//
////    @Override
////    public Customer findOne(Long id) {
////        String queryStr = "SELECT c FROM Customer AS c WHERE c.id = :id";
////        TypedQuery<Customer> query = entityManager.createQuery(queryStr, Customer.class);
////        query.setParameter("id", id);
////        return query.getSingleResult();
////    }
//
////    @Override
////    public void save(Customer customer) {
////        EntityTransaction entityTransaction =entityManager.getTransaction();
////        entityTransaction.begin();
////        entityManager.persist(customer);
////        entityTransaction.commit();
////    }
//
//    @Override
//    public void update(Customer customer) {
//        EntityTransaction entityTransaction = entityManager.getTransaction();
//        entityTransaction.begin();
//        entityManager.merge(customer);
//        entityTransaction.commit();
//    }
//    @Override
//    public Customer save(Customer customer) {
//        Session session = null;
//        Transaction transaction = null;
//        try {
//            session = sessionFactory.openSession();
//            transaction = session.beginTransaction();
//            Customer origin = findById(customer.getId());
//            origin.setName(customer.getName());
//            origin.setEmail(customer.getEmail());
//            origin.setAddress(customer.getAddress());
//            session.saveOrUpdate(origin);
//            transaction.commit();
//            return origin;
//        } catch (Exception e) {
//            e.printStackTrace();
//            if (transaction != null) {
//                transaction.rollback();
//            }
//        } finally {
//            if (session != null) {
//                session.close();
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public void remove(int id) {
//        EntityTransaction entityTransaction = entityManager.getTransaction();
//        entityTransaction.begin();
//        entityManager.remove(findById(id));
//        entityTransaction.commit();
//    }
//}
