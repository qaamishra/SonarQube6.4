package com.demo;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class MultiplicationTest {


    @Test
    public void testMultiplication() throws Exception {

        Multiplication multiplication=new Multiplication();
        int result=multiplication.multiplication(2,3);
        Assert.assertEquals(result,6);

    }
}