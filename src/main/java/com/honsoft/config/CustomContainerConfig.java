package com.honsoft.config;

import org.apache.catalina.Wrapper;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

//@Configuration
public class CustomContainerConfig implements WebServerFactoryCustomizer<TomcatServletWebServerFactory>{

	@Override
	public void customize(TomcatServletWebServerFactory factory) {
		TomcatContextCustomizer tomcatContextCustomizer = context -> {
            SecurityConstraint securityConstraint = new SecurityConstraint();
            securityConstraint.setDisplayName("Forbidden");
            securityConstraint.setAuthConstraint(true);
            SecurityCollection securityCollection = new SecurityCollection();
            securityCollection.addPattern("/*");
            securityCollection.addMethod("PUT");
            securityCollection.addMethod("DELETE");
            securityCollection.addMethod("TRACE");
            securityCollection.addMethod("OPTIONS");
            securityCollection.setName("Forbidden");
            securityConstraint.addCollection(securityCollection);
            context.addConstraint(securityConstraint);

            Wrapper defaultServlet = (Wrapper) context.findChild("default");
            defaultServlet.addInitParameter("readonly", "true");
        };
        factory.addContextCustomizers(tomcatContextCustomizer);
		
	}  
  
//  @Bean
//  public ConfigurableServletWebServerFactory tomcatWebServerFactory() {
//      TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
//      factory.addErrorPages(new ErrorPage(HttpStatus.UNAUTHORIZED, "/unauthenticated"));
//      factory.setPort(9090);
//      factory.setRegisterDefaultServlet(true);
//      return factory;
//  }
//  
//  //@Bean
//  public ConfigurableServletWebServerFactory undertowWebServerFactory() {
//      UndertowServletWebServerFactory factory = new UndertowServletWebServerFactory();
//      factory.addErrorPages(new ErrorPage(HttpStatus.UNAUTHORIZED, "/unauthenticated"));
//      factory.setPort(9090);
//      factory.setRegisterDefaultServlet(true);
//      return factory;
//  }
//  
  
}