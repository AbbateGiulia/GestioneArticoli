package it.gestionearticoli.web.servlet.articolo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.gestionearticoli.model.Articolo;
import it.gestionearticoli.model.Utente;
import it.gestionearticoli.service.MyServiceFactory;

/**
 * Servlet implementation class ExecuteSearchArticoloServlet
 */
@WebServlet("/ExecuteSearchArticoloServlet")
public class ExecuteSearchArticoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteSearchArticoloServlet() {
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
		
		
		if( utente == null) {
			request.setAttribute("messaggioErrore", "non puoi effettuare l'operazione");
			session.invalidate();
			request.getRequestDispatcher("login.jsp").forward(request, response);
			
			return;
		}
		// validiamo input

				Articolo articoloInstance = new Articolo();
				articoloInstance.setCodice(request.getParameter("codice"));
				articoloInstance.setDescrizione(request.getParameter("descrizione"));
				articoloInstance.setPrezzo(request.getParameter("prezzo").isEmpty()? 0:Integer.parseInt(request.getParameter("prezzo")));
				try {
					articoloInstance.setCategoria(MyServiceFactory.getCategoriaServiceInstance()
							.findById(Long.parseLong(request.getParameter("idCategoria"))));
				} catch (Exception e1) {
					request.getRequestDispatcher("index.jsp").forward(request, response);
					e1.printStackTrace();
					return;
					
				}
				
				
				try {
					request.setAttribute("listaArticoliAttribute", MyServiceFactory.getArticoloServiceInstance()
							.findByExample(articoloInstance));
				} catch (Exception e) {
					e.printStackTrace();
				}

				//andiamo ai risultati
				request.getRequestDispatcher("articolo/resultsfromsearch.jsp").forward(request, response);

			}
	}

