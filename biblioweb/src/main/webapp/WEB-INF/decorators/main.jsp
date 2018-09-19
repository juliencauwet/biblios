<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page" %>
<%@taglib prefix="s" uri="/struts-tags" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
	<title><decorator:title default="Struts Starter"/></title>
    <link href="<s:url value='/styles/main.css'/>" rel="stylesheet" type="text/css" media="all"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <link href="<s:url value='/styles/main.css'/>" rel="stylesheet" type="text/css" media="all"/>

    <script language="JavaScript" type="text/javascript" src="<s:url value='/struts/niftycorners/nifty.js'/>"></script>
	<script language="JavaScript" type="text/javascript">
        window.onload = function(){
            if(!NiftyCheck()) {
                return;
            }
            // perform niftycorners rounding
            // eg.
            // Rounded("blockquote","tr bl","#ECF1F9","#CDFFAA","smooth border #88D84F");
        }
    </script>
    <decorator:head/>
</head>

<header>
    <div class="container-fluid">
       <s:if test="#session.appUser">
            <h3>Hello <s:property value="#session.firstName" /> </h3>
            <s:a action="logout">Déconnexion</s:a>
       </s:if>
        <s:else>
            <s:a action="authentification">Connexion</s:a>
        </s:else>
    </div>

</header>
<body id="page-home">
<div class="container-fluid">
    <div id="page">
        <div id="header" class="clearfix">
            <h4>Bienvenue sur Biblioweb : Gestion de votre bibliothèque à distance!</h4>
            <hr />
        </div>

        <s:if test="hasActionErrors()">
            <div class = "errors">
                <s:actionerror/>
            </div>
        </s:if>

        <s:if test="hasActionMessages()">
            <div class="success">
                <s:actionmessage/>
            </div>
        </s:if>


        <div id="content" class="clearfix">
            <div id="main">
            	<decorator:body/>
                <hr />
            </div>

            <div class="row">
                <div id="allusers" class="col-md-3">
                <ul>
                    <li><s:a action="index">Page d'accueil</s:a></li>
                    <li><s:a action="book">Liste des livres et leur disponibilité</s:a></li>
                    <li><s:a action="currentBorrowings">Consulter vos emprunts</s:a></li>
                    </br>
                </ul>
                </div>

            <div id="admins" class="col-md-3">
                <s:if test="#session.isAdmin">
                    <h2>Espace Administrateurs</h2>
                    <s:a action="admin">Enregistrement d'un ouvrage</s:a><br/>
                    <s:a action="configuration">Configuration</s:a><br/>
                    <s:a action="getAllAppUsers">Liste des utilisateurs</s:a><br/>
                    <s:a action="allBorrowings">Voir tous les emprunts</s:a><br/>
                </s:if>
            </div>
            </div>
        </div>
        
        <div id="footer" class="clearfix">

        </div>
        
    </div>
    
    <div id="extra1">&nbsp;</div>
    <div id="extra2">&nbsp;</div>
</div>
</body>
</html>
