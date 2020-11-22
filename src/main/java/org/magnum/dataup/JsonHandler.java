package org.magnum.dataup;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.magnum.dataup.model.Video;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import cognitect.transit.Writer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class provides a simple Handler to convert JSON data
 * to Java 
 * 
 * @author Olivier
 *
 */
public class JsonHandler {
	
	private List<Video>  currentVideos = new ArrayList<Video>();
	private Path targetDir_ = Paths.get("videos/videoMETA");
	ObjectMapper mapper;
	
	public JsonHandler() throws IOException {
		
		BufferedReader reader = new BufferedReader(new FileReader(targetDir_.toString()));
		StringBuilder jsonString = new StringBuilder();;
		String currentLine;
		
		while((currentLine = reader.readLine())!=null) {
			jsonString.append(currentLine);
		}
		
		reader.close();
		mapper = new ObjectMapper();
		
		if (jsonString.length() == 0){
			currentVideos = new ArrayList<Video>();
		}else {
			
			List<Video> temp = (List<Video>) Arrays.asList(mapper.readValue(jsonString.toString(), Video[].class));
			currentVideos = new ArrayList<Video>(temp);
		}
	}
	
	public List<Video> getVideos(){
		return currentVideos;
	}
	
	public void addVideo(Video newVideo) {
		currentVideos.add(newVideo);
	}
	
	public void updateMeta() throws IOException {
		String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(currentVideos);
		BufferedWriter  writer = new BufferedWriter(new FileWriter(targetDir_.toString()));
		writer.write(json);
		writer.close();
	}
	

}
