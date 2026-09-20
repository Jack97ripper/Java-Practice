package ExceptionHandling;
import java.io.IOException;

abstract class TestSuite{
    private String suiteName;
    protected boolean initialized;
    public String getSuiteName(){return suiteName;}
    public void setSuiteName(String suiteName){
        this.suiteName=suiteName;
    }
    TestSuite(String suiteName){
        this.suiteName=suiteName;
    }

    void initialize(){
        this.initialized=true;
        System.out.println(suiteName+" initialized");
    }

    abstract void executeTests();
}

class WebTestSuite extends TestSuite{
    private int[] executionTimes;
    private int timeoutLimit;
    WebTestSuite(String suiteName, int[] executionTimes, int timeoutLimit){
        super(suiteName);
        this.executionTimes=executionTimes.clone();
        this.timeoutLimit=timeoutLimit;
    }
    @Override
    void executeTests() {
        int passedTests = 0;
        for (int i = 0; i < executionTimes.length; i++) {
            if (executionTimes[i] <= timeoutLimit) {
                passedTests++;
            } else {
                System.out.println("Test timed out at index: " + i);
                break;
            }
        }

        System.out.println("Tests successfully executed: " + passedTests);
    }
        void validateEnvironment(String environment){
            if(environment==null){
                throw new NullPointerException("Environment Cannot be Null");
            } else if(!environment.equalsIgnoreCase("QA")&&!environment.equalsIgnoreCase("UAT")) {
                throw new IllegalArgumentException("Invalid Environment");
            } else {
                System.out.println(environment.toUpperCase());
            }
        }

}
public class AutomationTestSuiteManager {
    static void loadTestData()throws IOException{
        throw new IOException("Test data file unavailable");
    }
    public static void main(String[] args) {
        try {
            int[] executionTimes = {20, 35, 60, 25};
            TestSuite suite = new WebTestSuite("UtafRunner", executionTimes, 50);
            WebTestSuite websuite = (WebTestSuite) suite;
            suite.initialize();
            suite.executeTests();
            websuite.validateEnvironment("qa");
            System.out.println("Environment Validated");
            int[] retryCounts = {1, 2, 3};
            System.out.println(retryCounts[5]);
            loadTestData();
            System.out.println("Suite Execution Completed");
        } catch (NullPointerException e){
            System.out.println("Caught: "+e.getMessage());
        } catch (IllegalArgumentException e){
            System.out.println(("New Caught: "+e.getMessage()));
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Another Caught: "+e.getMessage());
        } catch (IOException e){
            System.out.println("IO Exception Caught: "+e.getMessage());
        } catch (Exception e){
            System.out.println("Generic Exception");
        }
        finally {
            System.out.println("Automation cleanup Completed");
        }
        System.out.println("Program Finished");
    }
}
