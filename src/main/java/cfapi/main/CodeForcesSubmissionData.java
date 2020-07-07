package cfapi.main;

public class CodeForcesSubmissionData {

    String contestID;
    String index;
    String rating;
    String verdict;

    public CodeForcesSubmissionData(String contestID, String index, String rating, String verdict){
        this.contestID = contestID;
        this.index = index;
        this.rating = rating;
        this.verdict = verdict;
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

}
