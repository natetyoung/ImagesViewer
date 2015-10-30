package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImageSearch {
	private String id;
	private String key;

	public ImageSearch(String id, String key) {
		this.id = id;
		this.key = key;
	}
	
	public BufferedReader getResponseReader(String q) throws IOException {
		String url = "https://www.googleapis.com/customsearch/v1?"
				+ "key="+key
				+ "&cx="+id
				+ "&q="+q.replace(" ","+")
				+ "&searchType=image";
		return URLStuff.getReader(url);
	}
	
	public String getImageResultJSON(String q){
		String url = "https://www.googleapis.com/customsearch/v1?"
				+ "key="+key
				+ "&cx="+id
				+ "&q="+q
				+ "&searchType=image";
		return URLStuff.getFullResponse(url);
	}
	
	public List<String> getImageURLs(String q){
		List<String> urls = new ArrayList<String>();
		String json = getImageResultJSON(q);
		int i = json.indexOf("\"link\"");
		while(i>-1){
			json = json.substring(i+9);
			urls.add(json.substring(0,json.indexOf("\"")));
			i=json.indexOf("\"link\"");
		}
		return urls;
	}
}
