package pryadeina.conf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import pryadeina.rows.RawDataRow;

public class Parser {
	private String filenameFrom = null;
	private HashMap<String, RawDataRow> asIsMap;

	public Parser() {
		super();
		asIsMap = new HashMap<String, RawDataRow>();
	}

	public String getFilenameFrom() {
		return filenameFrom;
	}

	public void setFilenameFrom(String filenameFrom) {
		this.filenameFrom = filenameFrom;
	}

	public HashMap<String, RawDataRow> getAsIsMap() {
		return asIsMap;
	}

	public void setAsIsMap(HashMap<String, RawDataRow> asIsMap) {
		this.asIsMap = asIsMap;
	}

	public HashMap<String, RawDataRow> Parse() throws IOException {
		int count = 0;
		try (Reader reader = Files.newBufferedReader(Paths.get(filenameFrom));
				CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withDelimiter(';'));) {

			for (CSVRecord csvRecord : csvParser) {
				if (csvRecord.toString() == "") {
					continue;
				}
				// Accessing Values by Column Index
				try {
					String price = csvRecord.get(0);
					String id = csvRecord.get(1);
					String leftOver = csvRecord.get(2);

					if (isNullString(id)) {
						continue;
					}
					if (isNullString(leftOver)) {
						continue;
					}
					if (isNullString(price)) {
						continue;
					}
					if (leftOver.contains(" ")) {
						continue;
					}
					if (id.contains(" ")) {
						continue;
					}
					RawDataRow tmpRow = new RawDataRow();
					try {
						tmpRow.setId(Integer.parseInt(id));
						tmpRow.setCount(Integer.parseInt(leftOver));
						tmpRow.setPrice(Integer.parseInt(price));

					} catch (Exception ex) {
						continue;
					}

					asIsMap.put(id, tmpRow);
					count++;

				} catch (Exception ex) {
					continue;
				}

			}
		}
		System.out.println("The number of asIsMap rows = " + count);
		return asIsMap;
	}

	public void writeFile(String fileTo) throws IOException {
		String finalData = "";
		String semilicon = ";";
		String n = "\r\n";
		System.out.println(fileTo);
		System.out.println("2 The number of asIsMap rows = " + asIsMap.size());
		for (Map.Entry<String, RawDataRow> entry : asIsMap.entrySet()) {
			// System.out.println(entry.toString());
			String key = entry.getKey();
			RawDataRow value = entry.getValue();
			int price = value.getPrice(), leftover = value.getCount();

			if (price != 0 && price > 0 && leftover > 0) {

				finalData += key + semilicon + price + semilicon + leftover + n;

			}

		}
		System.out.println("finalData = " + finalData);

		File outFile = new File(fileTo);

		FileOutputStream outputStreamOne = new FileOutputStream(outFile);

		byte[] strToBytes = finalData.getBytes();

		outputStreamOne.write(strToBytes);

		outputStreamOne.close();
	}

	public boolean isNullString(String string) {
		if (string == null) {
			return true;
		}

		if (string.equals("")) {
			return true;
		}
		if (string.equals(" ")) {
			return true;
		}
		if (string.isEmpty()) {
			return true;
		}
		if (("\"" + string + "\"").equals("\"\"")) {
			return true;
		}
		if (string.length() == 0) {
			return true;
		}
		return false;
	}
}
