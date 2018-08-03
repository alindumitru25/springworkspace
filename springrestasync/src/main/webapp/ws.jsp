<%--
  Created by IntelliJ IDEA.
  User: dumitral
  Date: 8/3/2018
  Time: 12:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>WS Consumer</title>
    <script src="<c:url value="/resources/ws-consumer.js" />" type="text/javascript"></script>
</head>
<body>
<div>
    <div id="connect-container" class="ui centered grid">
        <div class="row">
            <button id="connect" onclick="connect();" class="ui green button ">
                Connect</button>
            <button id="disconnect" disabled="disabled" onclick="disconnect();" class="ui
red button">Disconnect</button>
        </div>
        <div class="row">
<textarea id="message" style="width: 350px" class="ui input"
          placeholder="Message to Echo"></textarea>
        </div>
        <div class="row">
            <button id="echo" onclick="echo();" disabled="disabled" class="ui button">
                Echo message</button>
        </div>
    </div>
    <div id="console-container">
        <h3>Logging</h3>
        <div id="logging"></div>
    </div>
</div>
</body>
</html>
