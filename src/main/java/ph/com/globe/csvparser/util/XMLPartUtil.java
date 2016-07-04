package ph.com.globe.csvparser.util;

import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import ph.com.globe.csvparser.constant.MassRequestTypes;
import ph.com.globe.csvparser.constant.XMLDynamicProperty;
import ph.com.globe.csvparser.constant.XMLMassRequestDetails;
import ph.com.globe.csvparser.constant.XMLOptionalConfigurationProperty;
import ph.com.globe.csvparser.constant.XMLSubProductIDElement;
import ph.com.globe.csvparser.constant.XMLTags;
import ph.com.globe.csvparser.constant.XMLMassRequestHeader;

/**
 * 
 * @author rreal
 * This class contains methods that get header parameters that is constant to all MSISDNs
 *
 */
public class XMLPartUtil {
		    
	public Map<String, String> getXmlMassRequestHeaders(String[] tags, String[] values, Integer requestLineSize) {
    	XMLMassRequestHeader headers = new XMLMassRequestHeader();
    	ArrayList<String> xmlMassRequestHeadersList = headers.getXmlMassHeaderList();
    	Map<String, String> xmlMassRequestHeadersMap = headers.getXmlHeaderMap();
    	Map<String, String> headersMap = new HashMap<String, String>();
    	
    	Integer valueIndex = 0;
    	String requestDescription = null;
    	for(String tag: tags) {
    		for(String xmlMassRequestHeader: xmlMassRequestHeadersList) {
    			if(tag.equals(xmlMassRequestHeader)) {
    				if(values[valueIndex].equals(MassRequestTypes.CHANGE_CONFIGURATION)) {
    					requestDescription = "Update";
    				}
    				String xmlMassheader = xmlMassRequestHeadersMap.get(xmlMassRequestHeader);
    				headersMap.put(xmlMassheader, values[valueIndex]);
    				break;
    			}
    		}
    		valueIndex++;
    	}
    	//add request line size
    	headersMap.put("numberOfRequestLines", requestLineSize.toString());
    	
    	if(requestDescription!=null) {
    		headersMap.put("requestDescription", requestDescription);
    		
    	}
    	
    	
		return headersMap;
    	
    }
	
	public void printMassRequestHeader(PrintWriter writer, Map<String, String> xmlMassRequestHeadersMap) {
    	XMLMassRequestHeader header = new XMLMassRequestHeader();
    	String[] headersStringArray = header.getHeadersStringArray();
    	writer.append("\t\t\t\t" + XMLTags.header_massRequestHeader);
    	for(String headerName: headersStringArray) {
        	String value = xmlMassRequestHeadersMap.get(headerName);
        	if(value != null) {
        		writer.append(" " + headerName + "=\"" + value +"\"");
        	}
        }
    	writer.append("/>");
    }
	
	public Map<String, String> getXmlMassRequestDetails(String[] tags, String[] values) {
    	XMLMassRequestDetails details = new XMLMassRequestDetails();
    	String[] xmlMassRequestDetailsList = details.getHeadersMassRequestDetailsStringArray();
    	Map<String, String> detailsMap = new HashMap<String, String>();
    	
    	Integer valueIndex = 0;
    	
    	for(String tag: tags) {
    		for(String detail: xmlMassRequestDetailsList) {
    			if(tag.equals(detail)) {
    				detailsMap.put(tag, values[valueIndex]);
    			}
    		}
    		valueIndex++;
    	}
    	return detailsMap;	
    }  
	
	public void printMassRequestDetails(PrintWriter writer, Map<String, String> xmlMassRequestDetailsMap) {
    	XMLMassRequestDetails header = new XMLMassRequestDetails();
    	String[] headersStringArray = header.getHeadersMassRequestDetailsStringArray();
    	writer.append("\n");
    	writer.append("\t\t\t\t" + XMLTags.header_massRequestDetails);
    	for(String headerName: headersStringArray) {
        	String value = xmlMassRequestDetailsMap.get(headerName);
        	if(value != null) {
        		writer.append(" " + headerName + "=\"" + value +"\"");
        	}
        }
    	writer.append(">");
    }
	
	public void printOptionalConfigurationItem(PrintWriter writer, Map<String,String> xmlMassRequestHeadersMap) {
		String value = (xmlMassRequestHeadersMap.get("massRequestType").equals(MassRequestTypes.CHANGE_CONFIGURATION)) ? "UP" : "AD";
		writer.append("\n\t\t\t\t\t" + XMLTags.header_optionalConfigurationItem + " action=\"" + value +"\">");
    }
	
