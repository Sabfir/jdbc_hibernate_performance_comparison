package com.opinta.dao;

import com.opinta.config.HibernateConfig;
import com.opinta.entity.StatusTracking;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class StatusTrackingDaoHibernate {
    private SessionFactory sessionFactory;

    public StatusTrackingDaoHibernate() {
        this.sessionFactory = HibernateConfig.getSessionFactory();
    }

    public List<StatusTracking> getAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<StatusTracking> query = builder.createQuery(StatusTracking.class);
        Root<StatusTracking> variableRoot = query.from(StatusTracking.class);
        query.select(variableRoot);
        List<StatusTracking> statusTrackings = session.createQuery(query).getResultList();

        session.getTransaction().commit();
        session.close();

        return statusTrackings;
    }

    public StatusTracking save(StatusTracking statusTracking) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        StatusTracking savedStatusTracking = (StatusTracking) session.merge(statusTracking);

        session.getTransaction().commit();
        session.close();

        return savedStatusTracking;
    }
}
