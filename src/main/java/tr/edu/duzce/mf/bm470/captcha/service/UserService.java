package tr.edu.duzce.mf.bm470.captcha.service;

import tr.edu.duzce.mf.bm470.captcha.model.Users;

public interface UserService {

    Users findByUserName(String userName);
}
