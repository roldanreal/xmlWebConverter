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
<body class="container">
<div class="container-fluid">
	<div class="page-header">
      <h1>Convert to XML</h1>
  	</div>
  	<div class="row">
        <form:form modelAttribute="myUploadForm" method="POST" action="" enctype="multipart/form-data">        
        Mass Request Type:
        	<form:select path="massRequestType">
			  <option value="replaceOfferWithBasePlan">Replace Offer with Base Plan</option>
			  <option value="changeConfiguration">Change Configuration</option>
			  <option value="mercedes">Mercedes</option>
			  <option value="audi">Audi</option>
			</form:select>
        	<form:input path="fileDatas" type="file" />
		<br />
		<input type="submit" value="Convert!">
	</form:form>
  	</div>
</div>
</body>
</html>