<%--
  Created by IntelliJ IDEA.
  User: julien
  Date: 27.06.18
  Time: 10:49
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Liste des utilisateurs</title>
</head>
<body>
        <!-- Liste des utilisateurs en fonction des critères recherchés das le formulaire -->

        <div class="container-fluid">

            <div class="row">
                <div class="col-md-3">
                    <s:a action="getAllAppUsers">Afficher tous les utilisateurs</s:a><br />
                    <br />
                </div>

                <div class="col-md-9">
                    <table class="table">
                        <tr>
                            <th>Adresse Email</th>
                            <th>Mot de Passe</th>
                            <th>Admin?</th>
                        </tr>
                        <s:iterator value="appUsers">
                            <tr>
                                <td><s:property value="email"/></td>
                                <td><s:property value="password"/></td>
                                <td><s:property value="isAdmin"/></td>
                            </tr>
                        </s:iterator>
                    </table>
                </div>


            </div>
        </div>

        <!-- Lien vers la description de l'utilisateur -->
</body>
</html>
