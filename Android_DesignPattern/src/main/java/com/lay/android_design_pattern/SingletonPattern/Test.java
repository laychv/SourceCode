package com.lay.android_design_pattern.SingletonPattern;

public class Test {

    public void text() {
        sSingleton.get();
    }

    static final Singleton<Test> sSingleton = new Singleton<Test>() {
        @Override
        protected Test create() {

            return null;
        }
    };

}
