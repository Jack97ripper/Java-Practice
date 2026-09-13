package Package1;

import java.util.*;
public class ExcerciseArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //define no.of exercises
        System.out.println("Enter the number of exercises: ");
        int n = sc.nextInt();

        //define the duration and deadline Array
        int[] duration = new int[n];
        int[] deadline = new int[n];

        int time = 0;
        int count = 0;

        //enter elements in duration array
        for(int i=0;i<n;i++){
            System.out.println("Enter the duration array elements: ");
            duration[i]=sc.nextInt();
        }

        //enter elements in deadline array
        for(int i=0;i<n;i++){
            System.out.println("Enter the deadline array elements: ");
            deadline[i]=sc.nextInt();
        }

        //Calculate no.of exercises possible
        for(int i=0;i<n;i++){
            time=time+duration[i];
            if(time<=deadline[i]){
                count++;
            }else {
                break;
            }
        }
        System.out.println("The total number of exercises possible: "+count);
        //release resources
        sc.close();

    }

}
