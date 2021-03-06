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

		<a href="/" class="navbar-brand">${name}</a>

		<ul class="nav navbar-nav">
			<li class="active"><a href="#">Home</a></li>
			<li><a href="<%= Constants.Pages.LIST_TODOS_BY_USER %>">Todos</a></li>
			<li><a href="http://www.teamliquid.net/" target="_blank">Streaming Service</a></li>
		</ul>

		<ul class="nav navbar-nav navbar-right">
			<li><a href="<%= Constants.Pages.LOGOUT %>">Logout</a></li>
		</ul>

	</nav>

	<div class="container">
		Your New Action Item
		<form method="POST" action="<%= Constants.Pages.ADD_TODO %>">
			<fieldset>
				<label>New Todo:</label>
				<input name="todo" type="text" class="form-control" required/> <br/>
			</fieldset>
			<fieldset>
				<label>Category:</label>
				<input name="category" type="text" class="form-control" required/> <br/>
			</fieldset>
			<fieldset>
				<label>Priority:</label>
				<select id="priority" name="priority" class="form-control">
					<option>LOW</option>
					<option>MED</option>
					<option>HIGH</option>
				</select>
			</fieldset>
			<input name="add" type="submit" class="btn btn-success" value="Submit"/>
		</form>
	</div>

	<footer class="footer">
		<p>footer content</p>
	</footer>

	<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
	<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>

</body>

</html>