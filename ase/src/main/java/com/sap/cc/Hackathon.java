package com.sap.cc;

import java.util.List;
import com.sap.cc.handsoff.*;

public class Hackathon implements DeveloperEvent {

	@Override
	public String codeTogether(List<CodeCreator> codeCreators) {

		StringBuilder response = new StringBuilder();
		for (CodeCreator creator : codeCreators) {
			String codeRes;
			try {
				codeRes = creator.code();
				
			} catch (UnsupportedDevelopmentLanguageException e) {
				codeRes = e.getMessage();
			}
			response.append(codeRes + "\n");

		}
		return response.toString();
	}

}
