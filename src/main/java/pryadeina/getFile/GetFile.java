package pryadeina.getFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetFile {
	String link, rawDataFolder;

	public GetFile(String link, String rawDataFolder) {
		this.link = link;
		this.rawDataFolder = rawDataFolder;

	}

	public void getFile() throws IOException {
		URL url = new URL(this.link);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		int status = con.getResponseCode();

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer content = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			content.append(inputLine+"\n");
		}
		in.close();
		System.out.println(this.rawDataFolder+"\\rawData.csv");
		File outFile = new File(this.rawDataFolder+"\\rawData.csv"); 

		FileOutputStream outputStreamOne = new FileOutputStream(outFile);

		byte[] strToBytes = content.toString().getBytes();

		outputStreamOne.write(strToBytes);

		outputStreamOne.close();

	}

}
