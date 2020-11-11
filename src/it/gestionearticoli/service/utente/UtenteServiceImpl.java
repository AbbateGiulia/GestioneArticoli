package it.gestionearticoli.service.utente;

import java.sql.Connection;
import java.util.List;

import it.gestionearticoli.connection.MyConnection;
import it.gestionearticoli.dao.Constants;
import it.gestionearticoli.dao.utente.UtenteDAO;
import it.gestionearticoli.model.Utente;

public class UtenteServiceImpl implements UtenteService {
	
	private UtenteDAO utenteDao;



	@Override
	public void setUtenteDao(UtenteDAO utenteDao) {
		this.utenteDao=utenteDao;		
	}

	@Override
	public List<Utente> listAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utente findById(Long idInput) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utente findByUser(String username) throws Exception {
		Utente result = null; //valorizzo prima
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto 
			utenteDao.setConnection(connection);

			// metodo dao
			result = new Utente();
			result = utenteDao.getUser(username);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int aggiorna(Utente input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int inserisciNuovo(Utente input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int rimuovi(Utente input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Utente> findByExample(Utente input) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
