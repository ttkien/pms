package pms.web_page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

 class WebServiceDomainGetter {

    private String domain = "http://localhost:8080";

    String getWebServiceDomain() {
        return domain;
     }
}

public class Utils {

    public static String getWebServiceDomain() {
        return new WebServiceDomainGetter().getWebServiceDomain();
    }

//    public  static String getHost() {
//        HttpServletRequest request = getHttpServletRequest();
//        return "http://" + request.getHeader("host");
//    }

    public  static HttpServletRequest getHttpServletRequest() {
        RequestAttributes requestAttributes = RequestContextHolder
                .currentRequestAttributes();
        ServletRequestAttributes attributes = (ServletRequestAttributes) requestAttributes;
        return attributes.getRequest();

    }

    public static HttpSession getSession() {
        return getHttpServletRequest().getSession();
    }
}
