<%--
  Created by IntelliJ IDEA.
  User: julien
  Date: 14.06.18
  Time: 17:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Livres</title>
</head>
<body>

<!-- Liste des ouvrages en fonction des critères recherchés das le formulaire
      avec lien sur le titre qui emmène vers la description du livre -->

<div class="container-fluid">

    <div class="row">
        <div class="col-md-3">
            <br />
            <s:form class="form" action="findBookByTitle">
                <s:textfield class="form-control" key="title" label="Titre de l'ouvrage"/>
                <s:submit class="form-control" value="Chercher"/>
            </s:form>


        </div>

        <div class="col-md-9">
            <table class="table">
                <tr>
                    <th>Prénom de l'auteur</th>
                    <th>Nom de l'auteur</th>
                    <th>Titre</th>
                    <th>disponibilité</th>
                    <th>attente</th>
                    <th>prochain retour</th>

                </tr>
                <s:iterator value="states">
                    <tr>

                        <td><s:property value="book.authorFirstName"/></td>
                        <td><s:property value="book.authorName"/></td>
                        <td><s:a action="getBookById"><s:param name="id" value="book.id"/> <s:property value="book.title" /></s:a> </td>
                        <td><s:property value="book.number"/> </td>

                        <td><s:property value="waitingListNumber"/></td><!--nb à attendre -->
                        <s:if test="book.number < 1">
                            <td><s:property value="nextReturn"/></td><!-- prochaine date de retour -->
                        </s:if>
                    </tr>
                </s:iterator>
            </table>
        </div>


</div>
</div>

</body>
</html>
