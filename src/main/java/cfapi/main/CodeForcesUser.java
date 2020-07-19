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

	String handle, country, city, organization, rank, maxRank;
	long rating, maxRating;
	URL titlePhoto;

	public CodeForcesUser(String name) throws IOException, NoUserException {
		String url = "http://codeforces.com/api/user.info?handles=";
		Connection connection = Jsoup.connect(url + name);
		connection.ignoreContentType(true);
		connection.followRedirects(false);
		Document doc;
		try {
			doc = connection.get();
		} catch (HttpStatusException e) {
			e.printStackTrace();
			throw new NoUserException("Cannot find such as user.");
		}
		String text = doc.text();
		JsonElement element = new JsonParser().parse(text);
		JsonObject object = element.getAsJsonObject();
		String status = object.get("status").getAsString();
		if (status.equals("FAILED"))
			throw new NoUserException("Cannot find such as user.");

		JsonObject result = object.get("result").getAsJsonArray().get(0).getAsJsonObject();
		JsonElement handleObject = result.get("handle");
		JsonElement countryObject = result.get("country");
		JsonElement cityObject = result.get("city");
		JsonElement organizationObject = result.get("organization");
		JsonElement maxRatingObject = result.get("maxRating");
		JsonElement maxRankObject = result.get("maxRank");
		JsonElement ratingObject = result.get("rating");
		JsonElement rankObject = result.get("rank");
		JsonElement titlePhotoObject = result.get("titlePhoto");

		handle = handleObject == null ? "Unknown" : handleObject.getAsString();
		country = countryObject == null ? "Unknown" : countryObject.getAsString();
		city = cityObject == null ? "Unknown" : cityObject.getAsString();
		organization = organizationObject == null ? "Unknown" : organizationObject.getAsString();
		maxRating = maxRatingObject == null ? -1L : maxRatingObject.getAsLong();
		maxRank = maxRankObject == null ? "Unrated" : maxRankObject.getAsString();
		rating = ratingObject == null ? -1L : ratingObject.getAsLong();
		rank = rankObject == null ? "Unrated" : rankObject.getAsString();
		titlePhoto = titlePhotoObject == null ? null : new URL("http:" + titlePhotoObject.getAsString());

		handle = handle.equals("")  ? "Unknwon" : handle;
		country = country.equals("") ? "Unknown" : country;
		city = city.equals("") ? "Unknown" : city;
		organization = organization.equals("") ? "Unknown" : organization;
		maxRank = maxRank.equals("") ? "Unknown" : maxRank;
		rank = rank.equals("") ? "Unknown" : rank;

	}

	public String getHandle() {
		return handle;
	}

	public String getCountry() {
		return country;
	}

	public String getCity() {
		return city;
	}

	public String getOrganization() {
		return organization;
	}

	public String getRank() {
		return rank;
	}

	public String getMaxRank() {
		return maxRank;
	}

	public long getRating() {
		return rating;
	}

	public long getMaxRating() {
		return maxRating;
	}

	public URL getTitlePhotoURL() {
		return titlePhoto;
	}

}
