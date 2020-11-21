package org.magnum.dataup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import org.magnum.dataup.model.Video;
import org.magnum.dataup.model.VideoStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import retrofit.client.Response;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.mime.TypedFile;

@Controller
public class VideoController implements VideoSvcApi{

	@Override
	@RequestMapping(value = "/video", method = RequestMethod.GET)
	@ResponseBody
	public Collection<Video> getVideoList(){
		Collection<Video> videos = new ArrayList<Video>(); 
		try {
			VideoFileManager videoFileManager = VideoFileManager.get();
			videos = videoFileManager.jsonHandler.getVideos();
		} catch (IOException e) {
			//return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			e.printStackTrace();
		}
		
		
		//for testing purpose
		Video v = Video.create().withContentType("video/mp4")
				.withDuration(123).withSubject(UUID.randomUUID().toString())
				.withTitle(UUID.randomUUID().toString()).build();
		try {
			VideoFileManager videoFileManager;
			videoFileManager = VideoFileManager.get();
			videoFileManager.jsonHandler.addVideo(v);
			videoFileManager.jsonHandler.updateMeta();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return videos;
	}

	@Override
	@RequestMapping(value = "/video", method = RequestMethod.POST)
	@ResponseBody
	public Video addVideo(Video v) {
		
		VideoFileManager videoFileManager;
		try {
			videoFileManager = VideoFileManager.get();
			videoFileManager.jsonHandler.addVideo(v);
			videoFileManager.jsonHandler.updateMeta();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return v;
	}

	@Override
	@Multipart
	@RequestMapping(value = VIDEO_DATA_PATH, method = RequestMethod.POST)
	public VideoStatus setVideoData(long id, TypedFile videoData) {
		Collection<Video> videos = new ArrayList<Video>(); 
		try {
			VideoFileManager videoFileManager = VideoFileManager.get();
			videos = videoFileManager.jsonHandler.getVideos();
		} catch (IOException e) {
			//return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
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
