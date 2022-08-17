/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;

import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Philip
 */
public class GenericDao<O> {
    O obj;
    public O create(O object){
        Session session = DBUtil.getSessionFactory().openSession();
        
        session.beginTransaction();
        session.save(object);
        session.getTransaction().commit();
        session.close();
        return object;
    }
    public O delete(O object){
        Session session = DBUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(object);
        session.getTransaction().commit();
        session.close();
        return object;
    }
    public O update(O object){
        Session session = DBUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(object);
        session.getTransaction().commit();
        session.close();
        return object;
    }
    public List<O> findAll(O object){
        Session session = DBUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from "+object.getClass().getName());
        List<O> list = query.list();
        session.close();
        return list;
    }

    public  O findOne(Class cl, String id){
        Session session = DBUtil.getSessionFactory().openSession();
        O object = (O)session.get(cl, id);
        session.close();
        return object;
    }

    public  List<O> findbyCourse(Class cl, String id){
        Session session = DBUtil.getSessionFactory().openSession();
        String hql = "FROM Registration WHERE course_code = '"+id+"'";
        Query query = session.createQuery(hql);
        List<O> object = (List<O>) query.list();
        session.close();
        return object;
    }

    public  List<O> findbyStudent(Class cl, String id){
        Session session = DBUtil.getSessionFactory().openSession();
        String hql = "FROM Registration WHERE student_id = '"+id+"'";
        Query query = session.createQuery(hql);
        List<O> object = (List<O>) query.list();
        session.close();
        return object;
    }

    public List<O> findStudents(String id){
        Session session = DBUtil.getSessionFactory().openSession();
        String hql = "FROM Student WHERE department_id = '"+id+"'";
        Query query = session.createQuery(hql);
        List<O> objects = (List<O>) query.list();
        session.close();
        return objects;
    }

    public List<O> findCourses(String id){
        Session session = DBUtil.getSessionFactory().openSession();
        String hql = "FROM Course WHERE department_id = '"+id+"'";
        Query query = session.createQuery(hql);
        List<O> object = (List<O>) query.list();
        session.close();
        return object;
    }
}
