package tr.edu.duzce.mf.bm470.captcha.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebAppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext applicationContext = getContext();
        applicationContext.register(WebSecurityConfig.class);
        ServletRegistration.Dynamic dispatcherServlet = servletContext.addServlet("DispatcherServlet", new DispatcherServlet(applicationContext));
        servletContext.addListener(new ContextLoaderListener(applicationContext));
        servletContext.addFilter("securityFilter", new DelegatingFilterProxy("springSecurityFilterChain"))
                .addMappingForUrlPatterns(null, false, "/*");

        dispatcherServlet.setLoadOnStartup(1);
        dispatcherServlet.addMapping("/");
        dispatcherServlet.setInitParameter("throwExceptionIfNoHandlerFound", "true");
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        servletContext.addFilter("characterEncodingFilter", characterEncodingFilter)
                                .addMappingForUrlPatterns(null, false, "/*");
    }

    private AnnotationConfigWebApplicationContext getContext(){
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.setConfigLocation("tr.edu.duzce.mf.bm470.captcha.config");
        return applicationContext;
    }



}
