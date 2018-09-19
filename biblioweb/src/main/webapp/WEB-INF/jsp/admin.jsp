
<%--
  Created by IntelliJ IDEA.
  User: julien
  Date: 27.06.18
  Time: 13:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Enregistrer un ouvrage</title>
</head>
<body>

<!-- formulaire pour l'ajout d'un livre avec confirmation/ erreur d'enregistrement du livre -->

    <h4>Enregistrement d'un livre</h4>
    <s:form class="form-group" method="post" action="addBook">
        <s:textfield key="authorName" label = "Nom de l'auteur" class="form-control"/>
        <s:textfield key="authorFirstName" label = "Prénom de l'auteur" class="form-control"/>
        <s:textfield key="title" label = "Titre du livre" class="form-control"/>
        <s:textfield key="number" label = "Nombre" class="form-control"/>
        <s:submit class="form-control" />
    </s:form >

<!-- lien visible uniquement pour les admins pour savoir les livres avec les retards (booklist.jsp) -->



<!-- formulaire pour rechercher un utilisateur uniquement visible par les admins (userlist.jsp)-->



</body>
</html>
