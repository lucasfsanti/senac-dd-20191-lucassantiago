package exercicio1.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import exercicio1.model.vo.NivelVO;

public class NivelDAO {
	
	public String cadastrarNivelDAO(NivelVO nivelVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		int resultado = 0;
		String mensagem = "";
		String query = "INSERT INTO NIVEL(DESCRICAO) VALUES ('" + nivelVO.getDescricao() + "')";
		try {
			resultado = stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		if(resultado >=1) {
			mensagem = "Nível cadastrado com sucesso!";
		} else {
			mensagem = "Erro ao executar query que cadastra nível!";
		}
		return mensagem;
	}
	
	public String alterarNivelDAO(NivelVO nivelVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		int resultado = 0;
		String mensagem = "";
		String query = "UPDATE NIVEL SET DESCRICAO = " + nivelVO.getDescricao() + " WHERE IDNIVEL = " + nivelVO.getIdNivel() + "";
		try {
			resultado = stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		if(resultado >= 1) {
			mensagem = "Alteração efetuada com sucesso!";	
		} else {
			mensagem = "Erro ao executar a query de alteração de nível!";
		}
		return mensagem;
	}
	
	public String excluirNivelDAO(NivelVO nivelVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		int resultado = 0;
		String mensagem = "";
		String query = "DELETE FROM NIVEL WHERE IDNIVEL = " + nivelVO.getIdNivel();
		try {
			resultado = stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		if(resultado >= 1) {
			mensagem = "Exclusão efetuada com sucesso!";
		} else {
			mensagem = "Erro ao executar a query de exclusão de nível!";
		}
		return mensagem;
	}
	
	public ArrayList<Object> listarNivelVO() {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		ArrayList<Object> listaNiveis = new ArrayList<Object>();
		String query = "SELECT IDNIVEL, DESCRICAO FROM NIVEL";
		try {
			resultado = stmt.executeQuery(query);
			while(resultado.next()) {
				NivelVO nivel = new NivelVO();
				nivel.setIdNivel(Integer.parseInt(resultado.getString(1)));
				nivel.setDescricao(resultado.getString(2));
				listaNiveis.add(nivel);
			}
		} catch (SQLException e) {
			listaNiveis.add("Erro ao executar query de consulta de níveis!");
			e.printStackTrace();
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return listaNiveis;
	}

}
