<%--
    Vue de la page d'accueil
    @see IndexServlet
    Attributs à fournir:
    - List<PDocument> list
    - int emprunts
    - (OPTIONEL) String success/error
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="parts/header.jsp"></jsp:include>
<div class="container">

    <jsp:include page="parts/alerts.jsp"></jsp:include>

        <div class="row equal my-4">

            <div class="col-12 col-md-6 col-lg-4 mb-4 mb-lg-0">
                <div class="card rounded shadow border-0 bg-light">
                    <div class="card-body p-4 text-center">
                        <p class="card-title">Documents</p>
                        <h2 class="card-text">${list.size()}</h2>
                    </div>
                </div>
            </div>

            <div class="col-12 col-md-6 col-lg-4 mb-4 mb-lg-0">
                <div class="card rounded shadow border-0 bg-light">
                    <div class="card-body p-4 text-center">
                        <p class="card-title">Emprunté</p>
                        <h2 class="card-text">${emprunts}</h2>
                    </div>
                </div>
            </div>

            <div class="col-12 col-md-6 col-lg-4 mb-4 mb-lg-0">
                <div class="card rounded shadow border-0 h-100">
                    <a href="${pageContext.request.contextPath}/add" class="btn btn-outline-success w-100 h-100">
                        <div class="card-body text-center">
                            <p class="card-title">Ajout rapide</p>
                            <span class="far fa-plus-square fa-2x"></span>
                        </div>
                    </a>
                </div>
            </div>

        </div>

    <jsp:include page="parts/list.jsp"></jsp:include>

</div>

<jsp:include page="parts/footer.jsp"></jsp:include>