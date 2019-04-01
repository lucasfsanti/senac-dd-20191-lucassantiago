package exercicio1.model.bo;

import java.util.ArrayList;

import exercicio1.model.dao.UsuarioDAO;
import exercicio1.model.vo.UsuarioVO;

public class UsuarioBO {
	
	public boolean validarNome(String nome) {
		if(nome.trim().length() >= 3) {
			return true;
		} else return false;
	}
	
	public boolean validarEmail(String email) {
		String[] emailDividido = email.split("@");
		if(emailDividido.length == 2) {
			if(emailDividido[1].length() > 0 && emailDividido[2].length() > 0) {
				return true;
			} else return false;
		} else return false;
	}
	
	public boolean validarSenha(String senha) {
		if(senha.length() >= 6) {
			return true;
		} else return false;
	}
	
	public String cadastrarUsuarioBO(UsuarioVO usuarioVO) {
		String mensagem = "";
		if(validarNome(usuarioVO.getNome()) == false) {
			mensagem += "Nome inválido. O nome precisa ter 3 ou mais caracteres!\n";
		}
		if(validarEmail(usuarioVO.getEmail()) == false) {
			mensagem += "Email inválido. Digite novamente!\n";
		}
		if(validarSenha(usuarioVO.getSenha()) == false) {
			mensagem += "Senha inválida. A senha precisa ter pelo menos 6 caracteres!\n";
		}
		if(usuarioVO.getNivel() == null) {
			mensagem += "Selecione o nível!\n";
		}
		if(mensagem == "") {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			mensagem += usuarioDAO.cadastrarUsuarioDAO(usuarioVO);
		}
		return mensagem;
	}

	public String excluirUsuarioBO(UsuarioVO usuarioVO, UsuarioVO admin) {
		String mensagem = "";
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		mensagem += usuarioDAO.validarEmailSenha(admin);
		mensagem += usuarioDAO.validarAdmin(admin);
		if(mensagem.equals("")) {
			mensagem += usuarioDAO.excluirUsuarioDAO(usuarioVO);
		}
		return mensagem;
	}

	public ArrayList<Object> consultarTodosUsuariosBO() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		return usuarioDAO.listarUsuarioDAO();
	}

}
