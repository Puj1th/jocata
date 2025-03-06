package com.jocata.oms.data.um.dao.impl;

import com.jocata.oms.data.um.dao.UserDao;
import com.jocata.oms.datamodel.um.entity.Role;
import com.jocata.oms.datamodel.um.entity.User;
import com.jocata.oms.datamodel.um.response.UserResponse;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

    @Transactional
    @Override
    public void createUser(User user) {

        try {
            Session session = factory.openSession();
            session.beginTransaction();
            session.persist(user);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println("Cannot create user ( " + user.getFullName() + " )");
            e.printStackTrace();
        }
    }

    @Transactional
    @Override
    public User getUserById(int userId) {
        User user = null;
        try {
            Session session = factory.openSession();
            session.beginTransaction();
            user = session.get(User.class, userId);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println("Cannot fetch user with ID: " + userId);
            e.printStackTrace();
        }
        return user;
    }

    @Transactional
    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            Session session = factory.openSession();
            session.beginTransaction();
            users = session.createQuery("FROM User", User.class).getResultList();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println("Cannot fetch users.");
            e.printStackTrace();
        }
        return users;
    }

    @Override
    @Transactional
    public String deleteUser(int id, boolean flag) {
        try {
            Session session = factory.openSession();
            session.beginTransaction();
            User user = session.get(User.class, id);
            if (flag == false) {
                if (user != null) {
                    session.delete(user);
                    session.getTransaction().commit();
                    return "User hard deleted successfully!";
                }
            }
            else {
                user.setDeletedAt(LocalDateTime.now());
                user.setIsActive(false);
                session.update(user);
                session.getTransaction().commit();
                return "User soft deleted successfully!";
            }
            return "User not found!";
        } catch (Exception e) {
            return "Error while deleting user: " + e.getMessage();
        }
    }

    @Override
    @Transactional
    public User getUserByEmail(String email) {
        User user = null;
        try {
            Session session = factory.openSession();
            session.beginTransaction();
            String hql = "FROM User u WHERE u.email = :email";
            Query<User> query = session.createQuery(hql, User.class);
            query.setParameter("email", email);
            user = query.uniqueResult();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println("Cannot fetch user with email: " + email);
            e.printStackTrace();
        }
        return user;
    }

    @Transactional
    public UserResponse getUserIdAndPasswordByEmail(String email) {
        UserResponse userResponse = null;
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            String hqlUser = "SELECT new com.jocata.oms.datamodel.um.response.UserResponse(u.id,u.fullName, u.passwordHash) " +
                    "FROM User u WHERE u.email = :email";
            Query<UserResponse> queryUser = session.createQuery(hqlUser, UserResponse.class);
            queryUser.setParameter("email", email);
            userResponse = queryUser.uniqueResult();

            if (userResponse != null) {
                int userId = userResponse.getUserId();

                String hqlRoleName = "SELECT r.roleName FROM UserRole ur " +
                        "JOIN ur.role r WHERE ur.user.id = :userId";
                Query<String> queryRoleName = session.createQuery(hqlRoleName, String.class);
                queryRoleName.setParameter("userId", userId);
                String roleName = queryRoleName.uniqueResult();

                userResponse.setRoleName(roleName);
            }

            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println("Cannot fetch user details for email: " + email);
            e.printStackTrace();
        }
        return userResponse;
    }



}
