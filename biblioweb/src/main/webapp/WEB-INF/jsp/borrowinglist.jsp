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

        <div class="col-md-12">
            <h3>Emprunts en cours</h3>
            <table class="table">
                <tr>
                    <th>n° emprunt</th>
                    <th>Utilisateur</th>
                    <th>Titre</th>
                    <th>Date de commencement</th>
                    <th>Retour avant</th>
                </tr>
                <s:iterator value="borrowings">
                    <tr>
                        <td><s:a action="borrowingDetail"><s:property value="id" /><s:param value="id" name="id" /> </s:a> </td>
                        <td><s:property value="appUser.firstName"/> <s:property value="appUser.name"/> </td>
                        <td><s:property value="book.title"/></td>
                        <s:if test="startDate">
                            <td><s:property value="startDate" /> </td>
                        </s:if>
                        <s:elseif test="waitingListOrder == 0">
                            <td><p>disponible</p></td>
                        </s:elseif>
                        <s:else>
                            <td><p>réservé: <s:property value="waitingListOrder"/> pers. en attente </p></td>
                        </s:else>
                        <s:if test="returnDate">
                            <td><p>rendu le: <s:property value="returnDate" /> </p></td>
                        </s:if>
                        <s:else>
                            <td><s:property value="dueReturnDate"/> </td>
                        </s:else>
                    </tr>
                </s:iterator>
            </table>

            <!-- ############################################################################################ -->

            <s:if test="bookings != null">
            <br>
<hr>
            <br>

            <h3>Emprunts réservés</h3>
                <table class="table">
                    <tr>
                        <th>Prénom de l'auteur</th>
                        <th>Nom de l'auteur</th>
                        <th>Titre</th>
                        <th>position dans liste d'attente</th>
                        <th>prochain retour</th>
                        <th></th>
                    </tr>
                <s:iterator value="bookings">
                    <tr>
                        <td><s:property value="book.authorFirstName"/></td>
                        <td><s:property value="book.authorName"/></td>
                        <td><s:property value="book.title" /> </td>
                        <td><s:property value="waitingListNumber"/></td><!--nb à attendre -->
                        <td><s:property value="nextReturn"/></td><!-- prochaine date de retour -->
                        <td><s:a action="deleteBorrowing"><s:param name="bookId" value="book.id"/> Annuler </s:a> </td>
                    </tr>
                </s:iterator>
            </table>
            </s:if>
        </div>


</div>
</div>

</body>
</html>

