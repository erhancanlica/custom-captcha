//package tr.edu.duzce.mf.bm470.captcha.security;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//import tr.edu.duzce.mf.bm470.captcha.model.Admins;
//import tr.edu.duzce.mf.bm470.captcha.model.Users;
//import tr.edu.duzce.mf.bm470.captcha.utils.Constants;
//
//import javax.naming.AuthenticationException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.lang.reflect.Method;
//import java.time.LocalDateTime;
//
//@Component
//@Slf4j
//public class SessionInterceptor extends HandlerInterceptorAdapter {
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        HandlerMethod hm = (HandlerMethod) handler;
//        Method method = hm.getMethod();
//        HttpSession session = request.getSession();
//        if (session != null) {
//            Users user = null;
//            Object sessionInfo = session.getAttribute(Constants.userInfoKey);
//            if(sessionInfo instanceof Admins){
//                user = ((Admins) sessionInfo).getUser();
//            }
//            if (user == null)
//                throw new AuthenticationException();
//            log.info("{} isimli kullanıcı {} methoda {} tarihinde istekte bulundu",user.getUsername(),method.getName(),LocalDateTime.now());
//            return true;
//        }
//
//        return false;
//    }
//
//}
