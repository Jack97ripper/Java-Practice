package BufferedReaderPractice;

import java.io.*;
import java.util.ArrayList;

class AutomationTest{
    private String testName;
    private int executionTime;
    private int priority;
    private int retryCount;
    private String status;
    public String getTestName(){return testName;}
    public int getExecutionTime(){return executionTime;}
    public int getPriority(){return priority;}
    public int getRetryCount(){return retryCount;}
    public String getStatus(){return status;}
    public void setTestName(String testName){
        this.testName=testName;
    }
    public void setExecutionTime(int executionTime){
        if(executionTime>0){
            this.executionTime=executionTime;
        } else {
            System.out.println("Enter Valid Input");
        }
    }
    public void setPriority(int priority){
        if(priority>=1&&priority<=3){
            this.priority=priority;
        } else {
            System.out.println("Enter Valid Input");
        }
    }
    public void setRetryCount(int retryCount) {
        if (retryCount >= 0 && retryCount <= 3) {
            this.retryCount = retryCount;
        } else {
            System.out.println("Enter Valid Input");
        }
    }
    public void setStatus(String status){
        this.status=status;
    }

    AutomationTest(String testName, int executionTime, int priority, int retryCount, String status){
        this.testName=testName;
        setExecutionTime(executionTime);
        setPriority(priority);
        setRetryCount(retryCount);
        this.status=status;
    }
}
public class AutomationSuiteManager {
    static int numberReceiver(BufferedReader br, String message) throws IOException {
        while (true) {
            System.out.println(message);
            try {
                int a = Integer.parseInt(br.readLine());
                if (a > 0) {
                    return a;
                } else {
                    System.out.println("Give a valid input");
                }
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid Number");
            }
        }
    }

    static int priorityReceiver(BufferedReader br, String message) throws IOException {
        while (true) {
            System.out.println(message);
            try {
                int a = Integer.parseInt(br.readLine());
                if (a >= 1 && a <= 3) {
                    return a;
                } else {
                    System.out.println("Give a valid input");
                }
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid Number");
            }
        }
    }

    static int retryReceiver(BufferedReader br, String message) throws IOException {
        while (true) {
            System.out.println(message);
            try {
                int a = Integer.parseInt(br.readLine());
                if (a >= 0 && a <= 3) {
                    return a;
                } else {
                    System.out.println("Give a valid input");
                }
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid Number");
            }
        }
    }

    static void validateTestName(String name) {
                if (name == null || name.trim().isEmpty()) {
                    throw new IllegalArgumentException("Test name cannot be blank");
                }
            }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int timeoutLimit = numberReceiver(br, "Enter a time out limit: ");
        int testNum = numberReceiver(br, "Enter the number of test cases: ");
        int passed = 0;
        int retryReq = 0;
        int timeout = 0;
        int total = 0;
        int sum = 0;
        double average;
        AutomationTest longestTest=null;
        int longest=0;

        ArrayList<AutomationTest> testCases = new ArrayList<>();
        for (int i = 0; i < testNum; i++) {
            String name;
            while (true) {
                System.out.println("Enter Test Case Name: ");
                name = br.readLine();
                try {
                    validateTestName(name);
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
            int execTime = numberReceiver(br, "Enter Execution Time: ");
            int priority = priorityReceiver(br, "Enter the priority: ");
            int retry = retryReceiver(br, "Enter the number of retries: ");
            testCases.add(new AutomationTest(name, execTime, priority, retry, "NOT RUN"));
        }
        for (AutomationTest tests : testCases) {
            if (tests.getExecutionTime() <= timeoutLimit) {
                tests.setStatus("PASSED");
                passed++;
            } else if (tests.getRetryCount() > 0) {
                tests.setStatus("RETRY REQUIRED");
                retryReq++;
            } else {
                tests.setStatus("TIMED OUT");
                timeout++;
            }
            System.out.println(tests.getTestName() + " - " + tests.getExecutionTime() + " - " + tests.getPriority() + " - " + tests.getRetryCount() + " - " + tests.getStatus());
        }
        total = passed + retryReq + timeout;
        System.out.println("Passed: " + passed);
        System.out.println("Retry Required: " + retryReq);
        System.out.println("Timed Out: " + timeout);
        System.out.println("Total Tests: " + total);


        for(AutomationTest tests : testCases) {
            sum=sum+tests.getExecutionTime();
        }
        average= (double) sum/total;
        System.out.println("Average Execution Time: "+average);

        System.out.println("Enter test name to search: ");
        boolean found = false;
        for (AutomationTest tests: testCases) {
            String name;
            while (true) {
                System.out.println("Enter Test Case Name: ");
                name = br.readLine();
                try {
                    validateTestName(name);
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
            if (tests.getTestName().equalsIgnoreCase(name)) {
                found = true;
                System.out.println(tests.getTestName() + " - " + tests.getExecutionTime() + " - " + tests.getPriority() + " - " + tests.getRetryCount() + " - " + tests.getStatus());
                break;
            }
        }
        if(!found){
            System.out.println("Test not found");
        }

        for(AutomationTest tests: testCases){
            if(tests.getExecutionTime()>longest){
                longest = tests.getExecutionTime();
                longestTest=tests;
            }
        }
        System.out.println("Longest Test: "+longestTest.getTestName()+" - "+longestTest.getExecutionTime());

        AutomationTest highest= null;
        int highestprio=4;
        for(AutomationTest tests: testCases){
            if(tests.getPriority()<highestprio){
                highestprio=tests.getPriority();
                highest=tests;
            }
        }
        System.out.println("Highest Priority Test: "+highest.getTestName()+" - "+highest.getPriority());

        AutomationTest testToRemove=null;
        System.out.println("Enter test name to remove");
        String nameTest=br.readLine();
        for(AutomationTest tests: testCases){
            if(tests.getTestName().equalsIgnoreCase(nameTest)){
                testToRemove=tests;
                break;
            }
        }
        if(testToRemove!=null){
            testCases.remove(testToRemove);
            System.out.println(nameTest+" removed");
        }

        for(AutomationTest tests: testCases){
            System.out.println(tests.getTestName());
        }


    }
}