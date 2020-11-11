<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!-- navbar -->
<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-primary">

	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
       <span class="navbar-toggler-icon"></span>
     </button>

  <div class="collapse navbar-collapse" id="navbarsExampleDefault">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="${pageContext.request.contextPath}/index.jsp">Home <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Link</a>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Dropdown</a>
        <div class="dropdown-menu" aria-labelledby="dropdown01">
          <a class="dropdown-item" href="../index.jsp">Home</a>
          <a class="dropdown-item" href="${pageContext.request.contextPath}/ListArticoliServlet">Risultati</a>
          <a class="dropdown-item" href="../insert.jsp">Inserisci nuovo elemento</a>
        </div>
      </li>
    </ul>
    
    <c:if test = "${not empty sessionScope.oggettoSessione}"> 
	   <div class="p-3 mb-2 bg-primary text-white"><span>Welcome ${sessionScope.oggettoSessione.getNome()} </span></div>
	</c:if>
	<c:if test = "${not empty sessionScope.oggettoSessione}">
	   <a class="btn btn-secondary" href="${pageContext.request.contextPath}/LogoutServlet" role="button">Logout &raquo;</a>
	 </c:if>
	    
    <form class="form-inline my-2 my-lg-0">
      <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form>
  </div>
</nav>
