package Package1;

class Employee {
    private int employeeId;
    private String employeeName;
    private int scores[];
    public int getEmployeeId(){return employeeId;}
    public String getEmployeeName(){return employeeName;}
    public void setEmployeeId(int employeeId){
        if(employeeId>0){
            this.employeeId=employeeId;
            System.out.println("Valid");
        } else {
            System.out.println("Invalid");
        }
    }
    public void setEmployeeName(String employeeName){
        this.employeeName=employeeName;
    }
    public int[] getScores(){return scores.clone();}
    public void setScores(int[] scores){
        this.scores=scores.clone();
    }
    Employee(int employeeId, String employeeName, int[] scores){
        this.employeeId=employeeId;
        this.employeeName=employeeName;
        this.scores=scores.clone();
    }
    public int averageScore(){
        int average;
        int sum=0;
        for(int i=0;i<scores.length;i++){
            sum=sum+scores[i];
        }
        average=sum/scores.length;
        return average;
    }

    public String performance(){
        int average = averageScore();
        String perf;
        if(average>=90){
            perf="Excellent";
        } else if(average>=75) {
            perf="Good";
        } else if(average>=60){
            perf="average";
        } else {
            perf="Needs Improvement";
        }
        return perf;
    }

public static void main(String[] args){

        int[] scores={90, 99, 76, 89, 98};
        int[] scores1={65, 78, 90, 89, 99};
        int[] scores2={88, 87, 75, 78, 95};
        Employee[] employee = new Employee[3];
        employee[0]= new Employee(101, "Robertooooooooo", scores);
        employee[1]= new Employee(102, "NoLove", scores1);
        employee[2]= new Employee(103, "MakeMeFeelNothing", scores2);

        int highestAvg=0;
        Employee topPerformer =null;
        for(int i=0; i< employee.length;i++){
            System.out.println("Employee: "+employee[i].getEmployeeName() + "\nAverage: "+employee[i].averageScore()+"\nPerformance: "+employee[i].performance());

            if(employee[i].averageScore() > highestAvg){
                highestAvg=employee[i].averageScore();
                topPerformer=employee[i];

            }
        }
        System.out.println("Top Performer: "+topPerformer.getEmployeeName() + "\nHighest Average: "+highestAvg);

}

}
