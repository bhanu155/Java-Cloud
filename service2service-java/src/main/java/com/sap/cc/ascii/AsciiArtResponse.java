package com.sap.cc.ascii;

public class AsciiArtResponse {

	private String beautifiedText;
	private String fontName;

	public AsciiArtResponse() {
		// Default constructor required by Jackson
	}

	public AsciiArtResponse(String beautifiedText, String fontName) {
		this.beautifiedText = beautifiedText;
		this.fontName = fontName;
	}

	public String getBeautifiedText() {
		return beautifiedText;
	}

	public String getFontName() {
		return fontName;
	}

	public void setBeautifiedText(String beautifiedText) {
		this.beautifiedText = beautifiedText;
	}

	public void setFontName(String fontName) {
		this.fontName = fontName;
	}

}