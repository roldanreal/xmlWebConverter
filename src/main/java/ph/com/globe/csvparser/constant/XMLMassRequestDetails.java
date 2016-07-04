package ph.com.globe.csvparser.constant;

public class XMLMassRequestDetails {
	private static final String salesChannel = "salesChannel";
	private static final String reason = "reason";
	private static final String serviceRequiredDate = "serviceRequiredDate";
	private static final String BypassProvisioning = "BypassProvisioning";
	private static final String SkipCaseCreation = "SkipCaseCreation";
	
	private String[] headersMassRequestDetailsStringArray = {salesChannel, reason, serviceRequiredDate, BypassProvisioning, SkipCaseCreation};

	public String[] getHeadersMassRequestDetailsStringArray() {
		return headersMassRequestDetailsStringArray;
	}
	
}