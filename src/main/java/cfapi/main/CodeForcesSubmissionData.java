package cfapi.main;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CodeForcesSubmissionData {

    String contestID;
    String index;
    String rating;
    String verdict;
    long creationTime;

    public CodeForcesSubmissionData(String contestID, String index, String rating, String verdict, long creationTime){
        this.contestID = contestID;
        this.index = index;
        this.rating = rating;
        this.verdict = verdict;
        this.creationTime = creationTime;
    }

    public String getContestID(){
        return contestID;
    }

    public String getIndex(){
        return index;
    }

    public String getRating(){
        return rating;
    }

    public String getVerdict(){
        return verdict;
    }

    public String getProblemID(){
        return contestID + index;
    }

    public long getCreationTime(){
        return creationTime;
    }

    public String getTime(){
        DateTime dateTime = new DateTime(creationTime * 1000);
        dateTime = dateTime.withZone(DateTimeZone.forID("Asia/Taipei"));
        return dateTime.toString("yyyy-MM-dd");
    }

}
