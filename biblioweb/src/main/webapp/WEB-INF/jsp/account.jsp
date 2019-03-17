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
        <div class="col-sm-3 ">
            <br>
            <div class="panel panel-info">
                <div class="panel-heading"><b>Mon compte</b></div>
                <div class="panel-body shadow-box-8">
                    <table class="table table-hover">
                        <tbody>
                        <tr>
                            <td>Mes informations</td>
                            <p>  Prénom : <s:property value="#session.firstName" /> </p>
                            <p>  Nom : <s:property value="#session.name" /> </p>
                            <p>  @ : <s:property value="#session.email" />   </p>
                            <hr>
                        </tr>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div class="col-sm-9">
            <form>
                <div class="form-group row">
                    <label for="inputEmail3" class="col-sm-2 col-form-label">Email</label>
                    <div class="col-sm-10">
                        <input type="email" class="form-control" id="inputEmail3" placeholder="email">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="inputPassword3" class="col-sm-2 col-form-label">Password</label>
                    <div class="col-sm-10">
                        <input type="password" class="form-control" id="inputPassword3" placeholder="Password">
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-sm-2">Alerte</div>
                    <div class="col-sm-10">
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" id="alerte" checked>
                            <label class="form-check-label" for="alerte">
                                Merci de m'alerter quand mon emprunt se rapproche de la date limite
                            </label>
                        </div>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-sm-10">
                        <button class="btn btn-primary"> <s:action name="editProfile" >Valider</s:action> </button>

                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
