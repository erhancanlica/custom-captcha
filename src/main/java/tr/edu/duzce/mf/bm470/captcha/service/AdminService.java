package tr.edu.duzce.mf.bm470.captcha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.edu.duzce.mf.bm470.captcha.dao.AdminDao;
import tr.edu.duzce.mf.bm470.captcha.model.Users;

import java.util.List;

@Service
@Transactional
public class AdminService {

    @Autowired
    private AdminDao adminDao;

    public List<Users> getByUsers() {
        List<Users> users = adminDao.getByUsers();
        return users;
    }
}
