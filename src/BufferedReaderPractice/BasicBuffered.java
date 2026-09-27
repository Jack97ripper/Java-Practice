package BufferedReaderPractice;

import java.io.*;
public class BasicBuffered {
    static int numberReceiver(BufferedReader br, String message)throws IOException{
        while(true){
            System.out.println(message);
            try{
                int a = Integer.parseInt(br.readLine());
                if(a>0){
                    return a;
                } else {
                    System.out.println("Enter a valid age");
                }
            } catch (NumberFormatException e){
                System.out.println("Enter a valid number");
            }
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter a name: ");
        String name= br.readLine();
        //System.out.println("Enter age: ");
        int age = numberReceiver(br, "Enter the Age:");
        System.out.println("Name is: "+name+" "+"Age is: "+age);
    }
}
