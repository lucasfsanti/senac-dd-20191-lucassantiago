package exercicio1.controller;

import java.util.ArrayList;

import exercicio1.model.bo.UsuarioBO;
import exercicio1.model.vo.NivelVO;
import exercicio1.model.vo.UsuarioVO;

public class UsuarioController {

	public String cadastrarUsuarioController(String nome, String email, String senha, String confirmacaoSenha, String nivel) {
		String mensagem = "";
		if(nome == null || nome.trim().length() == 0) {
			mensagem += "Digite seu nome.\n";
		}
		if(email == null || email.trim().length() == 0) {
			mensagem += "Digite seu email.\n";
		}
		if(senha == null || senha.trim().length() == 0) {
			mensagem += "Digite uma senha.\n";
		}
		if(confirmacaoSenha == null || confirmacaoSenha.trim().length() == 0 || confirmacaoSenha.equals(senha)) {
			mensagem += "Confirme sua senha!\n";
		}
		if(nivel == null || nivel.trim().length() == 0) {
			mensagem += "Digite o nível.\n";
		}
		if(mensagem.equals("")) {
			UsuarioVO usuarioVO = new UsuarioVO();
			usuarioVO.setNome(nome);
			usuarioVO.setEmail(email);
			usuarioVO.setSenha(senha);
			NivelVO nivelVO = new NivelVO();
			nivelVO.setDescricao(nivel);
			UsuarioBO usuarioBO = new UsuarioBO();
			mensagem = usuarioBO.cadastrarUsuarioBO(usuarioVO);
		}
		return mensagem;
	}

	public String excluirUsuarioController(String nome, String email, String senha, String confirmacaoSenha, String nivel) {
		String mensagem = "";
		if(nome == null || nome.trim().length() == 0) {
			mensagem += "Digite seu nome.\n";
		}
		if(email == null || email.trim().length() == 0) {
			mensagem += "Digite seu email.\n";
		}
		if(senha == null || senha.trim().length() == 0) {
			mensagem += "Digite uma senha.\n";
		}
		if(confirmacaoSenha == null || confirmacaoSenha.trim().length() == 0 || confirmacaoSenha.equals(senha)) {
			mensagem += "Confirme sua senha!\n";
		}
		if(nivel == null || nivel.trim().length() == 0) {
			mensagem += "Digite o nível.\n";
		}
		if(mensagem.equals("")) {
			UsuarioVO usuarioVO = new UsuarioVO();
			usuarioVO.setNome(nome);
			usuarioVO.setEmail(email);
			usuarioVO.setSenha(senha);
			NivelVO nivelVO = new NivelVO();
			nivelVO.setDescricao(nivel);
			UsuarioBO usuarioBO = new UsuarioBO();
			mensagem = usuarioBO.excluirUsuarioBO(usuarioVO);
		}
		return mensagem;
	}

	public ArrayList<Object> consultarTodosUsuariosController() {
		UsuarioBO usuarioBO = new UsuarioBO();
		return usuarioBO.consultarTodosUsuariosBO();
	}
	
	

}
