package ru.yank0vy3rdna.soa.lab3.hr;


import jakarta.servlet.Servlet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.SimpleWsdl11Definition;
import org.springframework.ws.wsdl.wsdl11.Wsdl11Definition;
import ru.yank0vy3rdna.soa.lab3.hr.utils.JakartaXmlHttpMessageConverter;

import java.util.List;


@SpringBootApplication
@EnableWs
public class HrApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(HrApplication.class).web(WebApplicationType.SERVLET).run(args);
    }

    @Bean
    public RestTemplate restTemplate() {
        try {
            CloseableHttpClient httpClient = HttpClients.custom().build();

            HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
            requestFactory.setConnectTimeout(6000);
            requestFactory.setConnectionRequestTimeout(6000);
            RestTemplate restTemplate = new RestTemplate(requestFactory);
            List<HttpMessageConverter<?>> converters = restTemplate.getMessageConverters();
            converters.add(new JakartaXmlHttpMessageConverter());
            restTemplate.setMessageConverters(converters);
            return restTemplate;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
    @Bean
    public ServletRegistrationBean<Servlet> messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

    @Bean(name = "hr")
    public Wsdl11Definition defaultWsdl11Definition() {
        SimpleWsdl11Definition wsdl11Definition = new SimpleWsdl11Definition();
        wsdl11Definition.setWsdl(new ClassPathResource("hr.wsdl"));
        return wsdl11Definition;
    }
}
