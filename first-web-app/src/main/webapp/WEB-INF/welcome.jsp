<%@page import="webapp.util.Constants"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">
    <div class="row well">
        <div class="span10 offset1">
            <div class="row">

                <div class="span5">
                    <h3>Welcome to Todolist MVC</h3>
                    
                      <a class="btn btn-primary btn-large" href="<%= Constants.Pages.LOGIN %>"> Sign in </a> or 
                      <a class="btn btn-primary btn-large" href="<%= Constants.Pages.REGISTER %>"> Sign up </a>
                    
                </div>
            </div>

        </div>

    </div>
</div>
