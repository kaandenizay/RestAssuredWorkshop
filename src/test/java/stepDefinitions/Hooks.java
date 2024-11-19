package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {


    @Before
    public void setup(){
        System.out.println("I'M BEFORE TEST");
    }

    @After
    public void tearDown(){
        System.out.println("I'M AFTER TEST");
    }
}
