package com.sap.cc;

import com.sap.cc.handsoff.CodeCreator;
import com.sap.cc.handsoff.UnsupportedDevelopmentLanguageException;

public class Developer extends CodeCreator {
	private String name, language;

	// constructor
	public Developer(String name, String language) {
		super();
		this.name = name;
		this.language = language;
	}

	// getters/setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	// inherited method
	@Override
	public String code() throws UnsupportedDevelopmentLanguageException {

		String response = "";
		UnsupportedDevelopmentLanguageException langException = new UnsupportedDevelopmentLanguageException(language);

		if (this.language.equals("go")) {
			response = "fmt.Println(\"Hello, " + name + "!\")";
		} else if (this.language.equals("nodejs")) {
			response = "console.log(\"Hello, " + name + "!\")";
		} else if (this.language.equals("python")) {
			response = "print(\"Hello, " + name + "!\")";
		} else {
			throw langException;
		}

		return response;
	}

}
