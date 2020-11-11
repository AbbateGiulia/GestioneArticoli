package it.gestionearticoli.web.servlet.articolo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.gestionearticoli.model.Articolo;
import it.gestionearticoli.model.Categoria;
import it.gestionearticoli.model.Utente;
import it.gestionearticoli.service.MyServiceFactory;
import it.gestionearticoli.service.categoria.CategoriaService;
import it.gestionearticoli.util.Util;

/**
 * Servlet implementation class ExecuteInsertSoloArticoloServlet
 */
@WebServlet("/ExecuteInsertSoloArticoloServlet")
public class ExecuteInsertSoloArticoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteInsertSoloArticoloServlet() {
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
		// validiamo input
				String codiceInputParam = request.getParameter("codice");
				String descrizioneInputParam = request.getParameter("descrizione");
				String prezzoInputStringParam = request.getParameter("prezzo");
				String idCategoriaParam= request.getParameter("idCategoria");
				Long idCategoria= Long.parseLong(idCategoriaParam);
				CategoriaService service= MyServiceFactory.getCategoriaServiceInstance();
				Categoria result = null;
				
				Integer prezzo = !prezzoInputStringParam.isEmpty() && Util.isNumber(prezzoInputStringParam) ? Integer.parseInt(prezzoInputStringParam) : 0;
				// se la validazione fallisce torno in pagina
				if (codiceInputParam.isEmpty() || descrizioneInputParam.isEmpty() || prezzo < 1) {
					request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
					request.getRequestDispatcher("articolo/insertarticolo.jsp").forward(request, response);
					return;
				}

				try {
					result=service.findById(idCategoria);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Articolo articoloInstance = new Articolo(codiceInputParam, descrizioneInputParam, prezzo, result);
				try {
					MyServiceFactory.getArticoloServiceInstance().inserisciNuovo(articoloInstance);
					request.setAttribute("listaArticoliAttribute", MyServiceFactory.getArticoloServiceInstance().listAll());
					request.setAttribute("successMessage", "Operazione effettuata con successo");
				} catch (Exception e) {
					e.printStackTrace();
				}

				//andiamo ai risultati
				request.getRequestDispatcher("articolo/results.jsp").forward(request, response);

			}
	}


