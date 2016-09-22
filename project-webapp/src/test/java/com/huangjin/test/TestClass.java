package com.huangjin.test;


//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:spring/*"})

public class TestClass {
    //@Test
    public Dog dog = new Dog("haha");
    public static void main(String[] args) {
        TestClass test = new TestClass();
        System.out.println(test.dog.status);
    }
}

class Dog {
    public String status;

    public Dog(String str) {
        this.status = str;
    }
    protected void finalize() {
        if(status.equals("full")) {
            System.out.println("error");
        }
    }
}
