package ru.yank0vy3rdna.soa.lab3.hr.ribbon;

import com.netflix.client.config.CommonClientConfigKey;
import com.netflix.client.config.DefaultClientConfigImpl;
import com.netflix.client.config.IClientConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.netflix.ribbon.RibbonClientName;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.netflix.client.config.DefaultClientConfigImpl.DEFAULT_CONNECT_TIMEOUT;
import static com.netflix.client.config.DefaultClientConfigImpl.DEFAULT_READ_TIMEOUT;

@Configuration()
public class RibbonConfiguration {

    @RibbonClientName
    private String name = "crud";

    @Bean
    @ConditionalOnMissingBean
    public IClientConfig ribbonClientConfig() {
        RibbonClientConfig config = new RibbonClientConfig();
        config.loadProperties(this.name);
        config.set(CommonClientConfigKey.ConnectTimeout, DEFAULT_CONNECT_TIMEOUT);
        config.set(CommonClientConfigKey.ReadTimeout, DEFAULT_READ_TIMEOUT);
        config.set(CommonClientConfigKey.GZipPayload, false);
        config.set(CommonClientConfigKey.ServerListRefreshInterval, 5000);
        config.set(CommonClientConfigKey.NIWSServerListClassName, ConsulServerList.class.getName());
        return config;
    }
}

