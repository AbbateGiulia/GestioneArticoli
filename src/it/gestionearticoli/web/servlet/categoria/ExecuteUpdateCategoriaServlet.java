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

/**
 * Servlet implementation class ExecuteUpdateCategoriaServlet
 */
@WebServlet("/ExecuteUpdateCategoriaServlet")
public class ExecuteUpdateCategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteUpdateCategoriaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session= request.getSession();
		Utente utente= (Utente) session.getAttribute("oggettoSessione");
		
		
		if( utente == null || utente.getRuolo().equals("guest") ) {
			request.setAttribute("messaggioErrore", "non puoi effettuare l'operazione");
			session.invalidate();
			request.getRequestDispatcher("login.jsp").forward(request, response);
			
			return;
		}
		//GESTIONE INPUT
				String idInputParam = request.getParameter("id");
				String categoriaString= request.getParameter("categoria");
				
				
				// VALIDAZIONE
				if (categoriaString.isEmpty()) {
					
					Categoria categoriaInstance = new Categoria(categoriaString);
					categoriaInstance.setId(Long.parseLong(idInputParam));
					
					request.setAttribute("articoloDaInviareAPaginaUpdate",categoriaInstance );	
					request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
					request.getRequestDispatcher("categoria/updatecategoria.jsp").forward(request, response);
					return;
				}

				// BUSINESS
				Categoria categoriaInstance = new Categoria(categoriaString);
				categoriaInstance.setId(Long.parseLong(idInputParam));
				try {
					MyServiceFactory.getCategoriaServiceInstance().aggiorna(categoriaInstance);
					request.setAttribute("listaCategorieAttribute", MyServiceFactory.getCategoriaServiceInstance().listAll());
					request.setAttribute("successMessage", "Operazione effettuata con successo");
				} catch (Exception e) {
					e.printStackTrace();
				}

				// DOVE VADO
				request.getRequestDispatcher("categoria/listacategorie.jsp").forward(request, response);

	}

}
