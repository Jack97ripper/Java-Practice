package ArrayList_Exercises;

import java.util.ArrayList;

public class TestManager {
    public static void main(String[] args){
        ArrayList<String> testNames = new ArrayList<>();
        testNames.add("LoginTest");
        testNames.add("SearchTest");
        testNames.add("CheckoutTest");
        testNames.add("PaymentTest");
        for(String names: testNames){
            System.out.println(names);
        }
        if(testNames.contains("CheckoutTest")){
            System.out.println("CheckoutTest found");
        } else {
            System.out.println("Not there");
        }
        int n = testNames.indexOf("PaymentTest");
        System.out.println("The index of PaymentTest is:"+n);
        int m = testNames.indexOf("LogoutTest");
        System.out.println("The index of LogoutTest is:"+m);
        boolean f = testNames.isEmpty();
        System.out.println(f);
        testNames.remove("SearchTest");
        if(!testNames.contains("SearchTest")){
            System.out.println("SearchTest Successfully Removed");
        }
        testNames.clear();
        if(testNames.isEmpty()){
            System.out.println("No Tests remaining");
        }
//        System.out.println("The Test at index 2 is: "+testNames.get(2));
//        testNames.set(1, "ProductSearchTest");
//        System.out.println("Updated value at 1st index is: "+testNames.get(1));
//        testNames.add("LogoutTest");
//        testNames.remove("CheckoutTest");
//        System.out.println("The size of the ArrayList is: "+testNames.size());
//        for(int i =0;i<testNames.size();i++){
//            System.out.println("Test "+i+": "+testNames.get(i));
//        }

    }
}
