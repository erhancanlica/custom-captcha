package tr.edu.duzce.mf.bm470.captcha.dao.Impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tr.edu.duzce.mf.bm470.captcha.dao.ImageDao;
import tr.edu.duzce.mf.bm470.captcha.model.Captcha;
import tr.edu.duzce.mf.bm470.captcha.model.ImageWrapper;

import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class ImageDaoImpl implements ImageDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(ImageWrapper imageWrapper) {
        sessionFactory.getCurrentSession().save(imageWrapper);
    }

    @Override
    public void merge(ImageWrapper imageWrapper) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(imageWrapper);
    }
    @Override
    public List<ImageWrapper> findByCaptchaId(Long id){
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<ImageWrapper> criteriaQuery = criteriaBuilder.createQuery(ImageWrapper.class);
        Root<ImageWrapper> imageRoot = criteriaQuery.from(ImageWrapper.class);

        Predicate predicateCaptcha = criteriaBuilder.equal(imageRoot.get("captcha").get("id"),id);
        Predicate predicateValid = criteriaBuilder.equal(imageRoot.get("isValid"), true);
        Predicate predicateJoin = criteriaBuilder.and(predicateCaptcha, predicateValid);

        criteriaQuery.select(imageRoot).where(predicateJoin);
        criteriaQuery.distinct(true);
        Query<ImageWrapper> captchaQuery = session.createQuery(criteriaQuery);
        List<ImageWrapper> image = captchaQuery.getResultList();

        return image;
    }


}
