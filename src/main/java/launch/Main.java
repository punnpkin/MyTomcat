package launch;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import servlet.HelloServlet;

public class Main {
    public static void main(String[] args) throws LifecycleException {

        Tomcat tomcat = new Tomcat();
        Connector connector = new Connector("HTTP/1.1");
        connector.setPort(8081);
        tomcat.setConnector(connector);

        tomcat.getHost().setAutoDeploy(false);

        StandardContext standardContext = new StandardContext();
        standardContext.setPath("/hello");
        standardContext.addLifecycleListener(new Tomcat.FixContextListener());
        tomcat.getHost().addChild(standardContext);

        tomcat.addServlet("/hello","hello",new HelloServlet());
        standardContext.addServletMappingDecoded("/","hello");

        tomcat.start();
        tomcat.getServer().await();

    }
}
