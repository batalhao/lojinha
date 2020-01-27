package br.com.ameridata.lojinha.config.init;

import br.com.ameridata.lojinha.config.JPAConfig;
import br.com.ameridata.lojinha.config.SecurityConfig;
import br.com.ameridata.lojinha.config.ServiceConfig;
import br.com.ameridata.lojinha.config.WebConfig;
import org.springframework.web.filter.FormContentFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{ JPAConfig.class, ServiceConfig.class, SecurityConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{ WebConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{ "/" };
    }

    @Override
    protected Filter[] getServletFilters() {
//		CharacterEncodingFilter filter = new CharacterEncodingFilter();
//
//		filter.setEncoding("UTF-8");
//		filter.setForceEncoding(true);
        FormContentFilter formContentFilter = new FormContentFilter();

        return new Filter[]{ formContentFilter };
    }

    @Override
    protected void customizeRegistration(Dynamic registration) {
        registration.setMultipartConfig(new MultipartConfigElement(""));
    }

}