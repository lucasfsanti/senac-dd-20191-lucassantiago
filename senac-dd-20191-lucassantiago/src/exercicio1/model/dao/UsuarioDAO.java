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
		usuarioVO.getNome() + "', '" + usuarioVO.getEmail() + "', '" + 
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
			mensagem += "Usuário cadastro efetuado com sucesso!\n";
		} else {
			mensagem += "Erro ao executar query que cadastra usuário!\n";
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
			mensagem += "Exclusão efetuada com sucesso!\n";
		} else {
			mensagem += "Erro ao executar a query de exclusão de usuário!\n";
		}
		return mensagem;
	}
	
	public ArrayList<UsuarioVO> listarUsuarioDAO() {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ArrayList<UsuarioVO> listaUsuarios = new ArrayList<UsuarioVO>();
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
			e.printStackTrace();
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return listaUsuarios;
	}
	
	public String validarEmailSenha(UsuarioVO usuarioVO) {
		String mensagem = "";
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		String query = "SELECT EMAIL, SENHA FROM USUARIO WHERE EMAIL = '" + usuarioVO.getEmail() + "' AND SENHA = '" + usuarioVO.getSenha();
		try {
			resultado = stmt.executeQuery(query);
			if(resultado.next() == false) {
				mensagem += "Usuário e/ou senha inválidos. Tente novamente.\n";
			}
		} catch (SQLException e) {
			mensagem += "Erro ao executar a query que verifica se usuário e senha estão corretos!\n";
			e.printStackTrace();
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return mensagem;
	}
	
	public String validarAdmin(UsuarioVO usuarioVO) {
		String mensagem = "";
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		String query = "SELECT NIVEL.DESCRICAO FROM USUARIO INNER JOIN NIVEL ON USUARIO.IDNIVEL = NIVEL.IDNIVEL WHERE EMAIL = '" + usuarioVO.getEmail() + "' AND SENHA = '" + usuarioVO.getSenha();
		try {
			resultado = stmt.executeQuery(query);
			if(!resultado.getString(1).equals("Administrador")) {
				mensagem += "Usuário não tem permissão para excluir cadastros.\n";
			}
		} catch (SQLException e) {
			mensagem += "Erro ao executar a query que verifica se usuário é administrador!\n";
			e.printStackTrace();
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return mensagem;
	}

	public ArrayList<UsuarioVO> listarPorNome(String nome) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ArrayList<UsuarioVO> listaUsuarios = new ArrayList<UsuarioVO>();
		String query = "SELECT USUARIO.IDUSUARIO, USUARIO.NOME, USUARIO.EMAIL, USUARIO.SENHA, NIVEL.IDNIVEL, NIVEL.DESCRICAO "
				+ "FROM USUARIO LEFT JOIN NIVEL ON USUARIO.IDNIVEL = NIVEL.IDNIVEL WHERE USUARIO.NOME LIKE '%" + nome + "%'";
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
			e.printStackTrace();
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return listaUsuarios;
	}

	public ArrayList<UsuarioVO> listarPorNivel(NivelVO nivel) {
		// TODO Auto-generated method stub
		return null;
	}

}
