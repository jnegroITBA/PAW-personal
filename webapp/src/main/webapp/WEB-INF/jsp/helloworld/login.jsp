<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<body>
<c:url value="/login" var="loginUrl" />
<form action="${loginUrl}" method="post" enctype="application/x-www-form-urlencoded">
    <div>
        <label for="username">Username: </label>
        <input id="username" name="j_username" type="text"
        />
    </div>
    <div>
        <label for="password">Password: </label>
        <input id="password" name="j_password" type="password"/>
    </div>
    <div>
        <label>Remember me: <input name="j_rememberme" type="checkbox"/></label>
    </div>
    <div>
        <input type="submit" value="Login!"/>
    </div>
</form>
</body>
</html>