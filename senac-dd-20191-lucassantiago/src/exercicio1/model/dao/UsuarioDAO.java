package exercicio1.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import exercicio1.model.vo.UsuarioVO;

public class UsuarioDAO {
	
	public int cadastrarUsuarioDAO(UsuarioVO usuarioVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		int resultado = 0;
		String query = "INSERT INTO USUARIO(NOME, EMAIL, SENHA, IDNIVEL) VALUES ('" + 
		usuarioVO.getNome() + "', '" + usuarioVO.getEmail() + ", '" + 
		usuarioVO.getSenha() + "', " + usuarioVO.getNivel().getIdNivel() + ")";
		try {
			resultado = stmt.executeUpdate(query);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao executar query que cadastra usuário!");
			e.printStackTrace();
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return resultado;
	}
	
	public int alterarUsuarioDAO(UsuarioVO usuarioVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		int resultado = 0;
		String query = "UPDATE USUARIO SET NOME = " + usuarioVO.getNome() + 
				", EMAIL = " + usuarioVO.getEmail() + 
				", SENHA = " + usuarioVO.getSenha() + 
				", IDNIVEL = " + usuarioVO.getNivel().getIdNivel() + 
				" WHERE IDUSUARIO = " + usuarioVO.getId() + "";
		try {
			resultado = stmt.executeUpdate(query);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao executar a query de alteração de usuário!");
			e.printStackTrace();
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return resultado;
	}
	
	public int excluirUsuarioDAO(UsuarioVO usuarioVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		int resultado = 0;
		String query = "DELETE FROM USUARIO WHERE IDUSUARIO = " + usuarioVO.getId();
		try {
			resultado = stmt.executeUpdate(query);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao executar a query de exclusão de usuário!");
			e.printStackTrace();
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return resultado;
	}


}