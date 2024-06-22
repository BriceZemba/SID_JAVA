<%--
    Composant permettant d'afficher la liste des documents
    Attributs à fournir:
    - List<PDocument> list
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="persistance.modèle.PDocument" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="card shadow">
    <div class="table-responsive">
        <table class="table table-bordered table-striped text-center">
            <thead>
            <tr>
                <th>#</th>
                <th>Type</th>
                <th>Titre</th>
                <th>Auteur</th>
                <th>Emprunté</th>
                <th>Opération</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="doc">
                <tr>
                    <td>${doc.getId()}</td>
                    <td>${doc.getType()}</td>
                    <td>${doc.getTitre()}</td>
                    <td>${doc.getAuteur()}</td>
                    <td>
                        <c:if test="${doc.estEmprunté()}">
                            <i class="fas fa-check"></i>
                        </c:if>
                        <c:if test="${!doc.estEmprunté()}">
                            <i class="fas fa-times"></i>
                        </c:if>
                    </td>
                    <td>
                        <form id="form_${doc.getId()}" method="POST" action="delete">
                            <input type="hidden" name="id" value="${doc.getId()}" />
                            <button class="btn btn-outline-danger" form="form_${doc.getId()}" type="submit"
                                <c:if test="${doc.estEmprunté()}">
                                    disabled
                                </c:if>
                            >Supprimer <i class="fa fa-trash"></i>
                            </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>