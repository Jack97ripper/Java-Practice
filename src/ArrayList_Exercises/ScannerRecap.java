package ArrayList_Exercises;

import java.util.*;

class TestCaseOne{
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
        if(executionTime>0){
            this.executionTime=executionTime;
        } else {
            System.out.println("Invalid Execution Time Input");
        }
    }
    public void setStatus(String status){
        this.status=status;
    }

    TestCaseOne(String testName, int executionTime, String status){
        this.testName=testName;
        setExecutionTime(executionTime);
        this.status=status;
    }
}
public class ScannerRecap {

//    static int numberReceiver(Scanner sc, String message){
//        while(true){
//            System.out.println(message);
//            try{
//                int a = sc.nextInt();
//                sc.nextLine();
//                return a;
//            } catch (InputMismatchException e) {
//                System.out.println("Invalid input");
//                sc.nextLine();
//            }
//        }
//    }

    static int positiveNumberReceiver(Scanner sc, String message){
        while(true){
            System.out.println(message);
            try{
                int a =sc.nextInt();
                sc.nextLine();
                if(a>0){
                    return a;
                } else {
                    System.out.println("Value must be greater than 0");
                }
            } catch (InputMismatchException e){
                System.out.println("Invalid input");
                sc.nextLine();
            }
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int timeoutLimit=positiveNumberReceiver(sc, "Enter timeout Limit:");
        ArrayList<TestCaseOne> testCases = new ArrayList<>();
        int testCount = positiveNumberReceiver(sc, "Enter the number of test cases:");
            for (int i = 0; i < testCount; i++) {
                System.out.println("Enter Test Name: ");
                String name = sc.nextLine();
                int time = positiveNumberReceiver(sc, "Enter Execution Time:");
                testCases.add(new TestCaseOne(name, time, "NOT RUN"));
            }
        for(TestCaseOne tests: testCases){
            if(tests.getExecutionTime()<=timeoutLimit){
                tests.setStatus("PASSED");
            } else{
                tests.setStatus("TIMED OUT");
            }
            System.out.println(tests.getTestName()+" - "+tests.getExecutionTime()+" - "+tests.getStatus());
        }

    }

}