	public Map<String, String> getXMLSubProductIDElement(String[] tags, String[] values) {
		XMLSubProductIDElement spIdElement = new XMLSubProductIDElement();
    	String[] xmlSubProductIDElementList = spIdElement.getXMLSubProductIDElementStringArray();
    	Map<String, String> xmlSubProductIDElementMap = new HashMap<String, String>();
    	
    	Integer valueIndex = 0;
    	
    	for(String tag: tags) {
    		for(String subproductIdElement: xmlSubProductIDElementList) {
    			if(tag.equals(subproductIdElement)) {
    				xmlSubProductIDElementMap.put(tag, values[valueIndex]);
    			}
    		}
    		valueIndex++;
    	}
    	return xmlSubProductIDElementMap;	
    }
	
	public void printXMLSubProductIDElement(PrintWriter writer, Map<String, String> xmlSubProductIDElementMap) {
		XMLSubProductIDElement spIdElement = new XMLSubProductIDElement();
    	String[] headersStringArray = spIdElement.getXMLSubProductIDElementStringArray();
    	writer.append("\n");
    	writer.append("\t\t\t\t\t\t" + XMLTags.header_subProductIDElement);
    	for(String headerName: headersStringArray) {
        	String value = xmlSubProductIDElementMap.get(headerName);
        	if(value != null) {
        		writer.append(" value=\"" + value +  "\" type=\"" + headerName +"\"/>");
        	}
        }
    }
	
	public Map<String, String> getXMLOptionalConfigurationProperty(String[] tags, String[] values) {
		XMLOptionalConfigurationProperty ocProperty = new XMLOptionalConfigurationProperty();
    	String[] xmlOptionalConfigurationPropertyList = ocProperty.getOptionalConfigurationProperties();
    	Map<String, String> xmlOptionalConfigurationProperty = new HashMap<String, String>();
    	
    	Integer valueIndex = 0;
    	
    	for(String tag: tags) {
    		for(String subproductIdElement: xmlOptionalConfigurationPropertyList) {
    			if(tag.equals(subproductIdElement)) {
    				xmlOptionalConfigurationProperty.put(tag, values[valueIndex]);
    			}
    		}
    		valueIndex++;
    	}
    	return xmlOptionalConfigurationProperty;	
    }
	
	public void printXMLOptionalConfigurationProperty(PrintWriter writer, Map<String, String> xmlOptionalConfigurationProperty) {
		XMLOptionalConfigurationProperty ocProperty = new XMLOptionalConfigurationProperty();
    	String[] headersStringArray = ocProperty.getOptionalConfigurationProperties();
    	for(String headerName: headersStringArray) {
        	String value = xmlOptionalConfigurationProperty.get(headerName);
        	if(value != null) {
        		 writer.append("\n");
                 writer.append("\t\t\t\t\t\t" + XMLTags.header_optionalConfigurationProperty  + " propertyName=\"" + headerName   + "\"" + " propertyValue=\"" + value +"\"/>");
        	}
        }
    }
	
	public Map<String, String> getXMLDynamicProperty(String[] tags, String[] values) {
		XMLDynamicProperty dynamicProperty = new XMLDynamicProperty();
    	String[] xmlDynamicPropertyList = dynamicProperty.getDynamicProperties();
    	Map<String, String> xmlDynamicPropertyMap = new HashMap<String, String>();
    	
    	Integer valueIndex = 0;
    	
    	for(String tag: tags) {
    		for(String xmlDynamicProperty: xmlDynamicPropertyList) {
    			if(tag.equals(xmlDynamicProperty)) {
    				System.out.println("Header: " + tag + ", value: " + values[valueIndex]);
    				xmlDynamicPropertyMap.put(tag, values[valueIndex]);
    			}
    		}
    		valueIndex++;
    	}
    	return xmlDynamicPropertyMap;	
    }
	
	public void printXMLDynamicProperty(PrintWriter writer, Map<String, String> xmlDynamicProperty) {
		XMLDynamicProperty dynamicProperty = new XMLDynamicProperty();
    	String[] headersStringArray = dynamicProperty.getDynamicProperties();
    	for(String headerName: headersStringArray) {
        	String value = xmlDynamicProperty.get(headerName);
        	if(value != null) {
        		 writer.append("\n");
                 writer.append("\t\t\t\t\t" + XMLTags.header_dynamicProperty + " name=\"" + headerName +"\"" + " value=\"" +  value  + "\"/>");
        	}
        }
    }
	
	
}
