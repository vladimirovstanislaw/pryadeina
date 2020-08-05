package pryadeina.conf;

import pryadeina.getFile.GetFile;

import java.io.IOException;

public class Runable {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
//"C:\\prydeina\\" "C:\\prydeina\\rawData" "Products_Vianor_340_43237.csv" "https://goodsmanager.easykassa.ru/g/export/v1/110b1cc5-1b38-4b33-9420-c8a2974a92c8/csv/" "C:\prydeina" "C:\prydeina\rawData"
		if (args.length != 0) {
			String mainFolder = args[0]; // Корневая папка
			String rawDataFolder = args[1]; // Куда кладем .csv от easyKassa
			String vianorFileName = args[2];
			String link = args[3]; // ссылка на данные easyKassa
			GetFile getFile = new GetFile(link, rawDataFolder);
			getFile.getFile();
			Parser parser = new Parser();
			parser.setFilenameFrom(rawDataFolder + "\\rawData.csv");
			parser.Parse();
			parser.writeFile(mainFolder + "\\" + vianorFileName);
			
			Sender sender = new Sender();// 30 seconds timeout
			sender.setData(mainFolder, vianorFileName);
			sender.send();

		}
	}

}
