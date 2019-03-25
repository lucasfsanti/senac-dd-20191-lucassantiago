package exercicio1.view;

import javax.swing.JOptionPane;

import exercicio1.controller.UsuarioController;
import exercicio1.model.vo.NivelVO;
import exercicio1.model.vo.UsuarioVO;

public class MenuCadastrar {

	public void apresentarMenuCadastrar() {
		UsuarioVO usuarioVO = new UsuarioVO();
			
		usuarioVO.setNome(JOptionPane.showInputDialog(null, "Nome: "));
		usuarioVO.setEmail(JOptionPane.showInputDialog(null, "Email: "));
		usuarioVO.setSenha(JOptionPane.showInputDialog(null, "Senha: "));
		NivelVO nivelVO = new NivelVO();
		nivelVO.setIdNivel(Integer.parseInt(JOptionPane.showInputDialog(null, "Nível: ")));
		usuarioVO.setNivel(nivelVO);
		
		UsuarioController usuarioController = new UsuarioController();
		usuarioController.cadastrarUsuarioController(usuarioVO);
		
		JOptionPane.showMessageDialog(null, "Usuário Cadastrado:\n\n" + usuarioVO);
	}
		

		
}


