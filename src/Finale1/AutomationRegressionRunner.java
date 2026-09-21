package Finale1;

import java.io.IOException;

abstract class AutomationSuite{
    private String suiteName;
    private int buildNumber;
    protected boolean initialized;
    public String getSuiteName(){return suiteName;}
    public int getBuildNumber(){return buildNumber;}
    public void setSuiteName(String suiteName){
        this.suiteName=suiteName;
    }
    public void setBuildNumber(int buildNumber){
        this.buildNumber=buildNumber;
    }
    void initialize(){
        this.initialized=true;
        System.out.println(suiteName+" initialized for: "+buildNumber);
    }
    AutomationSuite(String suiteName, int buildNumber){
        this.suiteName=suiteName;
        this.buildNumber=buildNumber;
    }

    abstract int executeTests();
    abstract String getSuiteType();
}

class WebAutomationSuite extends AutomationSuite{
    private int[] executionTimes;
    private String[] testNames;
    private int timeoutLimit;
    public int[] getExecutionTimes(){return executionTimes.clone();}
    public String[] getTestNames(){return testNames.clone();}
    public int getTimeoutLimit(){return timeoutLimit;}

    public void setExecutionTimes(int[] executionTimes) {
        this.executionTimes = executionTimes.clone();
    }

    public void setTestNames(String[] testNames) {
        this.testNames = testNames.clone();
    }

    public void setTimeoutLimit(int timeoutLimit) {
        this.timeoutLimit = timeoutLimit;
    }
    WebAutomationSuite(String suiteName, int buildNumber, int[] executionTimes, String[] testNames, int timeoutLimit){
        super(suiteName, buildNumber);
        this.executionTimes=executionTimes.clone();
        this.testNames=testNames.clone();
        this.timeoutLimit=timeoutLimit;
    }

    @Override
    int executeTests(){
        int passedTest=0;
        for(int i=0;i<executionTimes.length;i++){
            if(executionTimes[i]<=timeoutLimit){
                passedTest++;
                System.out.println(testNames[i]+" PASSED");
            } else {
                System.out.println(testNames[i]+" TIMED OUT");
                break;
            }
        }
        System.out.println("Passed Tests: "+passedTest);
        return passedTest;
    }

    @Override
    String getSuiteType(){
        return "WEB";
    }

    String validateEnvironment(String environment){
        if(environment==null){
            throw new NullPointerException("Environment cannot be null");
        } else if(!environment.equalsIgnoreCase("QA")&&!environment.equalsIgnoreCase("UAT")){
            throw new IllegalArgumentException("Unsupported environment: "+ environment);
        }
        return environment.toUpperCase();
    }

    String getTestName(int index){
        return testNames[index];
        }
}

public class AutomationRegressionRunner {
    static void loadConfiguration(String fileName)throws IOException{
        if(fileName.equalsIgnoreCase("missing.properties")){
            throw new IOException("Configuration not found");
        } else {
            System.out.println("Configuration loaded");
        }
    }
    public static void main(String[] args){
        try {

            String[] testNames = {"LoginTest", "SearchTest", "CheckoutTest", "LogoutTest"};
            int[] executionTimes = {20, 35, 75, 25};
            AutomationSuite suite = new WebAutomationSuite("Regression Suite", 105, executionTimes, testNames, 50);
            WebAutomationSuite websuite = (WebAutomationSuite) suite;
            suite.initialize();
            int passCount=suite.executeTests();
            System.out.println("Execution returned: "+passCount);
            System.out.println("Suite Type: "+suite.getSuiteType());
            String env = websuite.validateEnvironment("UAT");
            System.out.println("Running Against " + env);
            System.out.println("Selected Test: " + websuite.getTestName(1));
            loadConfiguration("config.properties");
            System.out.println("Primary Execution Completed");
            System.out.println(websuite.getTestName(10));
            loadConfiguration("missing.properties");
            System.out.println("All operations completed");
        } catch (NullPointerException e){
            System.out.println("Caught: "+e.getMessage());
        } catch (IllegalArgumentException e){
            System.out.println("Caught 2: "+e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Caught 3: "+e.getMessage());
        } catch (IOException e){
            System.out.println("Caught 4: "+e.getMessage());
        } catch (Exception e){
            System.out.println("Generic");
        }
        finally {
            System.out.println("Regression cleanup completed");
        }
        System.out.println("Runner terminated normally");
    }
}
