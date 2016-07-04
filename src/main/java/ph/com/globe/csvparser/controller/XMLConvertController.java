package ph.com.globe.csvparser.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import ph.com.globe.csvparser.constant.MassRequestTypes;
import ph.com.globe.csvparser.file.MyFileUploadForm;
import ph.com.globe.csvparser.xmlconverter.XMLConverter;
 
@Controller
public class XMLConvertController {
 
	private static final Logger LOGGER = LoggerFactory.getLogger(XMLConvertController.class);
	
	private String clientFileName = null;
	private static String downloadPath = null;
	private String result = null;
   
    @InitBinder
    public void myInitBinder(WebDataBinder dataBinder) {
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
        System.out.println("Target=" + target);
 
        if (target.getClass() == MyFileUploadForm.class) {
 
            // Register to handle the conversion between the multipart object
            // and byte array.
            dataBinder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
        }
    }
 
    // GET: Show upload form page.
    @RequestMapping(value = "/uploadOneFile", method = RequestMethod.GET)
    public String uploadOneFileHandler(Model model) {
 
        MyFileUploadForm myUploadForm = new MyFileUploadForm();
        model.addAttribute("myUploadForm", myUploadForm);
 
        // Forward to "/WEB-INF/pages/uploadOneFile.jsp".
        return "uploadOneFile";
    }
 
    // POST: Do Upload
    @RequestMapping(value = "/uploadOneFile", method = RequestMethod.POST)
    public String uploadOneFileHandlerPOST(HttpServletRequest request, //
            Model model, //
            @ModelAttribute("myUploadForm") MyFileUploadForm myUploadForm) {
 
        return this.doUpload(request, model, myUploadForm);
 
    }
 
    private String doUpload(HttpServletRequest request, Model model, //
            MyFileUploadForm myUploadForm) {
 
    	String massRequestType = myUploadForm.getMassRequestType();
    	System.out.println("Mass request type: " + massRequestType);
        String uploadRootPath = request.getServletContext().getRealPath("upload");
        System.out.println("uploadRootPath=" + uploadRootPath);
 
        File uploadRootDir = new File(uploadRootPath);
        //
        // Create directory if it not exists.
        if (!uploadRootDir.exists()) {
            uploadRootDir.mkdirs();
        }
        CommonsMultipartFile[] fileDatas = myUploadForm.getFileDatas();
        //
        List<File> uploadedFiles = new ArrayList<File>();
        for (CommonsMultipartFile fileData : fileDatas) {
 
      
            // Client File Name
        	clientFileName = fileData.getOriginalFilename();
            System.out.println("Client File Name = " + clientFileName);
 
            if (clientFileName != null && clientFileName.length() > 0) {
                try {
                    // Create the file on server
                    File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + clientFileName);
  
                    // Stream to write data to file in server.
                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                    stream.write(fileData.getBytes());
                    stream.close();
                    //
                    uploadedFiles.add(serverFile);
                    System.out.println("Write file: " + serverFile);
                } catch (Exception e) {
                    System.out.println("Error Write file: " + clientFileName);
                }
            }
        }
        model.addAttribute("uploadedFiles", uploadedFiles);
        
        String src = uploadRootPath + "\\" + clientFileName;
        
        //call the method
        String errorMsg = convertToXML(request, model, src, clientFileName, massRequestType);
        
        model.addAttribute("errorMessage", errorMsg);
        
        if(errorMsg.isEmpty()){
        	result = "uploadResult";
        }else{
        	result = "uploadError";
        }
     
		return result;        
    }
    
    private String convertToXML(HttpServletRequest request, Model model,
    		String csvSource, String fileName, String massRequestType) {
    	String errorMsg = "";
    	XMLConverter converter = new XMLConverter();
    	
    	System.out.println("MASS REQUEST TYPE AGAIN: " + massRequestType);
    	System.out.println("Src file: " + csvSource);
    	System.out.println("File name: " + fileName);
    	
    	String fileNameWoExtension = fileName.split(Pattern.quote("."))[0]; //first in the array created
    	
    	System.out.println("name of file without extension: " + fileNameWoExtension);
    	
    	//target path
    	String targetRootPath = request.getServletContext().getRealPath("target");
        System.out.println("uploadRootPath=" + targetRootPath);
 
        File targetRootDir = new File(targetRootPath);
        //
        // Create directory if it not exists.
        if (!targetRootDir.exists()) {
        	targetRootDir.mkdirs();
        }
    	
    	//starts here
    	//File csvFile = new File(new StringBuilder("COPY_").append(Math.random()).append(fileNameWoExtension).toString());
        downloadPath = targetRootPath+File.separatorChar+fileNameWoExtension+".xml";
        System.out.println("Path: " + downloadPath);
        
        System.out.println("Mass request type: " + massRequestType);
        
        if(massRequestType.equals(MassRequestTypes.REPLACE_OFFER_WITH_BASE_PLAN)) {
        	converter.convertToXML_replaceOfferWithBasePlan(csvSource, downloadPath, fileNameWoExtension);
        } else if(massRequestType.equals(MassRequestTypes.CHANGE_CONFIGURATION)) {
        	errorMsg = converter.convertToXML_changeConfiguration(csvSource, downloadPath, fileNameWoExtension);
        }
        return errorMsg;

    }
    
    @RequestMapping(value="/downloadXMLFile")
    public void getXMLFile(HttpSession session,HttpServletResponse response, //
            MyFileUploadForm myUploadForm) throws Exception {
        try {
            //String filePathToBeServed = myUploadForm.getDownloadPath();
            
            System.out.println("Download path: " + downloadPath);
            File fileToDownload = new File(downloadPath);
            InputStream inputStream = new FileInputStream(fileToDownload);
            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment; filename="+fileToDownload.getName()); 
            
            System.out.println("File to download: " + fileToDownload.getName());
            
            IOUtils.copy(inputStream, response.getOutputStream());
            response.flushBuffer();
            inputStream.close();
        } catch (Exception e){
            LOGGER.debug("Request could not be completed at this moment. Please try again.");
            e.printStackTrace();
        }

    }
 
}