import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import cfapi.main.*;
import org.jsoup.nodes.Document;

public class Example {

	public static void main(String[] args) throws IOException {
		CodeForcesProblemSet problemSet = new CodeForcesProblemSet();
		Document doc = problemSet.getDoc();
		String text = doc.text();
		List<CodeForcesProblemData> list = CodeForcesProblemSet.make(text);
		for(CodeForcesProblemData problemData : list){
			System.out.println(problemData.getName() + " " + problemData.getRating());
		}
	}

}
