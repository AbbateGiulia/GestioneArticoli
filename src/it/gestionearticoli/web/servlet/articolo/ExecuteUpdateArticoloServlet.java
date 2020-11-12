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
 * Servlet implementation class ExecuteUpdateArticoloServlet
 */
@WebServlet("/ExecuteUpdateArticoloServlet")
public class ExecuteUpdateArticoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExecuteUpdateArticoloServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
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
		String codiceInputParam = request.getParameter("codice");
		String descrizioneInputParam = request.getParameter("descrizione");
		
		String idCategoriaParam= request.getParameter("idCategoria");
		Long idCategoria= Long.parseLong(idCategoriaParam);
		CategoriaService service= MyServiceFactory.getCategoriaServiceInstance();
		Categoria result = null;
		
		String idCategoriaParam2= request.getParameter("vecchiaCategoria");
		Long idCategoria2= Long.parseLong(idCategoriaParam2);
		CategoriaService service2= MyServiceFactory.getCategoriaServiceInstance();
		Categoria result2 = null;
		
		try {
			result2=service2.findById(idCategoria2);
		} catch (Exception e2) {
			request.getRequestDispatcher("index.jsp").forward(request, response);
			e2.printStackTrace();
			return;
			
		}
		
		
		String prezzoInputStringParam = request.getParameter("prezzo");
		Integer prezzo = !prezzoInputStringParam.isEmpty() && Util.isNumber(prezzoInputStringParam)? Integer.parseInt(prezzoInputStringParam) : 0;
		// VALIDAZIONE
		if (codiceInputParam.isEmpty() || descrizioneInputParam.isEmpty() || prezzo < 1 || idCategoria < 0 ) {
			
			try {
				result=service.findById(idCategoria);
			} catch (Exception e) {
				request.getRequestDispatcher("index.jsp").forward(request, response);
				e.printStackTrace();
				return;
			}
			
			Articolo articoloInstance = new Articolo(codiceInputParam, descrizioneInputParam, prezzo, result);
			
			request.setAttribute("articoloDaInviareAPaginaUpdate",articoloInstance );	
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("articolo/update.jsp").forward(request, response);
			return;
		}

		// BUSINESS
		try {
			result=service.findById(idCategoria);
			System.out.println("questa e la nuova cat "+result.getId());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Articolo articoloInstance = new Articolo(codiceInputParam, descrizioneInputParam, prezzo, result);
		articoloInstance.setId(Long.parseLong(idInputParam));
		try {
			MyServiceFactory.getArticoloServiceInstance().aggiorna(articoloInstance);
			System.out.println(articoloInstance.getCodice()+" "+articoloInstance.getDescrizione()+" "+articoloInstance.getCategoria().getId());
			request.setAttribute("listaArticoliAttribute", MyServiceFactory.getArticoloServiceInstance().listByCategoria(result2));
			request.setAttribute("successMessage", "Operazione effettuata con successo");
		} catch (Exception e) {
			e.printStackTrace();
		}

		// DOVE VADO
		request.getRequestDispatcher("categoria/resultsfromcategoria.jsp").forward(request, response);

	}
}
