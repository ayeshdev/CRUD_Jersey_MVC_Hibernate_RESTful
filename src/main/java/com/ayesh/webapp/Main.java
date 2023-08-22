package com.ayesh.webapp;


import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;


import java.io.File;

public class Main {
    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.getConnector(); //meken apita puluwan http protocol ekak configure karaganna

        Context context = tomcat.addWebapp("/crudapp",new File("./src/main/webapp").getAbsolutePath()); // api contenxt path eka hadaganne mehema
        context.setAllowCasualMultipartParsing(true);  // form submission waladi POST request ekakin file uploading wage wedakadi oni wenwa.

//        tomcat.addServlet(context,"a",new ServletContainer(new AppConfig()));  // mehema thamai api embedded server ekak use karanawanam filters add karanne.
//        context.addServletMappingDecoded("/*","a");

        tomcat.start();
    }
}
