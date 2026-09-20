package Package2;

import java.io.IOException;

abstract class Browser{
    private String browserName;
    protected boolean launched;
    public String getBrowserName(){return browserName;}
    public void setBrowserName(String browserName){
        this.browserName=browserName;
    }
    Browser(String browserName){
        this.browserName=browserName;
    }
    boolean launch(){
        this.launched=true;
        System.out.println(browserName+" launched");
        return launched;
    }
    abstract void executeTest();
}

class ChromeBrowser extends Browser{
    private int[] testResults;
    ChromeBrowser(String browserName, int[] testResults){
        super(browserName);
        this.testResults=testResults.clone();
    }
    @Override
    void executeTest(){
        int countPass=0;
        for(int i=0;i<testResults.length;i++){
            if(testResults[i]>=50){
                countPass++;
            } else {
                break;
            }
        }
        System.out.println("Tests Passed: "+countPass);
    }

    void validateBrowser(String name){
        if(name==null){
            throw new IllegalArgumentException("Browser Name cannot be null");
        } else {
            System.out.println(name.toUpperCase());
        }
    }
}

public class Except {
    static void readConfiguration() throws IOException {
        throw new IOException("Configuration file Missing");
    }
    public static void main(String[] args) {
        try {
        int[] testResults={80, 70, 40, 90};
        Browser browser = new ChromeBrowser("Chrome", testResults);
            browser.launch();
            browser.executeTest();
            readConfiguration();
            ChromeBrowser chrome = (ChromeBrowser) browser;
            chrome.validateBrowser(null);

            System.out.println("Validation Completed");

                int[] scores = {10, 20, 30};
                System.out.println(scores[5]);
            } catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Invalid Index");
            } catch (IllegalArgumentException e){
                System.out.println("Caught: "+e.getMessage());
            } catch (NullPointerException e) {
                System.out.println("Caught Null: " + e.getMessage());
            } catch (IOException e){
                System.out.println(e.getMessage());
            } catch (Exception e){
                System.out.println("General Exception");
            }
            finally {
                System.out.println("Browser Cleanup");
            }
            System.out.println("Test Execution finished");
    }
}



