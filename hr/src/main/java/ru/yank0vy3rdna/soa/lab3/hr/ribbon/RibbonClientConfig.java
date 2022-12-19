package ru.yank0vy3rdna.soa.lab3.hr.ribbon;

import com.netflix.client.config.DefaultClientConfigImpl;

public class RibbonClientConfig extends DefaultClientConfigImpl {
    @Override
    public String getDefaultSeverListClass() {
        return ConsulServerList.class.getName();
    }
}
