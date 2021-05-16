package tr.edu.duzce.mf.bm470.captcha.security;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;
import tr.edu.duzce.mf.bm470.captcha.model.Admins;
import tr.edu.duzce.mf.bm470.captcha.model.Users;
import tr.edu.duzce.mf.bm470.captcha.service.AdminService;
import tr.edu.duzce.mf.bm470.captcha.service.UserService;
import tr.edu.duzce.mf.bm470.captcha.utils.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
@Service("customSuccessHandler")
public class CustomSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String targetUrl = StringUtils.EMPTY;
        final User user = (User) authentication.getPrincipal();
        final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        final List<String> roles = new ArrayList<>();
        authorities.forEach(grantedAuthority -> roles.add(grantedAuthority.getAuthority()));
        final Users dbUser = userService.findByUserName(user.getUsername());
        if (isAdmin(roles)) {
            targetUrl = authenticateAdmin(request, dbUser);
        }
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }


    public String authenticateAdmin(HttpServletRequest request, Users dbUser) {
        final Admins admin = adminService.findByUser(dbUser);
        request.getSession().setAttribute(Constants.userInfoKey, admin);
        return "/admin/list";
    }

    public boolean isAdmin(List<String> roles) {
        return roles.contains("ROLE_ADMIN");
    }
}
