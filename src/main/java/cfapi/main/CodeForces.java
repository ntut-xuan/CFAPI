package cfapi.main;

import java.io.IOException;

public class CodeForces {
	
	public CodeForcesUser getUser(String str) throws IOException, NoUserException {
		return new CodeForcesUser(str);
	}

}
