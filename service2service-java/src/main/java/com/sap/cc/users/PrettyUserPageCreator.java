package com.sap.cc.users;

import org.springframework.stereotype.Component;

import com.sap.cc.ascii.AsciiArtRequest;
import com.sap.cc.ascii.AsciiArtServiceClient;

import io.netty.util.AsciiString;

@Component
public class PrettyUserPageCreator {

	private AsciiArtServiceClient asciiArtServiceClient;

	public PrettyUserPageCreator(AsciiArtServiceClient asciiArtServiceClient) {
		this.asciiArtServiceClient = asciiArtServiceClient;
	}

	public String getPrettyPage(User user) {
		AsciiArtRequest request = new AsciiArtRequest(user.getName(), user.getFontPreference());

		String asciiArt = asciiArtServiceClient.getAsciiString(request);

		return asciiArt + "\r\n" + user.getPhoneNumber();
	}
}