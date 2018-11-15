package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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
                    String courseInfo = Request.Get("https://studies.cs.helsinki.fi/courses/"+course.getName()+"/stats").execute().returnContent().asString();
                    
                    JsonParser parser = new JsonParser();
                    JsonObject parsittuData = parser.parse(courseInfo).getAsJsonObject();
                    

                    int palautukset = 0;
                    int palautetutTehtavat = 0;
                    int totalTime = 0;
                    for(int j=1;j<=parsittuData.size();j++){
                        palautukset += parsittuData.get(""+j).getAsJsonObject().get("students").getAsInt();
                        palautetutTehtavat += parsittuData.get(""+j).getAsJsonObject().get("exercise_total").getAsInt();
                        totalTime += parsittuData.get(""+j).getAsJsonObject().get("hour_total").getAsInt();
                    }
                    System.out.println("Kurssilla yhteensa " + palautukset + " palautusta, palautettuja tehtavia " + palautetutTehtavat + " kpl, aikaa kaytetty yht " + totalTime + " tuntia.");
                    System.out.println("");
                }
            }
            
            
            
            //System.out.println(submission);
        }
        
        System.out.println("");
        //System.out.println("Yhteensa: " +tehtavat + " tehtavaa, " + tunnit+ " tuntia.");

    }
}