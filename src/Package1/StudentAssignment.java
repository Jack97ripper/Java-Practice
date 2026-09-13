package Package1;

import java.util.*;
public class StudentAssignment {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //Initialize the number of assignments
        int n = sc.nextInt();
        System.out.println("The number of assignments are: "+n);

        //Create two arrays for assignment time and dealine
        int[] assignmenttime=new int[n];
        int[] deadline= new int[n];

        //Input values to assignmenttime
        for(int i=0;i<n;i++){
            System.out.println("Enter assignment time");
            assignmenttime[i]=sc.nextInt();
        }

        //Input values to deadline
        for(int i=0;i<n;i++){
            System.out.println("Enter deadline time");
            deadline[i]=sc.nextInt();
        }

        //Initialize counters for time taken and assignment count
        int time=0;
        int count=0;

        //calculate number of assignments completed and total deadline
        for(int i=0;i<n;i++){
            time = time+assignmenttime[i];
            if(time<=deadline[i]){
                count++;
            } else {
                break;
            }
        }
        System.out.println("The number of assignments completed: "+count);

        //release resources
        sc.close();
    }
}
