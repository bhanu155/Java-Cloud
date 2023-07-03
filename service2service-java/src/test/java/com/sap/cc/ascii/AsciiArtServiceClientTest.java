package com.sap.cc.ascii;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.cc.InvalidRequestException;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

public class AsciiArtServiceClientTest {

	public static final AsciiArtRequest WITH_VALID_ARGS = new AsciiArtRequest("HelloWorld", "3");
	public static final AsciiArtRequest WITH_UNKNOWN_FONT_ID = new AsciiArtRequest("handleThis", "9");

	private AsciiArtServiceUrlProvider asciiArtServiceUrlProvider = Mockito.mock(AsciiArtServiceUrlProvider.class);
	private AsciiArtServiceClient asciiArtServiceClient;
	public static MockWebServer mockBackEnd;
	private ObjectMapper objectMapper = new ObjectMapper();

	@BeforeAll
	static void setUp() throws IOException {
		mockBackEnd = new MockWebServer();
		mockBackEnd.start();
	}

	@AfterAll
	static void tearDown() throws IOException {
		mockBackEnd.shutdown();
	}

	@BeforeEach
	void initialize() {
		String serviceUrl = String.format("http://localhost:%s/api/v1/asciiArt/", mockBackEnd.getPort());
		Mockito.when(asciiArtServiceUrlProvider.getServiceUrl()).thenReturn(serviceUrl);
		asciiArtServiceClient = new AsciiArtServiceClient(WebClient.create(), asciiArtServiceUrlProvider);
	}

	@Test
	void whenCallingGetAsciiString_thenClientMakesCorrectCallToService() throws JsonProcessingException {
		String expectedResponse = "'Hello World' as ascii art";

		// Create the AsciiArtResponse
		AsciiArtResponse asciiArtResponse = new AsciiArtResponse(expectedResponse, "comic");

		String responseBody = objectMapper.writeValueAsString(asciiArtResponse);

		// Enqueue a mock response
		mockBackEnd.enqueue(
				new MockResponse().setBody(responseBody).addHeader(org.springframework.http.HttpHeaders.CONTENT_TYPE,
						org.springframework.http.MediaType.APPLICATION_JSON_VALUE));

		// Call the getAsciiString method
		String result = asciiArtServiceClient.getAsciiString(WITH_VALID_ARGS);

		// Verify the response received from the MockWebServer
		assertThat(result).isEqualTo(expectedResponse);

	}

	@Test
	void whenRequestingWithInvalidRequest_thenInvalidRequestExceptionIsThrown() throws JsonProcessingException {
		String expectedResponse = "'Hello World' as ascii art";

		// Enqueue a mock response
		mockBackEnd.enqueue(new MockResponse().setResponseCode(HttpStatus.BAD_REQUEST.value()));

		assertThatThrownBy(() -> asciiArtServiceClient.getAsciiString(WITH_UNKNOWN_FONT_ID))
				.isInstanceOf(InvalidRequestException.class);
	}

}