<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="webapp.util.Constants"%>
<!DOCTYPE html>
<html>
<head>
<title>Todos</title>
<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet">

<style>
	.footer {
		position: absolute;
		bottom: 0;
		width: 100%;
		height: 60px;
		background-color: #f5f5f5;
	}
</style>
</head>

<body>

	<nav class="navbar navbar-default">

		<a href="/" class="navbar-brand">Brand</a>

		<ul class="nav navbar-nav">
			<li class="active"><a href="#">Home</a></li>
			<li><a href="<%= Constants.Pages.LIST_TODOS_BY_USER %>">Todos</a></li>
			<li><a href="http://udemy.com/">Udemy</a></li>
		</ul>

		<ul class="nav navbar-nav navbar-right">
			<li><a href="<%= Constants.Pages.REGISTER %>">Register</a></li>
			<li><a href="<%= Constants.Pages.LOGIN %>">Login</a></li>
		</ul>

	</nav>

	<div class="container">
		<form action="<%= Constants.Pages.LOGIN %>" method="POST">
			<p><font color="red">${errorMessage}</font></p>
			Username: <input type="text" name="username"/>
			<br/>
			Password: <input type="password" name="password"/>
			<br/>
			<input type="Submit" value="Submit"/>
		</form>
	</div>

	<footer class="footer">
		<p>footer content</p>
	</footer>

	<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
	<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>

</body>

</html>




