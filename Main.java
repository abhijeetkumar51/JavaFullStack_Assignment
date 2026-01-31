 abstract class Student {
    private int rollNumber;
    private String name;
    private int semester;
    static String universityName = "Lovely Professional University";
    final int MAX_SEMESTER =8;
    static{
        System.out.println("University Name: "+universityName);
    }
    
    public Student(int rollNumber, String name, int semester){
        this.rollNumber = rollNumber;
        this.semester = semester;
        this.name = name;
    }
    public int getRollNumber(){
        return rollNumber;
    }
    public String getName(){
        return name;
    }
    public int getSemester(){
        return semester;
    }
    public void setSemester(int semester){
        if(semester > 0 && semester <= 8){
            this.semester = semester;
        }else{
            System.out.println("Semeter input is not valid");
        }
    }
    public void display(){
        System.out.println("Roll Number: "+rollNumber);
        System.out.println("Name: "+name);
        System.out.println("Semester: "+semester);
        System.out.println("University Name: "+universityName);
    }
    final void showRules(){
        System.out.println("Rules: Attendance is mandatory. Follow university discipline.");
    }

    static void displayUniversityName(){
        System.out.println("University Name: "+universityName);
    }
    abstract double calculateGrade();
    // public static void main(String[] args){
    //     Student s = new Student(8,"Abhijeet",5);
    //     s.display();
    //     s.setSemester(9);
    // }
}
class EngineeringStudents extends Student{
    private int internalMarks;
    private int externalMarks;
    EngineeringStudents(int rollNumber, String name, int semester, int internalMarks, int externalMarks )
    {
        super(rollNumber, name, semester);
        this.internalMarks = internalMarks;
        this.externalMarks = externalMarks;


    }
    @Override
    double calculateGrade(){
        return (internalMarks + externalMarks)/2.0;
    }
}
class MedicalStudents extends Student{
    private int theoryMarks;
    private int practicalMarks;
    MedicalStudents(int rollNumber, String name , int semester, int theoryMarks, int practicalMarks){
        super(rollNumber, name , semester);
        this.theoryMarks = theoryMarks;
        this.practicalMarks= practicalMarks;
    }
    @Override
    double calculateGrade(){
        return (theoryMarks*0.6)+(practicalMarks*0.4);
    }
}
interface SportsParticipant{
    void playSport();
}
interface CulturalParticipant{
    void performActivity();
}

class AllRounderStudent extends Student implements SportsParticipant, CulturalParticipant{
    private int academicMarks;
    AllRounderStudent(int rollNumber, String name, int semester, int academicMarks){
        super(rollNumber, name, semester);
        this.academicMarks=academicMarks;
    }
    @Override
    double calculateGrade(){
        return academicMarks;
    }
    @Override
    public void playSport(){
        System.out.println(getName()+" is playing sports.");
    }
    public void performActivity(){
        System.out.println(getName()+" is performing cultural activity.");
    }
}







public class Main{
    public static void main(String[] args){
        Student eng = new EngineeringStudents(8,"Abhijeet",5,81,95);
        Student med = new MedicalStudents(9,"Shubham",5,70,72);
        eng.display();
        eng.showRules();
        System.out.println("Engineering Grade: "+eng.calculateGrade());
        System.out.println();
        med.display();
        med.showRules();
        System.out.println("Medical Grade: "+med.calculateGrade());
        SportsParticipant sp = new AllRounderStudent(10,"Anant",5,90);
        sp.playSport();
        AllRounderStudent ar = new AllRounderStudent(11,"Amar",5,98);
        ar.playSport();
        ar.performActivity();
        System.out.println();
        System.out.println("Polymorphism a nd Late Binding.");
        Student s1, s2, s3;
        s1 = new EngineeringStudents(12,"Raunak",7,70,85);
        s2 = new MedicalStudents(13,"sujal",7,65,75);
        s3 = new AllRounderStudent(14,"sahil",7,88);
        System.out.println(s1.getName()+"'s Grade: "+s1.calculateGrade());
        System.out.println(s2.getName()+"'s Grade: "+s2.calculateGrade());
        System.out.println(s3.getName()+"'s Grade: "+s3.calculateGrade());
        System.out.println();
        System.out.println("Interface Reference Polymorphism");
        SportsParticipant spRef;
        spRef = new AllRounderStudent(15,"himanshu",5,92);
        spRef.playSport();



        //Student.displayUniversityName();

    }
}
