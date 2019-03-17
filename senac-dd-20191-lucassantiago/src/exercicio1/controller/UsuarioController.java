package exercicio1.controller;

import java.util.ArrayList;

import exercicio1.model.bo.UsuarioBO;
import exercicio1.model.vo.UsuarioVO;

public class UsuarioController {

	public void cadastrarUsuarioController(UsuarioVO usuarioVO) {
		
		UsuarioBO usuarioBO = new UsuarioBO();
		usuarioBO.cadastrarUsuarioBO(usuarioVO);
		
	}

	public void excluirUsuarioController(UsuarioVO usuarioVO) {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<UsuarioVO> consultarTodosUsuariosController() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
