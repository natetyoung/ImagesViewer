package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;

public class URLStuff {
	public static BufferedReader getReader(String url) throws IOException{
		URL reader = new URL(url);
		return new BufferedReader(new java.io.InputStreamReader(reader.openStream()));
	}
	
	public static String getFullResponse(String url){
		BufferedReader br;
		String all = "";
		try{
			br = getReader(url);
			String str = br.readLine();
			while(str!=null){
				all = all+str;
				str = br.readLine();
			}
		} catch(IOException e){
			e.printStackTrace();
			all = "There was an error";
		}
		return all;
	}
}
