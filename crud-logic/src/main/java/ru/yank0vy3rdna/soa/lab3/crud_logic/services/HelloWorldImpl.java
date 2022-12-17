package ru.yank0vy3rdna.soa.lab3.crud_logic.services;

import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.enterprise.context.ApplicationScoped;

@Stateful(name = "HelloWorld")
@ApplicationScoped
@Remote(HelloWorld.class)
public class HelloWorldImpl implements HelloWorld {
    @Override
    public String hi() {
        return "Hello world!";
    }
}
