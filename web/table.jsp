<%-- 
    Document   : index
    Created on : 17.3.2011, 10:08:30
    Author     : saljack
--%>

<%@page pageEncoding="UTF-8"%>

<%@include file="header.jspf" %>

<!-- <script type="text/javascript">
  var timeout = setTimeout("location.reload(true);",10000);
  function resetTimeout() {
    clearTimeout(timeout);
    timeout = setTimeout("location.reload(true);",10000);
  }
</script>-->



<div class="main">
    <!-- Balicek -->
    ${sessionScope.game.balicek.HTML}
    
    

    <c:url value="/Turn" var="turn"/>
    <form method="GET" action="${turn}">
        <button type="submit" name="turnback" value="true">Otočit</button>
    </form>
</div>



<%@include file="footer.jspf" %>
