package tr.edu.duzce.mf.bm470.captcha.dao.Impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tr.edu.duzce.mf.bm470.captcha.dao.CaptchaDao;
import tr.edu.duzce.mf.bm470.captcha.model.Captcha;
import org.hibernate.query.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class CaptchaDaoImpl implements CaptchaDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveCaptcha(Captcha captcha) {
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
}
