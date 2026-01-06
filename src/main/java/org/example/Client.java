package org.example;

import org.example.api.HelloService;
import org.example.model.Person;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

public class Client {
    public static void main(String[] args) {
        // Create a client proxy
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(HelloService.class);
        factory.setAddress("http://localhost:8080/services/hello");

        HelloService service = (HelloService) factory.create();

        // Test SayHello operation
        System.out.println("=== Testing SayHello ===");
        String greeting = service.sayHello("Ahmed");
        System.out.println("Response: " + greeting);

        // Test FindPerson operation
        System.out.println("\n=== Testing FindPerson ===");
        Person person = service.findPersonById("123");
        System.out.println("Person ID: " + person.getId());
        System.out.println("Person Name: " + person.getName());
        System.out.println("Person Age: " + person.getAge());
    }
}

