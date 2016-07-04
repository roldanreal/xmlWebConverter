package ph.com.globe.csvparser.constant;

public class XMLTags {
	public static final String header_handleMassOperation_top= "HandleMassOperation|~|<?xml version=\"1.0\" encoding=\"UTF-8\"?>";    
    public static final String header_interface="<Interface xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"OmsInterface.xsd\">";
    public static final String header_handleMassOperation = "<HandleMassOperation>";
    public static final String header_handleMassOperationRequest = "<HandleMassOperationRequest>";
    public static final String header_massRequestHeader = "<MassRequestHeader";
    public static final String header_massRequestDetails = "<MassRequestDetails";
    public static final String header_optionalConfigurationItem = "<OptionalConfigurationItem";
    public static final String header_optionalConfigurationProperty = "<OptionalConfigurationProperty";
    public static final String header_subProductIDElement = "<SubProductIDElement";
    public static final String header_dynamicProperty = "<DynamicProperty";
    public static final String header_massLineInfo = "<MassLineInfo";
    public static final String header_idElement = "<IDElement";
    
    public static final String footer_massLineInfo = "</MassLineInfo>";
    public static final String footer_massRequestDetails = "</MassRequestDetails>";
    public static final String footer_optionalConfigurationItem = "</OptionalConfigurationItem>";
    public static final String footer_handleMassOperationRequest = "</HandleMassOperationRequest>";
    public static final String footer_handleMassOperation = "</HandleMassOperation>";
    public static final String footer_interface="</Interface>|~|<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
    public static final String footer_context = "<Context xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"OmsContext.xsd\" requestTime=\"2016-02-22T01:01:01\" userID=\"vineetpa\"/>";
   
}
