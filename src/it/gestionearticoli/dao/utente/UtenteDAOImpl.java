package it.gestionearticoli.dao.utente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import it.gestionearticoli.dao.AbstractMySQLDAO;
import it.gestionearticoli.model.Utente;

public class UtenteDAOImpl extends AbstractMySQLDAO implements UtenteDAO {

	@Override
	public List<Utente> list() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utente get(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Utente getUser(String username) throws Exception {
		String query = "select * from utente where username=? ";
		ResultSet rs = null;
		Utente utenteResult = null;
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, username);
			rs = preparedStatement.executeQuery();

			if (rs.next()) {
				utenteResult = new Utente();
				utenteResult.setId(rs.getLong("id_utente"));
				utenteResult.setNome(rs.getString("nome"));
				utenteResult.setCognome(rs.getString("cognome"));
				utenteResult.setCodiceFiscale(rs.getString("codice_fiscale"));
				utenteResult.setUsername(rs.getString("username"));
				utenteResult.setPassword(rs.getString("passw"));
				utenteResult.setRuolo(rs.getString("ruolo"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return utenteResult ;
	}

	@Override
	public int update(Utente input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Utente input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Utente input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Utente> findByExample(Utente input) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setConnection(Connection connection) {
		this.connection=connection;
		
	}

}
