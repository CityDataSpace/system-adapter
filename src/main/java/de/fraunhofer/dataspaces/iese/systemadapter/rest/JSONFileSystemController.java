package de.fraunhofer.dataspaces.iese.systemadapter.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import de.fraunhofer.dataspaces.iese.systemadapter.entity.JSON;


@RestController
@RequestMapping("/storage/filesystem")
public class JSONFileSystemController {

	@PostMapping("/{file_name}")
	public void saveFile(@PathVariable String file_name, @RequestBody JSON json) throws IOException {
		FileWriter fileWriter = new FileWriter(file_name);
		
		fileWriter.append(json.toString());
		
		fileWriter.close();
	}
	
	@GetMapping("/{file_name}")
	public String readFile(@PathVariable String file_name) throws FileNotFoundException, IOException {
		
		String contents = "";
		
		FileReader fileReader = new FileReader(file_name);
		BufferedReader br = new BufferedReader(fileReader);
		
		String line = br.readLine();
		
		while(line != null) {
			contents += line;
			line = br.readLine();
		}
		
		br.close();
		fileReader.close();
		
		return contents;
	}
}
