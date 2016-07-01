package ph.com.globe.csvparser.constant;

import java.util.ArrayList;

public class XMLMassRequestHeader {
	private static final String EXTERNAL_MASS_REQUEST_ID = "EXTERNAL_MASS_REQUEST_ID";
	private static final String MASS_REQUEST_TYPE = "MASS_REQUEST_TYPE";
	private static final String REQUEST_EXECUTION_DATE = "REQUEST_EXECUTION_DATE";
	private static final String REQUEST_CREATOR = "REQUEST_CREATOR";
	private static final String REQUEST_CREATION_DATE = "REQUEST_CREATION_DATE";
	private static final String DATE_FORMAT = "DATE_FORMAT";
	
	private ArrayList<String> xmlMassHeaderList = new ArrayList<String>();

	public ArrayList<String> getXmlMassHeaderList() {
		xmlMassHeaderList.add(EXTERNAL_MASS_REQUEST_ID);
		xmlMassHeaderList.add(MASS_REQUEST_TYPE);
		xmlMassHeaderList.add(REQUEST_EXECUTION_DATE);
		xmlMassHeaderList.add(REQUEST_CREATOR);
		xmlMassHeaderList.add(REQUEST_CREATION_DATE);
		xmlMassHeaderList.add(DATE_FORMAT);
		
		return xmlMassHeaderList;
	}	
}
