package pl.mobilization.speakermeter.downloader;

import java.net.URI;

import pl.mobilization.speakermeter.dao.Speaker;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SpeakerListDownloader extends AbstractDownloader<Speaker[]> {
	private static final String URL = "http://mobilization.herokuapp.com/speakers/";

	public SpeakerListDownloader() {
	}

	public void processAnswer(String json) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
		final Speaker[] speakerFromJson = gson.fromJson(json, Speaker[].class);
		setResult(speakerFromJson);
	}

	@Override
	public URI createURI() {
		return URI.create(URL);
	}
}