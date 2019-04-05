package exercicio1.model.bo;

import java.util.ArrayList;

import exercicio1.model.dao.UsuarioDAO;
import exercicio1.model.vo.NivelVO;
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
			if(emailDividido[0].length() > 0 && emailDividido[1].length() > 0) {
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
			mensagem += "Nome inv�lido. O nome precisa ter 3 ou mais caracteres!\n";
		}
		if(validarEmail(usuarioVO.getEmail()) == false) {
			mensagem += "Email inv�lido. Digite novamente!\n";
		}
		if(validarSenha(usuarioVO.getSenha()) == false) {
			mensagem += "Senha inv�lida. A senha precisa ter pelo menos 6 caracteres!\n";
		}
		if(usuarioVO.getNivel() == null) {
			mensagem += "Selecione o n�vel!\n";
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

	public ArrayList<UsuarioVO> consultarTodosUsuariosBO() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		return usuarioDAO.listarUsuarioDAO();
	}

	public ArrayList<UsuarioVO> listarPorNome(String nome) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		return usuarioDAO.listarPorNome(nome);
	}

	public ArrayList<UsuarioVO> listarPorNivel(NivelVO nivel) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		return usuarioDAO.listarPorNivel(nivel);
	}

}
