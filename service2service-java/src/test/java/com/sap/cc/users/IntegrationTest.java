package com.sap.cc.users;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.cc.ascii.AsciiArtResponse;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {
		"service.ascii.url=http://localhost:8181/api/v1/asciiArt/" })
public class IntegrationTest {

	@Autowired
	private WebTestClient webTestClient;

	@Autowired
	private ObjectMapper objectMapper;

	public static MockWebServer mockBackEnd;

	@BeforeAll
	static void setUp() throws IOException {
		mockBackEnd = new MockWebServer();
		mockBackEnd.start(8181);
	}

	@AfterAll
	static void tearDown() throws IOException {
		mockBackEnd.shutdown();
	}

	@Test
	void returnsPrettyPage() throws JsonProcessingException, InterruptedException {
		// Create the AsciiArtResponse
		AsciiArtResponse asciiArtResponse = new AsciiArtResponse("PrettyName", "comic");

		mockBackEnd.enqueue(new MockResponse().setBody(objectMapper.writeValueAsString(asciiArtResponse)).addHeader(
				org.springframework.http.HttpHeaders.CONTENT_TYPE,
				org.springframework.http.MediaType.APPLICATION_JSON_VALUE));

		webTestClient.get().uri("/api/v1/users/pretty/1").accept(MediaType.APPLICATION_JSON).exchange().expectStatus()
				.isOk().expectBody(String.class).value(body -> {
					assertThat(body).contains("PrettyName\r\n");
				});

		RecordedRequest request = mockBackEnd.takeRequest();
		assertEquals("POST", request.getMethod());
		assertEquals("/api/v1/asciiArt/", request.getPath());
	}
}
