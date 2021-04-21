package tr.edu.duzce.mf.bm470.captcha.dao.Impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tr.edu.duzce.mf.bm470.captcha.dao.ImageDao;
import tr.edu.duzce.mf.bm470.captcha.model.ImageWrapper;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Repository
public class ImageDaoImpl implements ImageDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveImage(ImageWrapper imageWrapper) {
        sessionFactory.getCurrentSession().save(imageWrapper);
    }

    @Override
    public List<ImageWrapper> findAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<ImageWrapper> criteriaQuery = criteriaBuilder.createQuery(ImageWrapper.class);
        Root<ImageWrapper> root = criteriaQuery.from(ImageWrapper.class);
        criteriaQuery.select(root);
        Query<ImageWrapper> query = session.createQuery(criteriaQuery);
        List<ImageWrapper> imageWrappers = query.getResultList();
//        imageWrappers.forEach(imageWrapper -> {
//            try {
//                imageWrapper.setBase(imageWrapper.getImgUtility());
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
//        });
        return imageWrappers;
    }
}
