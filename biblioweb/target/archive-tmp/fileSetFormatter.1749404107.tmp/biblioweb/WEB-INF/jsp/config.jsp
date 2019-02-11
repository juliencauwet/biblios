<%--
  Created by IntelliJ IDEA.
  User: julien
  Date: 27.06.18
  Time: 11:12
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Paramètres de réservation d'un ouvrage</title>
</head>
<body>

<s:form  class="form-group" action="changeConfig" method="POST">
    <s:textfield class="form-control" key="borrowingDuration" label="Durée de l'emprunt"/>
    <s:textfield class="form-control" key="extensionDuration" label="Durée de prolongation d'un emprunt" />
    <s:submit class="form-control" value="Enregistrer" />
</s:form>

<hr>

<p> La durée d'emprunt actuelle est de <s:property value="borrowingDuration" /> semaines. La prolongation est de <s:property value="extensionDuration" /> semaines </p>



</body>
</html>
