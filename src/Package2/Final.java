package Package2;

abstract class Test {
    private String testName;
    private String testType;
    private int executionTime;
    private boolean passed;
    public String getTestName(){return testName;}
    public String getTestType(){return testType;}
    public int getExecutionTime(){return executionTime;}
    public boolean getPassed(){return passed;}
    public void setTestName(String testName){
        this.testName=testName;
    }
    public void setTestType(String testType){
        this.testType=testType;
    }
    public void setExecutionTime(int executionTime){
        if(executionTime>0){
            this.executionTime=executionTime;
        }
    }
    protected void setPassed(boolean passed){
        this.passed=passed;
    }

    Test(String testName, String testType, int executionTime){
        this.testName=testName;
        this.testType=testType;
        setExecutionTime(executionTime);

    }

    public abstract void execute();
    public String getPerformanceCategory(){
        String performance;
        if(executionTime<=30){
            performance="Excellent";
        } else if(executionTime<=60){
            performance="Good";
        } else if(executionTime<=90){
            performance="Slow";
        } else {
            performance="Critical";
        }
        return performance;
    }
}

class WebTest extends Test{
    WebTest(String testName, String testType, int executionTime){
        super(testName, testType, executionTime);
    }
    @Override
    public void execute(){
        System.out.println("Executing Web test using Selenium");
        if(getExecutionTime()<=60){
            setPassed(true);
            System.out.println(getPassed());
        } else {
            setPassed(false);
            System.out.println(getPassed());
        }
    }
}

class ApiTest extends Test{
    ApiTest(String testName, String testType, int executionTime){
        super(testName, testType, executionTime);
    }
    @Override
    public void execute(){
        System.out.println("Executing API test using RestAssured");
        if(getExecutionTime()<=60){
            setPassed(true);
            System.out.println(getPassed());
        } else {
            setPassed(false);
            System.out.println(getPassed());
        }
    }
}

class MobileTest extends Test{
    MobileTest(String testName, String testType, int executionTime){
        super(testName, testType, executionTime);
    }
    @Override
    public void execute(){
        System.out.println("Executing Mobile test using Appium");
        if(getExecutionTime()<=60){
            setPassed(true);
            System.out.println(getPassed());
        } else {
            setPassed(false);
            System.out.println(getPassed());
        }
    }
}

public class Final{
    public static void main(String[] args){
        Test[] tests = new Test[6];
        tests[0]= new WebTest("LoginTest", "WebTest", 45);
        tests[1]= new WebTest("CheckoutTest", "WebTest", 75);
        tests[2]= new ApiTest("PaymentAPI", "ApiTest", 30);
        tests[3]= new ApiTest("UserAPI", "ApiTest", 90);
        tests[4]= new MobileTest("LoginMobile", "MobileTest", 55);
        tests[5]= new MobileTest("CheckoutMobile", "MobileTest", 120);

        int count=0;
        int passedTest=0;
        int failedTest=0;
        int slowTime=0;
        int fastTime=60;
        int webCount=0;
        int apiCount=0;
        int mobCount=0;
        double sum=0;
        double average=0;
        Test slowTest=null;
        Test fastTest=null;
        for(int i = 0; i< tests.length;i++){
            tests[i].execute();
            System.out.println(tests[i].getTestName()+"\nType: "+tests[i].getTestType()+"\nExecution Type: "+tests[i].getExecutionTime()+"\nStatus: "+tests[i].getPassed()+"\nPerformance: "+tests[i].getPerformanceCategory());
            count++;
            if(tests[i].getPassed()==true){
                passedTest++;
            } else {
                failedTest++;
            }
            if(tests[i].getExecutionTime()>slowTime){
                slowTime=tests[i].getExecutionTime();
                slowTest=tests[i];
            }
            if(tests[i].getExecutionTime()<fastTime){
                fastTime=tests[i].getExecutionTime();
                fastTest=tests[i];
            }
            if(tests[i] instanceof WebTest){
                webCount++;
            }
            if(tests[i] instanceof ApiTest){
                apiCount++;
            }
            if(tests[i] instanceof MobileTest){
                mobCount++;
            }
            sum=sum+tests[i].getExecutionTime();
            average=sum/tests.length;

        }
        System.out.println("Total Tests: "+count);
        System.out.println("Passed Tests: "+passedTest);
        System.out.println("Failed Tests: "+failedTest);
        System.out.println("Slowest Test: "+slowTest.getTestName()+"\nExecution Time: "+slowTest.getExecutionTime());
        System.out.println("Fastest Test: "+fastTest.getTestName()+"\nExecution Time: "+fastTest.getExecutionTime());
        System.out.println("Web Tests: "+webCount+"\nAPI Tests: "+apiCount+"\nMobile Tests: "+mobCount);
        System.out.println("Average execution time: "+average);
    }
}
