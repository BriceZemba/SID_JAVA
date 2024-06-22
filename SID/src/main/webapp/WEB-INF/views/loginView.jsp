<%--
    Vue de la page de connexion
    @see AuthentificationServlet
    Attributs à fournir:
    - (OPTIONEL) String success/error
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="parts/core.jsp"></jsp:include>

<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/login.css" />

</head>
<body class="text-center">
    <form method="POST" class="form-signin">

        <img class="mb-4" src="${pageContext.request.contextPath}/assets/img/default.png" alt="Mediatek" height="72">
        <h1 class="h3 mb-3 font-weight-normal">Veuillez vous identifier</h1>

        <label for="username" class="sr-only">Identifiant</label>
        <input type="text" id="username" name="username" class="form-control" placeholder="Identifiant" required autofocus>

        <label for="password" class="sr-only">Mot de passe</label>
        <input type="password" id="password" name="password" class="form-control" placeholder="Mot de passe" required>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Se connecter</button>

        <jsp:include page="parts/alerts.jsp"></jsp:include>

    </form>

<jsp:include page="parts/footer.jsp"></jsp:include>
