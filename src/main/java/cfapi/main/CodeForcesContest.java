package cfapi.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class CodeForcesContest {
	
	HashMap<Integer, CodeForcesContestData> map = new HashMap<Integer,CodeForcesContestData>();
	
	public CodeForcesContest() throws IOException {
		String url = "https://codeforces.com/api/contest.list?gym=false";
		Connection connection = Jsoup.connect(url).ignoreContentType(true).followRedirects(false);
		Document doc = connection.get();
		String text = doc.text();
		
		JsonObject mainObject = new JsonParser().parse(text).getAsJsonObject();
		JsonArray array = mainObject.get("result").getAsJsonArray();
		for(int i = 0; i < array.size(); i++) {
			JsonObject object = array.get(i).getAsJsonObject();
			String ID= object.get("id").getAsString();
			String name = object.get("name").getAsString();
			String type = object.get("type").getAsString();
			String phase = object.get("phase").getAsString();
			boolean frozen = object.get("frozen").getAsBoolean();
			long durationSeconds = object.get("durationSeconds").getAsInt();
			long startTimeSeconds = object.get("startTimeSeconds").getAsInt();
			long relativeTimeSeconds = object.get("relativeTimeSeconds").getAsInt();
			boolean russianOnly = name.replaceAll("[^a-zA-Z0-9-!@#$%^*()., ]", "").length() < name.length();
			CodeForcesContestData cfc = new CodeForcesContestData(ID,name,type,phase,durationSeconds,startTimeSeconds,relativeTimeSeconds,frozen, russianOnly);
			map.put(Integer.parseInt(ID), cfc);
		}
	}
	
	public CodeForcesContestData getContestData(int ID) {
		return map.get(ID);
	}
	
	public Set<Entry<Integer, CodeForcesContestData>> getContestEntrySet(){
		return map.entrySet();
	}
	
	public HashMap<Integer, CodeForcesContestData> getContestMap(){
		return map;
	}
	
	public List<CodeForcesContestData> getBeforeContest(boolean russiaOnly){
		List<CodeForcesContestData> list = new ArrayList<CodeForcesContestData>();
		for(Entry<Integer, CodeForcesContestData> entry : getContestEntrySet()) {
			CodeForcesContestData cfcd = entry.getValue();
			if(cfcd.getPhase().equals("BEFORE")) {
				if(russiaOnly == false && cfcd.russianOnly() == true) continue;
				list.add(entry.getValue());
			}
		}
		Collections.reverse(list);
		return list;
	}
	
}
