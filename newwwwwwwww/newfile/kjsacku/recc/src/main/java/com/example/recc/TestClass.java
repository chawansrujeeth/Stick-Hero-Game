package com.example.recc;
import org.junit.Test;


import java.io.IOException;

import static org.junit.Assert.assertEquals;
public class TestClass {

    HelloController helloController = new HelloController();
    @Test
    public void Test1(){
        assertEquals(helloController.getTest1(),0.0, 2);
    }

    @Test(timeout = 100)
    public void Test2(){
        assertEquals(GameState.getPresent_cherries(),0);
    }


}
