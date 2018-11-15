package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;

public class Main {

    public static void main(String[] args) throws IOException {
        // ÄLÄ laita githubiin omaa opiskelijanumeroasi
        String studentNr = "012345678";
        if ( args.length>0) {
            studentNr = args[0];
        }

        String url = "https://studies.cs.helsinki.fi/courses/students/"+studentNr+"/submissions";

        String bodyText = Request.Get(url).execute().returnContent().asString();
        
        String courseText = Request.Get("https://studies.cs.helsinki.fi/courses/courseinfo").execute().returnContent().asString();

        System.out.println("Opiskelijanumero: " + studentNr);
        //System.out.println( bodyText );

        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);
        Courses[] courses = mapper.fromJson(courseText, Courses[].class);
        
        
        System.out.println(" ");
        for (Submission submission : subs) {
            
            
            for(Courses course : courses){
                
                if(course.getName().equals(submission.getCourse())){
                int tunnit = 0;
                int tehtavat = 0; 
                    System.out.println(course.getFullName() + " " + course.getTerm()+ " " + course.getYear());
                    System.out.println("");
                    //System.out.println(submission);
                    int i = 0;
                    for (Submission submission2 : subs) {
                        
                        if(submission2.getCourse().equals(course.getName())){
                            tunnit = tunnit + submission2.getHours();
                            tehtavat = tehtavat + submission2.getExercises().length;
                            submission2.setMaxExercises(course.getMaxExercisesByWeek(submission2.getWeek()));
                            System.out.println(submission2);
                            subs[i].setCourse("KÄYTY");
                            
                            
                        }
                        i++;
                    }
                    System.out.println("");
                    System.out.println("Yhteensa: "+ tehtavat + "/" +course.getMaxExercises() + " tehtavaa, " + tunnit+ " tuntia.");
                    System.out.println("");
                }
            }
            
            
            
            //System.out.println(submission);
        }
        
        System.out.println("");
        //System.out.println("Yhteensa: " +tehtavat + " tehtavaa, " + tunnit+ " tuntia.");

    }
}