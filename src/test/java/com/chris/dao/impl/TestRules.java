package com.chris.dao.impl;

import org.junit.*;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

/**
 * Created by hanghua on 2/21/17.
 */
public class TestRules {

    @Rule
    public Retry retry = new Retry(4);

    @BeforeClass
    public static void beforeClass(){
        System.out.println("beforeClass method only execute once at the beginning   ");
    }


    @AfterClass
    public static void afterClass(){
        System.out.println("afterClass method only execute once at the end  ");
    }

    //Create listener for the test results
    @Rule
    public TestRule listen = new TestWatcher() {
        @Override
        protected void succeeded(Description description) {
            System.out.println("method name " + description.getMethodName() + " passed");
        }

        @Override
        protected void failed(Throwable e, Description description) {
            System.out.println("method name " + description.getMethodName() + " failed");
        }
    };
}
