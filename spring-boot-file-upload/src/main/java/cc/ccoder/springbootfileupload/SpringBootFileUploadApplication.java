package cc.ccoder.springbootfileupload;

import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootFileUploadApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootFileUploadApplication.class, args);
    }

    /**
     * 如果不天添加如下操作，在上传超过10MB文件时候，则会出现连接重置，ERR_CONNECTION_RESET错误
     * 在SpringBoot2.0 之后 将TomcatEmbeddedServletContainerFactory  改成了 TomcatServletWebServerFactory
     * 详情请见：https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-2.0-Migration-Guide
     * 但是仍然出现以下问题"Thu Aug 16 09:28:07 CST 2018
     * There was an unexpected error (type=Internal Server Error, status=500).
     * Maximum upload size exceeded; nested exception is java.lang.IllegalStateException: org.apache.tomcat.util.http.fileupload.FileUploadBase$SizeLimitExceededException: the request was rejected because its size (13020295) exceeds the configured maximum (10485760)"
     * 可以添加一个全局异常类来处理。在页面显示后台出现的异常信息
     * 但是仍然没有解决，是因为上传附件超过了自带Tomcat限制大小（1MB）,需要在application.properties中重新修改
     * @return
     */
    @Bean
    public TomcatServletWebServerFactory tomcatEmbedded(){
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addConnectorCustomizers(connector -> {
            if ((connector.getProtocolHandler() instanceof AbstractHttp11Protocol<?>)){
                ((AbstractHttp11Protocol<?>) connector.getProtocolHandler()).setMaxSwallowSize(-1);
            }
        });
        return tomcat;
    }


}
