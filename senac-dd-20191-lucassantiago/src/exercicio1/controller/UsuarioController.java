package exercicio1.controller;

import java.util.ArrayList;

import exercicio1.model.bo.UsuarioBO;
import exercicio1.model.vo.NivelVO;
import exercicio1.model.vo.UsuarioVO;

public class UsuarioController {

	public String cadastrarUsuarioController(String nome, String email, String senha, String confirmacaoSenha, NivelVO nivel) {
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
		if(confirmacaoSenha == null || confirmacaoSenha.trim().length() == 0 || !confirmacaoSenha.equals(senha)) {
			mensagem += "Confirme sua senha!\n";
		}
		if(nivel == null) {
			mensagem += "Selecione o nível.\n";
		}
		if(mensagem.equals("")) {
			UsuarioVO usuarioVO = new UsuarioVO();
			usuarioVO.setNome(nome);
			usuarioVO.setEmail(email);
			usuarioVO.setSenha(senha);
			usuarioVO.setNivel(nivel);
			UsuarioBO usuarioBO = new UsuarioBO();
			mensagem += usuarioBO.cadastrarUsuarioBO(usuarioVO);
		}
		return mensagem;
	}

	public String excluirUsuarioController(UsuarioVO usuarioVO, String emailAdmin, String senhaAdmin) {
		String mensagem = "";
		if(usuarioVO == null) {
			mensagem += "Selecione um usuário!\n";
		}
		if(emailAdmin == null || emailAdmin.length() == 0) {
			mensagem += "Digite seu email!\n";
		}
		if(senhaAdmin == null || senhaAdmin.length() ==0) {
			mensagem += "Digite sua senha!\n";
		}
		if(mensagem.equals("")) {
			UsuarioVO admin = new UsuarioVO();
			admin.setEmail(emailAdmin);
			admin.setSenha(senhaAdmin);
			UsuarioBO usuarioBO = new UsuarioBO();
			mensagem += usuarioBO.excluirUsuarioBO(usuarioVO, admin);
		}
		return mensagem;
	}

	public ArrayList<UsuarioVO> consultarTodosUsuariosController() {
		UsuarioBO usuarioBO = new UsuarioBO();
		return usuarioBO.consultarTodosUsuariosBO();
	}

	public ArrayList<UsuarioVO> consultarPorNome(String nome) {
		if(nome == null || nome.length() == 0) {
			System.out.println("Digite um nome!");
			return null;
		} else {
			UsuarioBO usuarioBO = new UsuarioBO();
			return usuarioBO.listarPorNome(nome);
		}
	}

	public ArrayList<UsuarioVO> consultarPorNivel(NivelVO nivel) {
		if(nivel == null ) {
			System.out.println("Selecione um nível!");
			return null;
		} else {
			UsuarioBO usuarioBO = new UsuarioBO();
			return usuarioBO.listarPorNivel(nivel);
		}
	}
	
}
