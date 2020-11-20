package org.magnum.dataup;

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
import retrofit.mime.TypedFile;

@Controller
public class VideoController implements VideoSvcApi{

	@Override
	@RequestMapping(value = "/video", method = RequestMethod.GET)
	@ResponseBody
	public Collection<Video> getVideoList(){
		Collection<Video> str = new ArrayList<Video>(); 
		Video video = Video.create().withContentType("video/mp4")
				.withDuration(123).withSubject(UUID.randomUUID().toString())
				.withTitle(UUID.randomUUID().toString()).build();
		str.add(video);
		return str;
	}

	@Override
	public Video addVideo(Video v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VideoStatus setVideoData(long id, TypedFile videoData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getData(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
