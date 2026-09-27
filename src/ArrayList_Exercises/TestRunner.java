package ArrayList_Exercises;

import java.util.*;
class TestCase{
    private String testName;
    private int executionTime;
    private String status;
    public String getTestName(){return testName;}
    public int getExecutionTime(){return executionTime;}
    public String getStatus(){return status;}
    public void setTestName(String testName){
        this.testName=testName;
    }
    public void setExecutionTime(int executionTime){
        this.executionTime=executionTime;
    }
    public void setStatus(String status){
        this.status=status;
    }

    TestCase(String testName, int executionTime, String status){
        this.testName=testName;
        this.executionTime=executionTime;
        this.status=status;
    }
}

public class TestRunner {
    public static void main(String[] args){
        int timeoutLimit = 50;
        ArrayList<TestCase> testCases = new ArrayList<>();
        testCases.add(new TestCase("LoginTest", 20, "NOT RUN"));
        testCases.add(new TestCase("SearchTest", 35, "NOT RUN"));
        testCases.add(new TestCase("CheckoutTest", 75, "NOT RUN"));
        testCases.add(new TestCase("PaymentTest", 40, "NOT RUN"));

        for(TestCase test : testCases){
            if(test.getExecutionTime()<=timeoutLimit){
                test.setStatus("PASSED");
                System.out.println("PASSED");
            } else {
                test.setStatus("TIMED OUT");
                System.out.println("TIMED OUT");
            }
        }
        for(TestCase test : testCases){
            System.out.println(test.getTestName()+" - "+test.getExecutionTime()+" - "+test.getStatus());
        }
        for(TestCase test : testCases){
            if(test.getTestName().equals("LoginTest")){
                System.out.println("LoginTest Found");
            }
            if(test.getTestName().equals("CheckoutTest")){
                System.out.println("CheckoutTest Execution Time is: "+test.getExecutionTime());
            }
            if(test.getTestName().equals("PaymentTest")){
                test.setStatus("RE-RUN");
            }
        }
        for(TestCase test : testCases){
            System.out.println(test.getTestName()+" - "+test.getExecutionTime()+" - "+test.getStatus());
        }
    }
}
