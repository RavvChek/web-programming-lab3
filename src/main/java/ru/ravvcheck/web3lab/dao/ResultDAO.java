package ru.ravvcheck.web3lab.dao;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.ravvcheck.web3lab.model.models.Result;
import ru.ravvcheck.web3lab.utils.HibernateSessionFactoryUtil;

import java.io.Serializable;
import java.util.List;

@Named("dao")
@SessionScoped
public class ResultDAO implements Serializable {
    private final SessionFactory sessionFactory;

    public ResultDAO() {
        sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
    }

    public void save(Result result) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(result);
        tx1.commit();
        session.close();
    }

    public List<Result> getResults() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<Result> locals = (List<Result>) session.createQuery("FROM Result").list();
        transaction.commit();
        session.close();
        return locals;
    }
}
