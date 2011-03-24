<%-- 
    Document   : index
    Created on : 17.3.2011, 10:08:30
    Author     : saljack
--%>

<%@page pageEncoding="UTF-8"%>

<%@include file="header.jspf" %>


<c:url value="/IndexServelet" var="IndexServlet"/>

<div class="main">
    <form method="post" action="${IndexServlet}">
        <label for="plr0">Hráč 1:</label>
        <input type="text" name="name0" value="Plr1"/>
        <select name="plr0">
            <option value="hum" selected="selected">Člověk</option>
        </select>
        <br/>
        <label for="plr1">Hráč 2:</label>
        <input type="text" name="name1" value="Plr2"/>
        <select name="plr1">
            <option value="hum" selected="selected">Člověk</option>
            <option value="com1">Počítač úroveň 1</option>
            <option value="com2">Počítač úroveň 1</option>
            <option value="com3">Počítač úroveň 1</option>
            <option value="off">Nehraje</option>
        </select>
        <br/>
        <label for="plr2">Hráč 3:</label>
        <input type="text" name="name2" value="Plr3"/>
        <select name="plr2">
            <option value="hum" selected="selected">Člověk</option>
            <option value="com1">Počítač úroveň 1</option>
            <option value="com2">Počítač úroveň 1</option>
            <option value="com3">Počítač úroveň 1</option>
            <option value="off">Nehraje</option>
        </select>
        <br/>
        <label for="plr3">Hráč 4:</label>
        <input type="text" name="name3" value="Plr4"/>
        <select name="plr3">
            <option value="hum" selected="selected">Člověk</option>
            <option value="com1">Počítač úroveň 1</option>
            <option value="com2">Počítač úroveň 1</option>
            <option value="com3">Počítač úroveň 1</option>
            <option value="off">Nehraje</option>
        </select>
        <br/>
        <label for="velikost">Velikost hracího pole:</label>
        <select name="velikost">
            <option value="8" selected="selected">8x8</option>
            <option value="7">7x7</option>
            <option value="6">6x6</option>
            <option value="5">5x5</option>
            <option value="4">4x4</option>
        </select>
        <br/>
        <label for="deftheme">Použít výchozí téma</label><input type="checkbox" checked="true" name="deftheme" value="yes"/><br/>
        <input type="submit" value="Start"/>
    </form>

    <%-- Tohle dat do vlastniho souboru ktery se bude nacitat--%>
    <c:choose>
        <c:when test='${param.theme == "loaded"}'>
            <div class="loaded">
                Téma bylo úspěšně změněno<br/>
            </div>
        </c:when>
        <c:when test='${param.theme == "failed"}'>
            <div class="failed">
                Soubor nebyl nahrán, není to obrázek!<br/>
            </div>
        </c:when>

    </c:choose>
    <div class="theme">
        <form action="LoadImage" method="POST" enctype="multipart/form-data" >
            <label for="file">Vyberte cestu k vašemu tématu:</label><input type="file" name="file" accept="image/*"/>
            <input type="submit"/>
        </form>
    </div> 
    <c:if test='${sessionScope.theme != null}'>
        <div class="preview">
            <h2>Vybrané téma</h2>
            <img src='img/${sessionScope.theme}/about.jpg' alt="about theme"/>
            <img src='img/${sessionScope.theme}/0.jpg' alt="priklad"/>
            <img src='img/${sessionScope.theme}/cover.jpg' alt="cover"/>
        </div>
    </c:if>
    <div class="preview">
        <h2>Defaultní téma</h2>
        <img src='img/Default/about.jpg' alt="about theme"/>
        <img src='img/Default/0.jpg' alt="priklad"/>
        <img src='img/Default/cover.jpg' alt="cover"/>
    </div>
</div>



<%@include file="footer.jspf" %>