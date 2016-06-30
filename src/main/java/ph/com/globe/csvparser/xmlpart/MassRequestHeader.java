package ph.com.globe.csvparser.xmlpart;

/**
 * 
 * @author rreal
 *
 * About the class: This class holds the data for XML header which is the same for every file
 * 
 */
public class MassRequestHeader {
	private String externalMassRequestID;
	private String massRequestType;
	private String requestCreator;
	private String requestCreationDate;
	private String numberOfRequestLines;
	private String requestExecutionDate;
	private String DATE_FORMAT;
	
	public String getExternalMassRequestID() {
		return externalMassRequestID;
	}
	public void setExternalMassRequestID(String externalMassRequestID) {
		this.externalMassRequestID = externalMassRequestID;
	}
	public String getMassRequestType() {
		return massRequestType;
	}
	public void setMassRequestType(String massRequestType) {
		this.massRequestType = massRequestType;
	}
	public String getRequestCreator() {
		return requestCreator;
	}
	public void setRequestCreator(String requestCreator) {
		this.requestCreator = requestCreator;
	}
	public String getRequestCreationDate() {
		return requestCreationDate;
	}
	public void setRequestCreationDate(String requestCreationDate) {
		this.requestCreationDate = requestCreationDate;
	}
	public String getNumberOfRequestLines() {
		return numberOfRequestLines;
	}
	public void setNumberOfRequestLines(String numberOfRequestLines) {
		this.numberOfRequestLines = numberOfRequestLines;
	}
	public String getRequestExecutionDate() {
		return requestExecutionDate;
	}
	public void setRequestExecutionDate(String requestExecutionDate) {
		this.requestExecutionDate = requestExecutionDate;
	}
	public String getDATE_FORMAT() {
		return DATE_FORMAT;
	}
	public void setDATE_FORMAT(String dATE_FORMAT) {
		DATE_FORMAT = dATE_FORMAT;
	}
}
