<%@page import="it.gestionearticoli.model.Articolo"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="../header.jsp" />
<title>Inserisci nuovo</title>

<!-- style per le pagine diverse dalla index -->
<link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">

</head>
<body>
	<jsp:include page="../navbar.jsp" />

	<main role="main" class="container">
	
	<c:if test = "${ empty sessionScope.oggettoSessione or sessionScope.oggettoSessione.getRuolo()  == 'guest' }"> 
	      <c:redirect url= "/index.jsp"/>
	      </c:if>

		<div class="alert alert-danger alert-dismissible fade show d-none"
			role="alert">
			Operazione fallita!
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>

		<div
			class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}"
			role="alert">
			${errorMessage}
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>

		<div class='card'>
			<div class='card-header'>
				<h5>Modifica elemento</h5>
			</div>
			<div class='card-body'>
				<c:set var="articolo"
					value="${requestScope.articoloDaInviareAPaginaUpdate}" />


				<form method="post" action="${pageContext.request.contextPath}/ExecuteUpdateArticoloServlet"
					class="needs-validation" novalidate>

					<div class="form-row">
						<div class="form-group col-md-6">
							<input type="hidden" name="id" id="id" class="form-control"
								value="${articolo.id}" required>
						</div>
					</div>

					<div class="form-row">
						<div class="form-group col-md-6">
							<input type="hidden" name="vecchiaCategoria"
								id="vecchiaCategoria" class="form-control"
								value="${articolo.categoria.getId()}" required>
						</div>
					</div>



					<div class="form-row">
						<div class="col-md-6 mb-3">
							<label for="codice">Codice</label> <input type="text"
								class="form-control" name="codice" id="codice"
								placeholder="Inserire il codice" value="${articolo.codice}"
								required>
							<div class="invalid-tooltip">Inserisci un codice valido.</div>
						</div>
					</div>

					<div class="form-row">
						<div class="col-md-6 mb-3">
							<label for="descrizione">Descrizione</label> <input type="text"
								class="form-control" name="descrizione" id="descrizione"
								placeholder="Inserire descrizione"
								value="${articolo.descrizione}" required>
							<div class="invalid-tooltip">Inserisci descrizione valida.
							</div>
						</div>
					</div>

					<div class="form-row">
						<div class="col-md-6 mb-3">
							<label for="prezzo">Prezzo</label> <input type="number"
								class="form-control" name="prezzo" id="prezzo"
								placeholder="Inserire prezzo" value="${articolo.prezzo}"
								required>
							<div class="invalid-tooltip">Prezzo non valido.</div>
						</div>
					</div>


					<div class="form-row">
						<div class="form-group col-md-6">
							<label>Categoria <span class="text-danger">*</span></label> <select
								name="idCategoria" id="idCategoria">
								<c:forEach items="${requestScope.listaCategorieAttribute}"
									var="categoria">
									<option value="${categoria.id}">
										<c:out value="${categoria.categoria }" />
									</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<button type="submit" name="submit" value="submit" id="submit"
						class="btn btn-primary">Conferma</button>


				</form>

				<script src="${pageContext.request.contextPath}/assets/js/myscript.js"></script>


				<!-- end card-body -->
			</div>
		</div>


		<!-- end container -->
	</main>
	<jsp:include page="../footer.jsp" />

</body>
</html>