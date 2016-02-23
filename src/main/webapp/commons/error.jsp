<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>Error Page</title>
	<script language="javascript">
		function showDetail()
		{
			document.getElementById('detail_error_msg').style.display = "";
		}
	</script>
</head>

<body>

<div id="content">

	<h3>System Runtime Error: <br><%=exception.getMessage()%>
	</h3>
	<br>

	<button onclick="history.back();">Back</button>
	<br>

	<p><a href="#" onclick="showDetail();">Administrator click here to get the detail.</a></p>

	<div id="detail_error_msg" style="display:none">
		<pre><%exception.printStackTrace(new java.io.PrintWriter(out));%></pre>
	</div>
</div>
</body>
</html>