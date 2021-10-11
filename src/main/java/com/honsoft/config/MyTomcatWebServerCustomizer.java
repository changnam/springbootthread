package com.honsoft.config;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.catalina.Context;
import org.apache.catalina.Wrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

// springboot 는 auto configure 하는 WebServerFactoryCustomizer 를 수행하고, 여기 정의된 customizer를 후에 수행한다.
// WebServerFactory 에 접근하여 customization 가능하다.
@Component
public class MyTomcatWebServerCustomizer implements WebServerFactoryCustomizer<TomcatServletWebServerFactory>  {
	private static Logger logger = LoggerFactory.getLogger(MyTomcatWebServerCustomizer.class);
	
    @Value("${tomcat.file.base}")  // C:\\some\\parent\\child
    String tomcatBaseDir;

    @Override
    public void customize(TomcatServletWebServerFactory factory) {
    	factory.setRegisterDefaultServlet(true);
    	File file = factory.getDocumentRoot();
    	Map<String,String> initParameters = new HashMap<>();
    	initParameters.put("listings", "true");
    	factory.setInitParameters(initParameters);
    	
    	if (file != null)
    		logger.info("+++++++++++++++++ " +file.getAbsolutePath()+","+file.getName());
    	initParameters = factory.getInitParameters();
    	for (String key : initParameters.keySet()) {
    		logger.info("+++++++++++++++++ " +key+ " , " + initParameters.get(key));
    	}
    	Collection<TomcatContextCustomizer> customizers = factory.getTomcatContextCustomizers();
    	logger.info("+++++++++++++++++ " +Integer.toString(customizers.size()));
    	
    	factory.setDocumentRoot(new File(tomcatBaseDir));
//    	Iterator<TomcatContextCustomizer> iter = customizers.iterator();
    	
//    	while(iter.hasNext()) {
//    		TomcatContextCustomizer customizer = iter.next();
//    		customizer.
//    	}
    	
        // customize the factory here
        TomcatContextCustomizer tomcatContextCustomizer = new TomcatContextCustomizer() {
            @Override
            public void customize(Context context) {
                Wrapper defServlet = (Wrapper) context.findChild("default");
                defServlet.addInitParameter("listings", "true");
                defServlet.addInitParameter("readOnly", "false");
            }
        };
        factory.addContextCustomizers(tomcatContextCustomizer);

    }
}


