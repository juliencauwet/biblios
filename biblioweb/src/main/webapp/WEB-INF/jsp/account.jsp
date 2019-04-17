<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: julien
  Date: 2019-03-17
  Time: 13:36
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
<head>
    <title>Profil utilisateur</title>
</head>
<body>
<div data-layout-fragment="content" style="padding-top: 10px;">
    <div class="row">

            <div class="panel panel-info">
                <div class="panel-heading"><b>Mon compte</b></div>
                <div class="panel-body shadow-box-8">
                    <table class="table table-hover">
                        <thead>Mes informations</thead>
                        <tbody>
                        <ul>
                            <li style="color: black">  Prénom : <s:property value="#session.appUser.firstName" /> </li>
                            <li style="color: black">  Nom : <s:property value="#session.appUser.name" /> </li>
                            <li style="color: black">  @ : <s:property value="#session.appUser.email" />   </li>
                        </ul>

                        </tbody>
                    </table>
                </div>
            </div>
    </div>
    <div class="row">

            <s:form action="updateProfile" >

                <s:checkbox name="alert" fieldValue="#session.appUser.alert" value="#session.appUser.alert" label="Merci de m'alerter quand mon emprunt se rapproche de la date limite" />

                <button class="btn btn-primary"> <s:submit class="form-control btn btn-primary" value="Valider" /> </button>

            </s:form>

    </div>
</div>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
