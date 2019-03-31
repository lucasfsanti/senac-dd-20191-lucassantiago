package exercicio1.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import exercicio1.model.vo.NivelVO;
import exercicio1.model.vo.UsuarioVO;

public class UsuarioDAO {
	
	public String cadastrarUsuarioDAO(UsuarioVO usuarioVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		int resultado = 0;
		String mensagem = "";
		String query = "INSERT INTO USUARIO(NOME, EMAIL, SENHA, IDNIVEL) VALUES ('" + 
		usuarioVO.getNome() + "', '" + usuarioVO.getEmail() + ", '" + 
		usuarioVO.getSenha() + "', " + usuarioVO.getNivel().getIdNivel() + ")";
		try {
			resultado = stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		if(resultado >=1) {
			mensagem = "Cadastro efetuado com sucesso!";
		} else {
			mensagem = "Erro ao executar query que cadastra usuário!";
		}
		return mensagem;
	}
	
	public String alterarUsuarioDAO(UsuarioVO usuarioVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		int resultado = 0;
		String mensagem = "";
		String query = "UPDATE USUARIO SET NOME = " + usuarioVO.getNome() + 
				", EMAIL = " + usuarioVO.getEmail() + 
				", SENHA = " + usuarioVO.getSenha() + 
				", IDNIVEL = " + usuarioVO.getNivel().getIdNivel() + 
				" WHERE IDUSUARIO = " + usuarioVO.getId() + "";
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
			mensagem = "Erro ao executar a query de alteração de usuário!";
		}
		return mensagem;
	}
	
	public String excluirUsuarioDAO(UsuarioVO usuarioVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		int resultado = 0;
		String mensagem = "";
		String query = "DELETE FROM USUARIO WHERE IDUSUARIO = " + usuarioVO.getId();
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
			mensagem = "Erro ao executar a query de exclusão de usuário!";
		}
		return mensagem;
	}
	
	public ArrayList<Object> listarUsuarioDAO() {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ArrayList<Object> listaUsuarios = new ArrayList<Object>();
		String query = "SELECT USUARIO.IDUSUARIO, USUARIO.NOME, USUARIO.EMAIL, USUARIO.SENHA, NIVEL.IDNIVEL, NIVEL.DESCRICAO FROM USUARIO LEFT JOIN NIVEL ON USUARIO.IDNIVEL = NIVEL.IDNIVEL";
		ResultSet resultado = null;
		try {
			resultado = stmt.executeQuery(query);
			while(resultado.next()) {
				UsuarioVO usuarioVO = new UsuarioVO();
				usuarioVO.setId(Integer.parseInt(resultado.getString(1)));
				usuarioVO.setNome(resultado.getString(2));
				usuarioVO.setEmail(resultado.getString(3));
				usuarioVO.setSenha(resultado.getString(4));
				NivelVO nivel = new NivelVO();
				nivel.setIdNivel(Integer.parseInt(resultado.getString(5)));
				nivel.setDescricao(resultado.getString(6));
				usuarioVO.setNivel(nivel);
				listaUsuarios.add(usuarioVO);
			}
		} catch (SQLException e) {
			listaUsuarios.add("Erro ao executar a query de consulta de usu�rios!");
			e.printStackTrace();
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return listaUsuarios;
	}


}
