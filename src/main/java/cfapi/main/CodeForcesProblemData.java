package cfapi.main;

import java.util.List;

public class CodeForcesProblemData {
	
	String name;
	int rating;
	String index;
	String contestID;
	List<String> tagList;
	
	public CodeForcesProblemData(String name, String index, String contestID, int rating, List<String> tagList) {
		this.name = name;
		this.rating = rating;
		this.tagList = tagList;
		this.contestID = contestID;
		this.index = index;
	}
	
	public String getName() {
		return name;
	}
	
	public String getIndex() {
		return index;
	}
	
	public String getContestID() {
		return contestID;
	}
	
	public int getRating() {
		return rating;
	}
	
	public List<String> getTagList() {
		return tagList;
	}
	
}
