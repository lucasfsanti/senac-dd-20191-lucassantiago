package exercicio1.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

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
	
	public ArrayList<UsuarioVO> listarUsuarioDAO(UsuarioVO usuarioVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ArrayList<UsuarioVO> = new ArrayList<>;
		String query = "SELECT USUARIO.NOME, USUARIO.EMAIL, USUARIO.SENHA, NIVEL.DESCRICAO FROM USUARIO LEFT JOIN NIVEL ON USUARIO.IDNIVEL = NIVEL.IDNIVEL"
	}


}
