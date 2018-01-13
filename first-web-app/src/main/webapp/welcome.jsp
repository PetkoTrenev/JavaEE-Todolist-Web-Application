<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">
    <div class="row well">
        <div class="span10 offset1">
            <div class="row">

                <div class="span5">
                    <h3>Welcome to Todolist MVC</h3>
                    
                    <c:if test="${sessionScope.user == null}">
                    <p>
                        <a class="btn btn-primary btn-large" href="/login.do"> Sign in </a> or <a class="btn btn-primary btn-large" href="/register.html"> Sign up </a>
                    </p>
                    </c:if>
                </div>
            </div>

        </div>

    </div>
</div>
