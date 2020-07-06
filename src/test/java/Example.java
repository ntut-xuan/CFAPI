import java.io.IOException;
import java.util.List;

import cfapi.main.CodeForcesContest;
import cfapi.main.CodeForcesContestData;

public class Example {
	
	public static void main(String[] args) throws IOException {
		CodeForcesContest cf = new CodeForcesContest();
		List<CodeForcesContestData> list = cf.getBeforeContest(false);
		for(CodeForcesContestData data : list) {
			System.out.println(data.getName());
		}
	}

}
