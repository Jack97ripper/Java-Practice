package BufferedReaderPractice;

import java.io.*;
import java.util.ArrayList;

class TestCaseTwo {
    private String testName;
    private int executionTime;
    private String status;
    private int priority;

    public String getTestName() {
        return testName;
    }

    public int getExecutionTime() {
        return executionTime;
    }

    public String getStatus() {
        return status;
    }

    public int getPriority() {
        return priority;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public void setExecutionTime(int executionTime) {
        if (executionTime > 0) {
            this.executionTime = executionTime;
        } else {
            System.out.println("Enter Valid Execution Time");
        }
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPriority(int priority) {
        if(priority>=1&&priority<=3){
            this.priority=priority;
        } else {
            System.out.println("Enter Valid Value");
        }
    }
    TestCaseTwo(String testName, int executionTime, String status, int priority){
        this.testName=testName;
        setExecutionTime(executionTime);
        this.status=status;
        setPriority(priority);
    }
}
public class TestManagerTwo {
    static int numberReceiver(BufferedReader br, String message)throws IOException{
        while (true){
            System.out.println(message);
            try{
                int a = Integer.parseInt(br.readLine());
                if(a>0){
                    return a;
                } else {
                    System.out.println("Enter a valid integer value");
                }
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid number");
            }
        }
    }
    static int priorityReceiver(BufferedReader br, String message)throws IOException{
        while(true){
            System.out.println(message);
            try{
                int a = Integer.parseInt(br.readLine());
                if(a>=1&&a<=3){
                    return a;
                } else {
                    System.out.println("Enter a number within range");
                }
            } catch (Exception e) {
                System.out.println("Enter valid value");
            }
        }
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<TestCaseTwo> testCases = new ArrayList<>();
        int testCount=numberReceiver(br, "Enter the Number of Test Cases: ");
        int timeoutLimit=numberReceiver(br, "Enter positive timeout Limit");
        int passedTest=0;
        int timedoutTest=0;
        int longest=0;
        TestCaseTwo longestTest=null;
        boolean found = false;
        for(int i=0; i<testCount;i++){
            System.out.println("Enter Test Case Name: ");
            String name = br.readLine();
            int time = numberReceiver(br, "Enter Execution Time: ");
            int prio = priorityReceiver(br, "Enter Priority of test case: ");
            testCases.add(new TestCaseTwo(name, time, "NOT RUN", prio));
        }
        for(TestCaseTwo tests: testCases){
            if(tests.getExecutionTime()<=timeoutLimit){
                tests.setStatus("PASSED");
                passedTest++;
            } else {
                tests.setStatus("TIMED OUT");
                timedoutTest++;
            }
            System.out.println(tests.getTestName()+" - "+tests.getExecutionTime()+" - "+tests.getPriority()+" - "+tests.getStatus());
        }
        System.out.println("Passed: "+passedTest+"\nTimed Out: "+timedoutTest);

        System.out.println("Enter test case to be searched: ");
        String searchName= br.readLine();
        for(TestCaseTwo tests: testCases){
            if(tests.getTestName().contains(searchName)){
                found =true;
                System.out.println(tests.getTestName()+" - "+tests.getExecutionTime()+" - "+tests.getStatus());
            }
        }
        if(!found){
            System.out.println("Test not found");
        }
        for(TestCaseTwo tests: testCases){
            if(tests.getExecutionTime()>longest){
                longest = tests.getExecutionTime();
                longestTest=tests;
            }
        }
        System.out.println("Longest Test: "+longestTest.getTestName()+" - "+longestTest.getExecutionTime());
    }
}
