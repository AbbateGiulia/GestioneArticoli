package it.gestionearticoli.dao.categoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.gestionearticoli.dao.AbstractMySQLDAO;
import it.gestionearticoli.model.Categoria;

public class CategoriaDAOImpl extends AbstractMySQLDAO implements CategoriaDAO {

	@Override
	public List<Categoria> list() throws Exception {
		
		if (isNotActive()) {
			return null;
		}

		ArrayList<Categoria> result = new ArrayList<Categoria>();
		Categoria categoriaTemp = null;

		try (Statement ps = connection.createStatement()) {
			ResultSet rs = ps.executeQuery("select * from categoria");

			while (rs.next()) {
				categoriaTemp = new Categoria();
				categoriaTemp.setCategoria(rs.getString("categoria"));
				categoriaTemp.setId(rs.getLong("id_categoria"));
				result.add(categoriaTemp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}


	@Override
	public Categoria get(Long id) throws Exception {
		String query = "select * from categoria where id_categoria=? ";
		ResultSet rs = null;
		Categoria categoriaResult = null;
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setLong(1, id);
			rs = preparedStatement.executeQuery();

			if (rs.next()) {
				categoriaResult = new Categoria();
				categoriaResult.setCategoria(rs.getString("categoria"));
				categoriaResult.setId(rs.getLong("id_categoria"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categoriaResult;
	}

	@Override
	public int update(Categoria input) throws Exception {
		String query = "UPDATE categoria SET  categoria = ? WHERE id_categoria= ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setString(1, input.getCategoria());
			preparedStatement.setLong(2, input.getId());

			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int insert(Categoria input) throws Exception {
		if (isNotActive() || input == null) {
			return -1;
		}

		int result = 0;

		try (PreparedStatement ps = connection
				.prepareStatement("INSERT INTO categoria (categoria) VALUES (?);")) {
			
			ps.setString(1, input.getCategoria());			
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int delete(Categoria input) throws Exception {
		String query = "DELETE FROM categoria WHERE id_categoria = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setLong(1, input.getId());

			return preparedStatement.executeUpdate();
		}catch(SQLIntegrityConstraintViolationException er){
			throw er;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Categoria> findByExample(Categoria input) throws Exception {
		if (isNotActive()) {
			return null;
		}

		ArrayList<Categoria> result = new ArrayList<Categoria>();
		Categoria categoriaTemp = null;
		ResultSet rs = null;

		String query = "select * " + 
				"FROM categoria " + 
				"WHERE categoria like ?; ";
		
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			
			preparedStatement.setString(1, "%"+input.getCategoria()+"%");
		
			  rs = preparedStatement.executeQuery();

			while (rs.next()) {
				categoriaTemp = new Categoria();
				categoriaTemp.setId(rs.getLong("id_categoria"));
				categoriaTemp.setCategoria(rs.getString("categoria"));
				result.add(categoriaTemp);
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

}
