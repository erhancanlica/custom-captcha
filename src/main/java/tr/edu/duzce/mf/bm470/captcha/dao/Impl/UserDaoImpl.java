package tr.edu.duzce.mf.bm470.captcha.dao.Impl;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tr.edu.duzce.mf.bm470.captcha.dao.UserDao;
import tr.edu.duzce.mf.bm470.captcha.model.Users;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Repository
@Slf4j
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Users findByUsername(String userName) {
        Session session = sessionFactory.getCurrentSession();
        Users user = null;

        try {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Users> criteriaQuery = criteriaBuilder.createQuery(Users.class);
            Root<Users> root = criteriaQuery.from(Users.class);
            Predicate predicateUsername = criteriaBuilder.equal(root.get("username"), userName);
            criteriaQuery.select(root).where(predicateUsername);;
            Query<Users> query = session.createQuery(criteriaQuery);
            user = query.getSingleResult();
        } catch (NoResultException nr){
            log.debug("kullanıcı bulunamadı username = {}", userName);
        } catch (Exception err){
            log.error(err.getMessage());
        }
        return user;
    }
}
