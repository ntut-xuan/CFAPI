package cfapi.main;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class CodeForcesProblemSet {

	Document doc;

	public CodeForcesProblemSet() throws IOException {
		String url = "https://codeforces.com/api/problemset.problems";
		Connection connection = Jsoup.connect(url).ignoreContentType(true).maxBodySize(0);
		doc = connection.get();
	}

	public Document getDoc(){
		return doc;
	}

	public static List<CodeForcesProblemData> make(String text) {
		JsonObject mainObject = new JsonParser().parse(text).getAsJsonObject();
		JsonObject subObject = mainObject.get("result").getAsJsonObject();
		JsonArray array = subObject.get("problems").getAsJsonArray();
		List<CodeForcesProblemData> list = new ArrayList<>();
		for (int i = 0; i < array.size(); i++) {
			JsonObject obj = array.get(i).getAsJsonObject();
			JsonElement ratingElement = obj.get("rating");
			if (ratingElement == null) continue;
			int rating = ratingElement.getAsInt();
			String name = obj.get("name").getAsString();
			String index = obj.get("index").getAsString();
			String contestID = obj.get("contestId").getAsString();
			List<String> tag = new ArrayList<>();
			JsonArray tagArray = obj.get("tags").getAsJsonArray();
			for (int j = 0; j < tagArray.size(); j++) {
				tag.add(tagArray.get(j).getAsString());
			}
			CodeForcesProblemData cfpd = new CodeForcesProblemData(name, index, contestID, rating, tag);
			list.add(cfpd);
		}
		return list;
	}

	public void saveProblemList(){
		try {
			File file = new File("problemSet.txt");
			PrintWriter pw = new PrintWriter(file);
			String data = doc.text();
			pw.println(data);
			pw.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
