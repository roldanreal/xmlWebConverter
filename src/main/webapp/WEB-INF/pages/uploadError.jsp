<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<link href="resources/css/bootstrap.min.css" rel="stylesheet">
<link href="resources/css/main.css" rel="stylesheet">
<meta charset="UTF-8">
<title>Upload Error</title>
</head>
<img src="resources/images/globe-logo.jpg" alt="Globe Logo" style="width:250px;height:100px;">
<body class = "container">
<div class="container-fluid" style="color:#191970;font-size:100%;margin-left:2em">
	<br />
	<div >
      <b>Errors!</b>
  	</div>	
  	<br />	
	<span style="color:#191970;font-size:100%">${errorMessage}</span>
	<br />
	<br />
</div>
<input style = "margin-left:2em"" class="btn btn-primary btn-md" type="button" onclick="window.history.back();" value="Back"/>
</body>
</html>

