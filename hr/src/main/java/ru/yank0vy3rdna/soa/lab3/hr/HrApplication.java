package ru.yank0vy3rdna.soa.lab3.hr;


import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import ru.yank0vy3rdna.soa.lab3.hr.ribbon.RibbonClientConfig;
import ru.yank0vy3rdna.soa.lab3.hr.utils.JakartaXmlHttpMessageConverter;

import java.util.List;


@SpringBootApplication
@RibbonClients(defaultConfiguration = RibbonClientConfig.class)
public class HrApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(HrApplication.class).web(WebApplicationType.SERVLET).run(args);
    }

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        try {
            HttpClient httpClient = HttpClients.custom().build();

            HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(
                    httpClient);
            requestFactory.setReadTimeout(6000);
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

}
