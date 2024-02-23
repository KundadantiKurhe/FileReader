package com.file.CSVDemoservice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.files.FileReader.repository.UserRepository;
import com.files.entity.User;

@Service
public class FileServiceImpl implements FileService {

	@Autowired
	private UserRepository repository;

	@Override
	public boolean hasCsvFormat(MultipartFile file) {
		String type = "text/csv";
		if (!type.equals(file.getContentType())) {
			return false;
		}
		return true;
	}

	@Override
	public void processAndSaveData(MultipartFile file) {
		try {
			List<User> users = csvToUsers(file.getInputStream());
			repository.saveAll(users);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
String line=" ";
	private List<User> csvToUsers(InputStream inputStream) {
		BufferedReader br = new BufferedReader(new FileReader("src/main/resources/CsvFile.csv"));
		while((line=br.readLine( ) )!= null)
		{
			String [] data = line.split(",");
		//	User u =new User();
			
		}
		
		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
				CSVParser csvParser = new CSVParser(fileReader, 
						CSVFormat.DEFAULT.withFirstRecordAsHeader().	withIgnoreHeaderCase(). withTrim());) {

			List<User> users = new ArrayList<User>();
			List<CSVRecord> records = csvParser.getRecords();
			for (CSVRecord csvRecord : records) {
				User user = new User(Long.parseLong(csvRecord.get("id")), csvRecord.get("ftname"),
						csvRecord.get("ltname"), csvRecord.get("email"));
				users.add(user);
			}
			return users;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
