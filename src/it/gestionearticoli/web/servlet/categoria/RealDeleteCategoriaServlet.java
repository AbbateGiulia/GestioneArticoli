package it.gestionearticoli.web.servlet.categoria;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.gestionearticoli.model.Categoria;
import it.gestionearticoli.model.Utente;
import it.gestionearticoli.service.MyServiceFactory;
import it.gestionearticoli.service.categoria.CategoriaService;

/**
 * Servlet implementation class RealDeleteCategoriaServlet
 */
@WebServlet("/RealDeleteCategoriaServlet")
public class RealDeleteCategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RealDeleteCategoriaServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Utente utente = (Utente) session.getAttribute("oggettoSessione");

		if (utente == null || !utente.getRuolo().equals("admin")) {
			request.setAttribute("messaggioErrore", "non puoi effettuare l'operazione");
			session.invalidate();
			request.getRequestDispatcher("login.jsp").forward(request, response);

			return;
		}
		String param = request.getParameter("id");

		if (param == null || param.isEmpty()) {
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}

		CategoriaService service = MyServiceFactory.getCategoriaServiceInstance();
		Categoria result = null;

		try {
			result = service.findById(Long.parseLong(param));
			if (result == null) {
				request.getRequestDispatcher("index.jsp").forward(request, response);
				return;
			}			
			
		} catch (Exception e) {
			request.getRequestDispatcher("index.jsp").forward(request, response);
			e.printStackTrace();
			return;
		}
		try {
			
			service.rimuovi(result);
		} catch (Exception e) {
			request.setAttribute("messaggioErrore", "non puoi effettuare l'operazione");
			request.getRequestDispatcher("/ListCategorieServlet").forward(request, response);
			
			return;
		}
		request.getRequestDispatcher("/ListCategorieServlet").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
