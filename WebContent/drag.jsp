<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>drag</title>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css">
<script src="js/jquery.js"></script>
<script src="http://code.jquery.com/ui/1.12.0-rc.1/jquery-ui.js"></script>
<script>
$(document).ready(function(){
   $('#draggable').draggable();
});
</script>
</head>
<body>
<div id="draggable" class="ui-widget-content">
   <p><img src="Penguins.jpg"></p>
</div>
</body>
</html>