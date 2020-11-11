 package it.gestionearticoli.dao.articolo;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.gestionearticoli.dao.AbstractMySQLDAO;
import it.gestionearticoli.model.Articolo;
import it.gestionearticoli.model.Categoria;
import it.gestionearticoli.service.MyServiceFactory;
import it.gestionearticoli.service.categoria.CategoriaService;

public class ArticoloDAOImpl extends AbstractMySQLDAO implements ArticoloDAO {

	@Override
	public List<Articolo> list() throws Exception {
		if (isNotActive()) {
			return null;
		}

		ArrayList<Articolo> result = new ArrayList<Articolo>();
		Articolo articoloTemp = null;

		try (Statement ps = connection.createStatement()) {
			ResultSet rs = ps.executeQuery("select * from articolo");
			CategoriaService service= MyServiceFactory.getCategoriaServiceInstance();

			while (rs.next()) {
				articoloTemp = new Articolo();
				articoloTemp.setCodice(rs.getString("CODICE"));
				articoloTemp.setCategoria(service.findById(rs.getLong("categoria_fk")));
				articoloTemp.setDescrizione(rs.getString("DESCRIZIONE"));
				articoloTemp.setPrezzo(rs.getInt("PREZZO"));
				articoloTemp.setId(rs.getLong("ID"));
				result.add(articoloTemp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}
	
	
	@Override
	public Articolo get(Long id) throws Exception {
		String query = "select * from articolo where id=? ";
		ResultSet rs = null;
		Articolo articoloResult = null;
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setLong(1, id);
			rs = preparedStatement.executeQuery();
			CategoriaService service= MyServiceFactory.getCategoriaServiceInstance();

			if (rs.next()) {
				articoloResult = new Articolo();
				articoloResult.setCodice(rs.getString("CODICE"));
				articoloResult.setDescrizione(rs.getString("DESCRIZIONE"));
				articoloResult.setPrezzo(rs.getInt("PREZZO"));
				articoloResult.setId(rs.getLong("ID"));
				articoloResult.setCategoria(service.findById(rs.getLong("categoria_fk")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return articoloResult;
	}

	@Override
	public int update(Articolo input) throws Exception {
		String query = "UPDATE articolo SET codice = ?, prezzo = ?, descrizione = ?, categoria_fk = ?  WHERE id = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, input.getCodice());
			preparedStatement.setLong(2, input.getPrezzo());
			preparedStatement.setString(3, input.getDescrizione());
			preparedStatement.setLong(4, input.getCategoria().getId());
			preparedStatement.setLong(5, input.getId());

			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int insert(Articolo input) throws Exception {
		if (isNotActive() || input == null) {
			return -1;
		}

		int result = 0;

		try (PreparedStatement ps = connection
				.prepareStatement("INSERT INTO articolo (codice, descrizione, prezzo, categoria_fk) VALUES (?, ?, ?,?);")) {
			ps.setString(1, input.getCodice());
			ps.setString(2, input.getDescrizione());
			ps.setInt(3, input.getPrezzo());
			ps.setLong(4, input.getCategoria().getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int delete(Articolo input) throws Exception {
		String query = "DELETE FROM articolo WHERE id = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setLong(1, input.getId());

			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Articolo> findByExample(Articolo input) throws Exception {
		if (isNotActive()) {
			return null;
		}

		ArrayList<Articolo> result = new ArrayList<Articolo>();
		Articolo articoloTemp = null;
		ResultSet rs = null;

		String query = "SELECT * " + 
				"FROM ARTICOLO " + 
				"WHERE CODICE like ? " + 
				"AND " + 
				" (PREZZO = ? OR ? = 0) " + 
				"AND " + 
				"DESCRIZIONE like ? " + 
				"AND " + 
				"(CATEGORIA_FK = ? OR ? is NULL); ";
		
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, "%"+input.getCodice()+"%");
			preparedStatement.setInt(2, input.getPrezzo());
			preparedStatement.setInt(3, input.getPrezzo());
			preparedStatement.setString(4, "%"+input.getDescrizione()+"%");
			preparedStatement.setString(5, input.getCategoria()==null? null : input.getCategoria().getId().toString());
			preparedStatement.setString(6, input.getCategoria()==null? null :input.getCategoria().getId().toString());
			
			  rs = preparedStatement.executeQuery();

			while (rs.next()) {
				articoloTemp = new Articolo();
				articoloTemp.setId(rs.getLong("ID"));
				articoloTemp.setCodice(rs.getString("CODICE"));				
				articoloTemp.setDescrizione(rs.getString("DESCRIZIONE"));
				articoloTemp.setPrezzo(rs.getInt("PREZZO"));
				articoloTemp.setCategoria(MyServiceFactory.getCategoriaServiceInstance().findById(rs.getLong("CATEGORIA_FK")));
				
				result.add(articoloTemp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}	

	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;

	}


	@Override
	public List<Articolo> listByCategoria (Categoria categoria) throws Exception  {
		if (isNotActive()) {
			return null;
		}

		ArrayList<Articolo> result = new ArrayList<Articolo>();
		Articolo articoloTemp = null;
		ResultSet rs = null;

		String query = "Select * FROM articolo WHERE categoria_fk = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setLong(1, categoria.getId());
			
			  rs = preparedStatement.executeQuery();

			while (rs.next()) {
				articoloTemp = new Articolo();
				articoloTemp.setCodice(rs.getString("CODICE"));
				articoloTemp.setCategoria(categoria);
				articoloTemp.setDescrizione(rs.getString("DESCRIZIONE"));
				articoloTemp.setPrezzo(rs.getInt("PREZZO"));
				articoloTemp.setId(rs.getLong("ID"));
				result.add(articoloTemp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

}
