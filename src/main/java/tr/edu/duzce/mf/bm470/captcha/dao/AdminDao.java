package tr.edu.duzce.mf.bm470.captcha.dao;

import tr.edu.duzce.mf.bm470.captcha.model.Admins;
import tr.edu.duzce.mf.bm470.captcha.model.Users;

import java.util.List;

public interface AdminDao {

    Admins findByUser(Users user);
}
