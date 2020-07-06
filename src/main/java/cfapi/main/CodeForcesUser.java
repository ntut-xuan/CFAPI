package cfapi.main;

import java.io.IOException;
import java.net.URL;

import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class CodeForcesUser {

	String[] StringDataKey = { "handle", "email", "vkId", "openId", "firstName", "lastName", "country", "city",
			"organization", "rank	", "maxRank" };
	String[] StringDataValue = new String[11];

	String[] LongIntegerDataKey = { "contribution", "rating", "maxRating", "lastOnlineTimeSeconds",
			"registrationTimeSeconds", "friendOfCount" };
	long[] LongIntegerValue = new long[6];

	String[] URLDataKey = { "avatar", "titlePhoto" };
	URL[] URLDataValue = new URL[2];

	public CodeForcesUser(String name) throws IOException, NoUserException {
		String url = "http://codeforces.com/api/user.info?handles=";
		Connection connection = Jsoup.connect(url + name);
		connection.ignoreContentType(true);
		connection.followRedirects(false);
		Document doc;
		try {
			doc = connection.get();
		} catch (HttpStatusException e) {
			throw new NoUserException("Cannot find such as user.");
		}
		String text = doc.text();
		JsonElement element = new JsonParser().parse(text);
		JsonObject object = element.getAsJsonObject();
		String status = object.get("status").getAsString();
		if (status.equals("FAILED"))
			throw new NoUserException("Cannot find such as user.");
		JsonObject result = object.get("result").getAsJsonArray().get(0).getAsJsonObject();
		for (int i = 0; i < StringDataValue.length; i++) {
			JsonElement subElement = result.get(StringDataKey[i]);
			if (subElement == null) {
				StringDataValue[i] = null;
			} else {
				StringDataValue[i] = result.get(StringDataKey[i]).getAsString();
			}
		}
		for (int i = 0; i < LongIntegerDataKey.length; i++) {
			JsonElement subElement = result.get(LongIntegerDataKey[i]);
			if (subElement == null) {
				LongIntegerValue[i] = 0;
			} else {
				LongIntegerValue[i] = result.get(LongIntegerDataKey[i]).getAsLong();
			}
		}
		for (int i = 0; i < URLDataKey.length; i++) {
			JsonElement subElement = result.get(URLDataKey[i]);
			if (subElement == null) {
				URLDataValue[i] = null;
			} else {
				URLDataValue[i] = new URL("https:" + result.get(URLDataKey[i]).getAsString());
			}
		}
	}

	public String getHandle() {
		return StringDataValue[0];
	}

	public String getEmail() {
		return StringDataValue[1];
	}

	public String getVKID() {
		return StringDataValue[2];
	}

	public String getOpenID() {
		return StringDataValue[3];
	}

	public String getFirstName() {
		return StringDataValue[4];
	}

	public String getLastName() {
		return StringDataValue[5];
	}

	public String getCountry() {
		return StringDataValue[6];
	}

	public String getCity() {
		return StringDataValue[7];
	}

	public String getOrganization() {
		return StringDataValue[8];
	}

	public String getRank() {
		return StringDataValue[9];
	}

	public String getMaxRank() {
		return StringDataValue[10];
	}

	public long getContribution() {
		return LongIntegerValue[0];
	}

	public long getRating() {
		return LongIntegerValue[1];
	}

	public long getMaxRating() {
		return LongIntegerValue[2];
	}

	public long getLastOnlineTimeSeconds() {
		return LongIntegerValue[3];
	}

	public long getRegistrationTimeSeconds() {
		return LongIntegerValue[4];
	}

	public long getFriendOfCount() {
		return LongIntegerValue[5];
	}

	public URL getAvaterURL() {
		return URLDataValue[0];
	}

	public URL getTitlePhotoURL() {
		return URLDataValue[1];
	}

}
