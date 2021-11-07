package oop.Demo07;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

public class Person {
    {
        System.out.println("匿名代码块");
    }
    static {
        System.out.println("静态代码块");
    }
    public Person() {
        System.out.println("构造器");
    }
}
