package com.chris.dao.impl;

import org.junit.AssumptionViolatedException;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * Created by hanghua on 2/20/17.
 */
public class Retry implements TestRule {

    private int rc;

    public Retry(int rc){
        this.rc = rc;
    }

    public Statement apply(Statement base, Description description) {
        return statement(base,description);
    }

    public Statement statement(final Statement base, final Description description){
        return  new Statement() {
            @Override
            public void evaluate() throws Throwable {
                Throwable throwable = null;
                for(int i=0; i< rc; i++){
                    try{
                        base.evaluate();
                        //if everything is good then return, return means stop the loop
                        return;
                    }catch (Throwable e){
                        throwable = e;
                        if(throwable.getClass() != AssumptionViolatedException.class){
                            System.out.println("Round of run" + (i+1) + " failed");
                        }else{
                            throw throwable;
                        }

                    }
                }
                System.out.println("Give up after " + rc + " failures");
                throw throwable;
            }
        };
    }
}
