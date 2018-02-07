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
form.form-inline {
	display: inline;
}
</style>
</head>

<body>

	<nav class="navbar navbar-default">

		<a href="/" class="navbar-brand">${name}</a>

		<ul class="nav navbar-nav">
			<li class="active"><a href="#">Home</a></li>
			<li><a href="<%=Constants.Pages.LIST_TODOS_BY_USER%>">Todos</a></li>
			<li><a href="http://www.teamliquid.net/" target="_blank">Streaming
					Service</a></li>
		</ul>

		<ul class="nav navbar-nav navbar-right">
			<li><a href="<%=Constants.Pages.LOGOUT%>">Logout</a></li>
		</ul>

	</nav>

	<div class="container">
		<div class="container">
			<h1>Welcome ${name}</h1>

			<h2>Your Todos:</h2>
			<table class="table table-striped">
				<tr>
					<th>To Do Items</th>
					<th>Category</th>
					<th>Priority</th>
					<th>Actions</th>
				</tr>
				<tbody>
					<c:forEach items="${todos}" var="todo">
						<tr>
							<td>${todo.name}</td>
							<td>${todo.category}</td>
							<td>${todo.priority}</td>
							<td><a
								href="<%=Constants.Pages.UPDATE_TODO %>?id=${todo.id}"
								class="btn btn-primary">Edit</a>

								<form class="form-inline" action="<%=Constants.Pages.DELETE_TODO%>" method="POST">
									<input type="hidden" name="id" value="${todo.id}" />
									<button type="submit" class="btn btn-danger">Delete</button>
								</form></td>

						</tr>
					</c:forEach>
				</tbody>

			</table>

			<p>
				<font color="red">${errorMessage}</font>
			</p>
			<a class="btn btn-success" href="<%=Constants.Pages.ADD_TODO%>">Add
				a new item to the list</a>
		</div>
	</div>

	<footer class="footer">
		<p>footer content</p>
	</footer>

	<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
	<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>

</body>

</html>