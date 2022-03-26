<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
 <link type="text/css" rel="stylesheet" href="css/login.css"> 
 <script type = "text/javascript" >
function preventBack() { window.history.forward(); }
setTimeout("preventBack()", 0);
window.onunload = function () { null };
</script>
</head>
<body style="background-image: url('css/background.jpg');">

  <h1> Administrator Login </h1> 
    <form action="LogoutServlet" method="POST">  
        <div class="container">   
            <label>Are you sure you want to logout? </label>   
            <br/>
            <button type="submit">Logout</button>   
            <br/>   
             
        </div>   
    </form>     


</body>
</html>