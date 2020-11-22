package org.magnum.dataup;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.io.File;
import java.io.FileInputStream;

import org.magnum.dataup.model.Video;
import org.magnum.dataup.model.VideoStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.Path;
import retrofit.mime.TypedFile;

@Controller
public class VideoController implements VideoSvcApi{

	@Override
	@RequestMapping(value = "/video", method = RequestMethod.GET)
	@ResponseBody
	public Collection<Video> getVideoList(){

		List<Video> videos = new ArrayList<Video>();
		try {
			VideoFileManager videoFileManager = VideoFileManager.get();
			videos = videoFileManager.getVideos();		
			
		} catch (IOException e) {
			//return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			e.printStackTrace();
		}
	
		return videos;
	}

	@Override
	@RequestMapping(value = "/video", method = RequestMethod.POST)
	@ResponseBody
	public Video addVideo(@RequestBody Video v) {
		VideoFileManager videoFileManager;
		try {
			videoFileManager = VideoFileManager.get();
			
			v = videoFileManager.addVideo(v);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return v;
	}

	@Override
	@Multipart
	//@RequestMapping(value = VIDEO_DATA_PATH, method = RequestMethod.POST)
	public VideoStatus setVideoData(@Path(ID_PARAMETER) long id, @Part(DATA_PARAMETER) TypedFile videoData) {
		//assure id is in database
		//assign pathID to video
		//import video 
		
		VideoFileManager videoFileManager;
		try {
			videoFileManager = VideoFileManager.get();
		
			if (videoFileManager.jsonHandler.isIDPresent(id)) {
				videoFileManager.jsonHandler.updateLocation(id);
				Video v = videoFileManager.jsonHandler.getVideo(id);
				videoFileManager.saveVideoData(v, videoData.in());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		


		return null;
	}

	@Override
	public Response getData(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
