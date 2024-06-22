<%--
    Vue de la page d'ajout d'un document
    @see AddServlet
    Attributs à fournir:
    - (OPTIONEL) String success/error
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="parts/header.jsp"></jsp:include>

<div class="container">
    <div class="py-5 text-center">
        <%-- <img class="d-block mx-auto mb-4" src="${pageContext.request.contextPath}/assets/img/default.png">--%>
       	<h2>Ajouter un nouveau document</h2>
    </div>

    <div class="row justify-content-center">
        <div class="col-md-8">

            <jsp:include page="parts/alerts.jsp"></jsp:include>

            <h4 class="mb-3">Détails</h4>
            <form class="needs-validation" method="POST">

                <div class="mb-3">
                    <label for="titre">Titre</label>
                    <input type="text" class="form-control" id="titre" name="titre" placeholder="" value="" required>
                    <div class="invalid-feedback">
                        Veuillez précisez le titre.
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label for="auteur">Auteur</label>
                        <input type="text" class="form-control" id="auteur" name="auteur" placeholder="" value="" required>
                        <div class="invalid-feedback">
                            Veuillez précisez l'autheur.
                        </div>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="type">Type</label>
                        <select class="custom-select d-block w-100" id="type" name="type" required>
                            <option value="">Sélection...</option>
                            <option value="1">Livre</option>
                            <option value="2">DVD</option>
                            <option value="3">CD</option>
                        </select>
                        <div class="invalid-feedback">
                            Veuillez choisir un type valide.
                        </div>
                    </div>
                </div>

                <hr class="mb-4">
                <button class="btn btn-primary btn-lg btn-block" type="submit">Ajouter</button>
            </form>
        </div>
    </div>

<jsp:include page="parts/footer.jsp"></jsp:include>
