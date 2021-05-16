package tr.edu.duzce.mf.bm470.captcha.dao.Impl;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tr.edu.duzce.mf.bm470.captcha.dao.AdminDao;
import tr.edu.duzce.mf.bm470.captcha.model.Admins;
import tr.edu.duzce.mf.bm470.captcha.model.Users;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@Slf4j
public class AdminDaoImpl implements AdminDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Admins findByUser(Users user) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Admins> criteriaQuery = criteriaBuilder.createQuery(Admins.class);
        Root<Admins> root = criteriaQuery.from(Admins.class);
        Predicate predicateUser = criteriaBuilder.equal(root.get("user"), user);
        criteriaQuery.select(root).where(predicateUser);;
        Query<Admins> query = session.createQuery(criteriaQuery);
        return query.getSingleResult();
    }
}
