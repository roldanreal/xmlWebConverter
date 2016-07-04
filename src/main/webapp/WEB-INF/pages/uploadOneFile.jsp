<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Upload One File</title>

<!-- JQuery -->
<script src="resources/js/jquery/jquery-2.1.4.min.js"></script>
    
<!-- Bootstrap -->
<link href="resources/css/bootstrap.min.css" rel="stylesheet">
<script src="resources/js/plugin/bootstrap-3.3.6-dist/bootstrap.min.js"></script>
 
<!-- custom resources -->
<link href="resources/css/main.css" rel="stylesheet">
<script src="resources/js/main.js"></script>

</head>
<img src="resources/images/globe-logo.jpg" alt="Globe Logo" style="width:250px;height:100px;">
<body class="container">
<div class="container-fluid">
	<div class="page-header">
      <b style="color:#191970;font-size:200%">CSV to XML Converter</b>
  	</div>
  	<div class="row">
        <form:form modelAttribute="myUploadForm" method="POST" action="" enctype="multipart/form-data">        
	        <b style="color:#191970;font-size:100%">Mass Request Type:</b>
	        	<form:select path="massRequestType">
				  <option value="ReplaceOfferWithBasePlan">Replace Offer with Base Plan</option>
				  <option value="ChangeConfiguration">Change Configuration</option>
				</form:select>
				<br/>
				<br/>
	        	<form:input path="fileDatas" type="file" />
			<br/>
			<input class="btn btn-primary btn-md" type="submit" value="Convert!">
	</form:form>
  	</div>
</div>
</body>
</html>