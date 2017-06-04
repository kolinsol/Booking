package by.bsuir.tsiarokhin.booking.tests;

import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class TestRules {

    @Rule
    public TestRule listener = new TestWatcher() {

        @Override
        protected void succeeded(Description description) {
            System.out.println("test passed " + description.getMethodName());
        }

        @Override
        protected void failed(Throwable e, Description description) {
            System.out.println("test failed " + description.getMethodName());
        }
    };
}
