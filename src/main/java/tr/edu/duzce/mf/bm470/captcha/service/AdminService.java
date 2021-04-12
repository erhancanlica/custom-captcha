package tr.edu.duzce.mf.bm470.captcha.service;

import tr.edu.duzce.mf.bm470.captcha.model.Admins;
import tr.edu.duzce.mf.bm470.captcha.model.Users;

import java.util.List;

public interface AdminService {

    List<Users> getByUsers();

    Admins findByUser(Users user);
}
