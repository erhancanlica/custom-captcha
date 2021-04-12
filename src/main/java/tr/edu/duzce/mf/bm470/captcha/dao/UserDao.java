package tr.edu.duzce.mf.bm470.captcha.dao;

import tr.edu.duzce.mf.bm470.captcha.model.Users;

public interface UserDao {

    Users findByUsername(String userName);
}
