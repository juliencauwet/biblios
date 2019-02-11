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
    <title>Liste des emprunts </title>
</head>
<body>

<!-- Liste des ouvrages en fonction des critères recherchés das le formulaire
      avec lien sur le titre qui emmène vers la description du livre -->

<div class="container-fluid">

    <div class="row">
        <div class="col-md-3">
            <s:a action="currentBorrowings">Afficher tous les emprunts</s:a><br />
            <br />
        </div>

        <div class="col-md-9">

            <table class="table">
                <tr>
                    <th>n° emprunt</th>
                    <th>Utilisateur</th>
                    <th>Nom du livre</th>
                    <th>Date de commencement</th>
                    <th>Date échéance de retour</th>
                </tr>
                <s:iterator value="borrowings">
                    <tr>
                        <td><s:a action="borrowingDetail"><s:property value="id" /><s:param value="id" name="id" /> </s:a> </td>
                        <td><s:property value="appUser.firstName"/> <s:property value="appUser.name"/> </td>
                        <td><s:property value="book.title"/></td>
                        <td><s:property value="startDate" /> </td>
                        <s:if test="returnDate">
                            <td><p>rendu</p></td>
                        </s:if>
                        <s:else>
                            <td><s:property value="dueReturnDate"/> </td>
                        </s:else>
                    </tr>
                </s:iterator>
            </table>

        </div>


</div>
</div>

</body>
</html>

