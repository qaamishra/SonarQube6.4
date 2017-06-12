package com.demo;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class AdditionTest {

    @Test
    public void testAddition() throws Exception {

        Addition addition=new Addition();
        int result=addition.addition(5,5);
        Assert.assertEquals(result,10);
    }
}