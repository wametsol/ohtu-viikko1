package ohtu;

public class Submission {
    private int week;
    private String course;
    private int hours;
    private int[] exercises;

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int[] getExercises() {
        return exercises;
    }

    public void setExercises(int[] exercises) {
        this.exercises = exercises;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
    public void setWeek(int week) {
        this.week = week;
    }
    

    public int getWeek() {
        return week;
    }
    
    public String exerciseList(){
        String list = "";
        for(int i=1;i<exercises.length;i++){
            list = list + ", " + exercises[i];
        }
        return list;
    }
    
    
    

    @Override
    public String toString() {
        return ""+course + ", viikko " +week + ". Aikaa kului: " + hours + ". Tehtyja tehtavia yhteensa " + exercises.length + ", tehdyt tehtavat: " + exercises[0] + exerciseList();
    }
    
}