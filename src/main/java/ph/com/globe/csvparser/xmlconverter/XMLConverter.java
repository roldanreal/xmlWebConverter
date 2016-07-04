/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.com.globe.csvparser.xmlconverter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import ph.com.globe.csvparser.constant.XMLTags;
import ph.com.globe.csvparser.util.XMLPartUtil;

/**
 *
 * 
 */
public class XMLConverter {

    private String requestCreationDt;
    private String requestExecutionDt;
    private String dateFormat;
    private Date creationDate;
    private Date executionDate;
    private Boolean isCreationDtValid;
    private Boolean isExecutionDtValid;
    private String lockInStart;
    private String lockInEnd;
    private Date lockInStartDate;
    private Date lockInEndDate;
    private Boolean isLockInStartDateValid;
    private Boolean isLockInEndDateValid;
    
    public String convertToXML_changeConfiguration(String csvSource, String destination, String fileName) {//GEN-FIRST:event_convertActionPerformed
    	XMLPartUtil util = new XMLPartUtil();
    	//File xmlFile = null;
    	String errorMsg = "";
    	
    	System.out.println("File destination: " + destination);

        try {
            if(fileName!=null){
                File excelFile = new File(csvSource);
                BufferedReader br = new BufferedReader(new FileReader(excelFile));
                String str;
                List<String> listStr = new ArrayList<String>();
                List<String> listValue = new ArrayList<String>();
                
                //List of MSISDNs
                List<String> listMsisdn = new ArrayList<String>();
                while((str = br.readLine())!=null){
                    listStr.add(str);
                }
                
                //Tags are now stored in this array of Strings
                String[] tags = listStr.get(0).split("\\,");
                
                //Get second line of the code and split to get the required values
                String[] values = listStr.get(1).split("\\,");

                //Add to the list of values
                for(int i=1;i<listStr.size();i++){
                    listValue.add(listStr.get(i));               	
                }
 
                //List of MSISDNs
                for (String value: listValue) {
                	String msisdnValue = value.split("\\,")[(value.split("\\,").length)-1];
                	//Add MSISDN to the arraylist
                	if(msisdnValue != null)
                		listMsisdn.add(msisdnValue);
                }
                
                Map<String, String> xmlMassRequestHeadersMap = util.getXmlMassRequestHeaders(tags, values, listMsisdn.size());
                Map<String, String> xmlMassRequestDetailsMap = util.getXmlMassRequestDetails(tags, values); 
                Map<String, String> xmlSubProductIDElementMap = util.getXMLSubProductIDElement(tags, values);
                Map<String, String> xmlOptionalConfigurationProperty = util.getXMLOptionalConfigurationProperty(tags, values);
                Map<String, String> xmlDynamicPropertyMap = util.getXMLDynamicProperty(tags, values);
                
              //here
            	Iterator<Map.Entry<String,String>> iterator = xmlMassRequestHeadersMap.entrySet().iterator();
            	requestCreationDt = xmlMassRequestHeadersMap.get("requestCreationDate");
            	requestExecutionDt = xmlMassRequestHeadersMap.get("requestExecutionDate");
            	dateFormat = xmlMassRequestHeadersMap.get("dateFormat");
            	lockInStart = xmlMassRequestHeadersMap.get("LOCK_IN_START_DATE");
            	lockInEnd = xmlMassRequestHeadersMap.get("LOCK_IN_END_DATE");
            	System.out.println("Request creation date: " + requestCreationDt);
            	System.out.println("Request requestExecutionDate : " + requestExecutionDt);
            	System.out.println("Request dateFormat: " + dateFormat);
            	System.out.println("Request lockInStart: " + lockInStart);
            	System.out.println("Request lockInEnd: " + lockInEnd);
            	
            	if(dateFormat!=null) {
            		if (requestCreationDt!=null) {
            			if(!isValidDateFormat(dateFormat, requestCreationDt)){
                        	errorMsg = errorMsg.concat("Invalid request creation date format. <br/>");
                        }else{
                        	isCreationDtValid = true;
                        }
            		}
            		if(requestExecutionDt!=null) {
                        if(!isValidDateFormat(dateFormat,requestExecutionDt)){
                        	errorMsg = errorMsg.concat("Invalid request execution date format. <br/>");
                        }else{
                        	isExecutionDtValid = true;
                        }
            		}
            		if(lockInStart!=null) {
                      if(!isValidDateFormat(dateFormat,lockInStart)){
                    	errorMsg = errorMsg.concat("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Invalid lock in start date format. <br/>");
                      }else{
                    	  isLockInStartDateValid = true;
                      }
            		}
            	}

                if(isValidDateFormat(dateFormat,lockInEnd)== false){
                	errorMsg = errorMsg.concat("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Invalid lock in end date format. <br/>");
                }else{
                	isLockInEndDateValid = true;
                }
                
                if(isCreationDtValid && isExecutionDtValid){
                	creationDate = convertStringToDate(dateFormat, requestCreationDt);
                	executionDate = convertStringToDate(dateFormat, requestExecutionDt);
                	
                	 if(creationDate.compareTo(executionDate)>0){
                         errorMsg = errorMsg.concat("Request Creation Date is greater than Request Execution Date.");
                     }
                }
                
                if(isLockInStartDateValid && isLockInEndDateValid){
                    lockInStartDate = convertStringToDate(dateFormat, lockInStart);
                	lockInEndDate = convertStringToDate(dateFormat, lockInEnd);
                	
                	 if(lockInStartDate.compareTo(lockInEndDate)>0){
                         errorMsg = errorMsg.concat("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Lock In Start Date is greater than Lock In End Date");
                     }
                }
            	//ends here
                System.out.println("file destination: " + destination);
                File xmlFile = new File(destination);
                xmlFile.createNewFile();
                
                PrintWriter writer = new PrintWriter(xmlFile);
                writer.append(XMLTags.header_handleMassOperation_top);
                writer.append("\n");
                writer.append("\t" + XMLTags.header_interface);
                writer.append("\n");
                writer.append("\t\t" + XMLTags.header_handleMassOperation);
                writer.append("\n");
                writer.append("\t\t\t" + XMLTags.header_handleMassOperationRequest);
                writer.append("\n");
               
                //Add MassRequestHeader parameters
                util.printMassRequestHeader(writer, xmlMassRequestHeadersMap);

                //Add MassRequestDetails header and its parameters
                util.printMassRequestDetails(writer, xmlMassRequestDetailsMap);
                
                //Add OptionalConfigurationItem and its parameter
                util.printOptionalConfigurationItem(writer, xmlMassRequestHeadersMap);
                
                //Add SubProductIDElement and its parameter
                util.printXMLSubProductIDElement(writer, xmlSubProductIDElementMap);
                
                //Add OptionalConfigurationProperty and its parameter
                util.printXMLOptionalConfigurationProperty(writer, xmlOptionalConfigurationProperty);
                
                writer.append("\n");
                writer.append("\t\t\t\t\t" +  XMLTags.footer_optionalConfigurationItem);
                
                //Add DynamicProperty and its paramater
                util.printXMLDynamicProperty(writer, xmlDynamicPropertyMap);
                
                writer.append("\n");
                writer.append("\t\t\t\t" +  XMLTags.footer_massRequestDetails);
                
                for (int j =0; j < listMsisdn.size(); j++) {
                	writer.append("\n");
                	writer.append("\t\t\t\t" +  XMLTags.header_massLineInfo + " requestLineNumber=\"" + (j+1) + "\">");
                	writer.append("\n");
                	writer.append("\t\t\t\t\t" +  XMLTags.header_idElement + " value=\"" + listMsisdn.get(j) + "\" type=\"" + tags[tags.length-1] + "\">");
                	writer.append("\n");
                	writer.append("\t\t\t\t" +  XMLTags.footer_massLineInfo);
                }
                writer.append("\n");
                writer.append("\t\t\t" +  XMLTags.footer_handleMassOperationRequest);
                writer.append("\n");
                writer.append("\t\t" + XMLTags.footer_handleMassOperation);
                writer.append("\n");
                writer.append("\t" + XMLTags.footer_interface);
                writer.append("\n");        
                
                writer.append(XMLTags.footer_context);
                
                //close reader
                br.close();
                
                //close writer
                writer.close();
               
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return errorMsg;
		//return xmlFile;
		   
    }//GEN-LAST:event_convertActionPerformed
    
    
    public void convertToXML_replaceOfferWithBasePlan(String csvSource, String destination, String fileName) {//GEN-FIRST:event_convertActionPerformed
    	XMLPartUtil util = new XMLPartUtil();
    	File xmlFile = null;
    	String errorMsg = "";
        try {
            if(fileName!=null){
                File excelFile = new File(csvSource);
                BufferedReader br = new BufferedReader(new FileReader(excelFile));
                String str;
                List<String> listStr = new ArrayList<String>();
                List<String> listValue = new ArrayList<String>();
                
                //List of MSISDNs
                List<String> listMsisdn = new ArrayList<String>();
                while((str = br.readLine())!=null){
                    listStr.add(str);
                }
                
                //close reader
                br.close();
                
                String[] tags = listStr.get(0).split("\\,");
                
                //Get second line of the code and split to get the required values
                String[] values = listStr.get(1).split("\\,");
                
                //Add to the list of values
                for(int i=1;i<listStr.size();i++){
                    listValue.add(listStr.get(i));               	
                }
 
                //List of MSISDNs
                for (String value: listValue) {
                	String msisdnValue = value.split("\\,")[(value.split("\\,").length)-1];
                	//Add MSISDN to the arraylist
                	if(msisdnValue != null)
                		listMsisdn.add(msisdnValue);
                }
                
                Map<String, String> xmlMassRequestHeadersMap = util.getXmlMassRequestHeaders(tags, values, listMsisdn.size());
                Map<String, String> xmlMassRequestDetailsMap = util.getXmlMassRequestDetails(tags, values); 
                Map<String, String> xmlSubProductIDElementMap = util.getXMLSubProductIDElement(tags, values);
                Map<String, String> xmlOptionalConfigurationProperty = util.getXMLOptionalConfigurationProperty(tags, values);
                Map<String, String> xmlDynamicPropertyMap = util.getXMLDynamicProperty(tags, values);
                
                //here
            	Iterator<Map.Entry<String,String>> iterator = xmlMassRequestHeadersMap.entrySet().iterator();
            	requestCreationDt = xmlMassRequestHeadersMap.get("requestCreationDate");
            	requestExecutionDt = xmlMassRequestHeadersMap.get("requestExecutionDate");
            	dateFormat = xmlMassRequestHeadersMap.get("dateFormat");
            	lockInStart = xmlMassRequestHeadersMap.get("LOCK_IN_START_DATE");
            	lockInEnd = xmlMassRequestHeadersMap.get("LOCK_IN_END_DATE");
            	System.out.println("Request creation date: " + requestCreationDt);
            	System.out.println("Request requestExecutionDate : " + requestExecutionDt);
            	System.out.println("Request dateFormat: " + dateFormat);
            	System.out.println("Request lockInStart: " + lockInStart);
            	System.out.println("Request lockInEnd: " + lockInEnd);
            	
            	if(dateFormat!=null) {
            		if (requestCreationDt!=null) {
            			if(!isValidDateFormat(dateFormat, requestCreationDt)){
                        	errorMsg = errorMsg.concat("Invalid request creation date format. <br/>");
                        }else{
                        	isCreationDtValid = true;
                        }
            		}
            		if(requestExecutionDt!=null) {
                        if(!isValidDateFormat(dateFormat,requestExecutionDt)){
                        	errorMsg = errorMsg.concat("Invalid request execution date format. <br/>");
                        }else{
                        	isExecutionDtValid = true;
                        }
            		}
            		if(lockInStart!=null) {
                      if(!isValidDateFormat(dateFormat,lockInStart)){
                    	errorMsg = errorMsg.concat("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Invalid lock in start date format. <br/>");
                      }else{
                    	  isLockInStartDateValid = true;
                      }
            		}
            	}

                if(isValidDateFormat(dateFormat,lockInEnd)== false){
                	errorMsg = errorMsg.concat("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Invalid lock in end date format. <br/>");
                }else{
                	isLockInEndDateValid = true;
                }
                
                if(isCreationDtValid && isExecutionDtValid){
                	creationDate = convertStringToDate(dateFormat, requestCreationDt);
                	executionDate = convertStringToDate(dateFormat, requestExecutionDt);
                	
                	 if(creationDate.compareTo(executionDate)>0){
                         errorMsg = errorMsg.concat("Request Creation Date is greater than Request Execution Date.");
                     }
                }
                
                if(isLockInStartDateValid && isLockInEndDateValid){
                    lockInStartDate = convertStringToDate(dateFormat, lockInStart);
                	lockInEndDate = convertStringToDate(dateFormat, lockInEnd);
                	
                	 if(lockInStartDate.compareTo(lockInEndDate)>0){
                         errorMsg = errorMsg.concat("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Lock In Start Date is greater than Lock In End Date");
                     }
                }
            	//ends here
                xmlFile = new File(destination);
                xmlFile.createNewFile();
                PrintWriter writer = new PrintWriter(xmlFile);
                writer.append(XMLTags.header_handleMassOperation_top);
                writer.append("\n");
                writer.append("\t" + XMLTags.header_interface);
                writer.append("\n");
                writer.append("\t\t" + XMLTags.header_handleMassOperation);
                writer.append("\n");
                writer.append("\t\t\t" + XMLTags.header_handleMassOperationRequest);
                writer.append("\n");
               
                //Add MassRequestHeader parameters
                util.printMassRequestHeader(writer, xmlMassRequestHeadersMap);

                //Add MassRequestDetails header and its parameters
                util.printMassRequestDetails(writer, xmlMassRequestDetailsMap);
                
                //Add OptionalConfigurationItem and its parameter
                util.printOptionalConfigurationItem(writer, xmlMassRequestHeadersMap);
                
                //Add SubProductIDElement and its parameter
                util.printXMLSubProductIDElement(writer, xmlSubProductIDElementMap);
                
                //Add OptionalConfigurationProperty and its parameter
                util.printXMLOptionalConfigurationProperty(writer, xmlOptionalConfigurationProperty);
                
                writer.append("\n");
                writer.append("\t\t\t\t\t" +  XMLTags.footer_optionalConfigurationItem);
                
                //Add DynamicProperty and its paramater
                util.printXMLDynamicProperty(writer, xmlDynamicPropertyMap);
                
                writer.append("\n");
                writer.append("\t\t\t\t" +  XMLTags.footer_massRequestDetails);
                
                for (int j =0; j < listMsisdn.size(); j++) {
                	writer.append("\n");
                	writer.append("\t\t\t\t" +  XMLTags.header_massLineInfo + " requestLineNumber=\"" + (j+1) + "\">");
                	writer.append("\n");
                	writer.append("\t\t\t\t\t" +  XMLTags.header_idElement + " value=\"" + listMsisdn.get(j) + "\" type=\"" + tags[tags.length-1] + "\">");
                	writer.append("\n");
                	writer.append("\t\t\t\t" +  XMLTags.footer_massLineInfo);
                }
                writer.append("\n");
                writer.append("\t\t\t" +  XMLTags.footer_handleMassOperationRequest);
                writer.append("\n");
                writer.append("\t\t" + XMLTags.footer_handleMassOperation);
                writer.append("\n");
                writer.append("\t" + XMLTags.footer_interface);
                writer.append("\n");        
                
                writer.append(XMLTags.footer_context);
                
                //close writer
                writer.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		//return xmlFile;
		   
    }//GEN-LAST:event
    
    public static boolean isValidDateFormat(String format, String value) {
    	
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(value);           
            if (!value.equals(sdf.format(date))) {
                date = null;
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        if (date !=null){
            return true;
        }else{
        	return false;        	
        }
        
    }
    
    public static Date convertStringToDate(String format, String value){
    	Date date = null;
    	try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(value);
                  
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
		return date;
    	
    }
}
