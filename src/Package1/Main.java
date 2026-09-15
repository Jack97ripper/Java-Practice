package Package1;

abstract class TestCase {
    private String testName;
    private int executionTime;
    public String getTestName(){return testName;}
    public int getExecutionTime(){return executionTime;}
    public void setTestName(String testName){
        this.testName=testName;
    }
    public void setExecutionTime(int executionTime){
        if(executionTime>0){
            this.executionTime=executionTime;
            System.out.println("Valid");
        } else {
            System.out.println("Invalid Time");
        }
    }
    TestCase(String testName, int executionTime){
        this.testName=testName;
        setExecutionTime(executionTime);
    }
    public abstract void executeTest();
}

class WebTest extends TestCase{
    WebTest(String testName, int executionTime){
        super(testName,executionTime);
    }

    @Override
    public void executeTest() {
        System.out.println(getTestName()+" is being executed via selenium"+"\nThe execution time is: "+getExecutionTime());
    }
}

class ApiTest extends TestCase{
    ApiTest(String testName, int executionTime){
        super(testName,executionTime);
    }

    @Override
    public void executeTest() {
        System.out.println(getTestName()+" is being executed via Rest Assured"+"\nThe execution time is: "+getExecutionTime());
    }
}

class MobileTest extends TestCase{
    MobileTest(String testName, int executionTime){
        super(testName,executionTime);
    }

    @Override
    public void executeTest() {
        System.out.println(getTestName()+" is being executed via Appium"+"\nThe execution time is: "+getExecutionTime());
    }
}

public class Main{
    public static void main(String[] args){
        TestCase[] tests = new TestCase[5];
        tests[0]=new WebTest("WebTest",50);
        tests[1]=new ApiTest("ApiTest", 89);
        tests[2]=new MobileTest("MobileTest", 20);
        tests[3]=new ApiTest("ApiTest2", 15);
        tests[4]=new WebTest("WebTest2", 70);
        int count=0;
        TestCase slowestTest=null;
        int slowestTime=0;
        for(int i=0; i< tests.length;i++){
            tests[i].executeTest();
            if(tests[i].getExecutionTime()<=30){
                System.out.println("Fast Test");
            } else if(tests[i].getExecutionTime()<=60){
                System.out.println("Normal Test");
            } else  {
                System.out.println("Slow Test");
            }
            if(tests[i].getExecutionTime()>60) {
                count++;
            }
            if(tests[i].getExecutionTime()>slowestTime){
                slowestTest=tests[i];
                slowestTime=tests[i].getExecutionTime();
            }
        }
        System.out.println("The number of slow tests are: "+count);
        System.out.println("Slowest Test: "+slowestTest.getTestName());
        System.out.println("Execution Time: "+slowestTime);
    }
}
