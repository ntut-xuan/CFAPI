package cfapi.main;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class CodeForcesStatus {

    Document doc;
    String user;
    List<CodeForcesSubmissionData> list;

    public CodeForcesStatus(String user) throws IOException {
        this.user = user;
        String url = "https://codeforces.com/api/user.status?handle=" + user;
        doc = Jsoup.connect(url).ignoreContentType(true).maxBodySize(0).get();
    }

    public Document getDoc(){
        return doc;
    }

    public static List<CodeForcesSubmissionData> make(String text) {
        List<CodeForcesSubmissionData> list = new ArrayList<>();
        JsonElement element = new JsonParser().parse(text);
        JsonObject mainObject = element.getAsJsonObject();
        JsonArray array = mainObject.get("result").getAsJsonArray();
        for (int i = 0; i < array.size(); i++) {
            JsonObject object = array.get(i).getAsJsonObject();
            JsonObject problem = object.get("problem").getAsJsonObject();
            String contestID = "";
            if (problem.get("contestId") == null) {
                contestID = "987654321";
            } else {
                contestID = problem.get("contestId").getAsString();
            }
            String index = problem.get("index").getAsString();
            String verdict = object.get("verdict").getAsString();
            long creationTime = object.get("creationTimeSeconds").getAsLong();
            String rating = "";
            if (problem.get("rating") == null) {
                rating = "0";
            } else {
                rating = problem.get("rating").getAsString();
            }
            CodeForcesSubmissionData codeForcesSubmissionData = new CodeForcesSubmissionData(contestID, index, rating, verdict, creationTime);
            list.add(codeForcesSubmissionData);
        }
        return list;
    }

    public void save(){
        try {
            File file = new File("UserData");
            if(!file.exists()) file.mkdir();
            file = new File("UserData/" + user);
            if(!file.exists()) file.mkdir();
            file = new File("UserData/" + user + "/Submission.json");
            if(!file.exists()) file.createNewFile();
            PrintWriter pw = new PrintWriter(file);
            String data = doc.text();
            pw.println(data);
            pw.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public List<CodeForcesSubmissionData> getSubmissionData(){
        return list;
    }

}
