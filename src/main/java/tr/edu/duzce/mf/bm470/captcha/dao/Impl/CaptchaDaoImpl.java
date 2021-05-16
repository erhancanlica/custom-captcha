package tr.edu.duzce.mf.bm470.captcha.dao.Impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tr.edu.duzce.mf.bm470.captcha.dao.CaptchaDao;
import tr.edu.duzce.mf.bm470.captcha.model.Captcha;
import org.hibernate.query.Query;

import javax.persistence.criteria.*;
import java.io.Serializable;
import java.util.List;

@Repository
public class CaptchaDaoImpl implements CaptchaDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Captcha captcha) {
        sessionFactory.getCurrentSession().save(captcha);
    }

    @Override
    public List<Captcha> findAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Captcha> criteriaQuery = criteriaBuilder.createQuery(Captcha.class);
        Root<Captcha> root = criteriaQuery.from(Captcha.class);
        criteriaQuery.select(root);
        Query<Captcha> captchaQuery = session.createQuery(criteriaQuery);

        return captchaQuery.getResultList();
    }

    @Override
    public Captcha findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Captcha> criteriaQuery = criteriaBuilder.createQuery(Captcha.class);
        Root<Captcha> root = criteriaQuery.from(Captcha.class);
        Predicate predicateId = criteriaBuilder.equal(root.get("id"), id);
        criteriaQuery.select(root).where(predicateId);
        Query<Captcha> captchaQuery = session.createQuery(criteriaQuery);
        Captcha captcha = captchaQuery.getSingleResult();
        return captcha;
    }

    @Override
    public void delete(Captcha captcha) {
        Session session = sessionFactory.getCurrentSession();
        Object persistentInstance = session.load(Captcha.class, captcha.getId());
        if (persistentInstance != null) {
            session.delete(persistentInstance);
        }
    }

    @Override
    public List<Long> findAllCaptchaIds() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<Captcha> root = criteriaQuery.from(Captcha.class);
        Predicate predicateValid = criteriaBuilder.equal(root.get("status"), true);
        criteriaQuery.select(root.get("id")).where(predicateValid);
        Query<Long> query = session.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
