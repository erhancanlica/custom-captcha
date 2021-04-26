package tr.edu.duzce.mf.bm470.captcha.dao.Impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tr.edu.duzce.mf.bm470.captcha.dao.ImageDao;
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
}
