package exercicio1.view;

import javax.swing.JOptionPane;

import exercicio1.controller.UsuarioController;
import exercicio1.model.vo.UsuarioVO;

public class MenuExcluir {

	public void apresentarMenuExcluir() {
		UsuarioVO usuarioVO = new UsuarioVO();
		
		usuarioVO.setId(Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o ID do usu�rio a ser exclu�do: ")));
		
		String emailAdmin = JOptionPane.showInputDialog(null, "Digite seu email: ");
		String senhaAdmin = JOptionPane.showInputDialog(null, "Digite sua senha: ");
		
		int opcao = JOptionPane.showConfirmDialog(null, "Confirma a Exclus�o do usu�rio de ID " + usuarioVO.getId() + "?");
		
		switch(opcao) {
		case JOptionPane.YES_OPTION:
			UsuarioController usuarioController = new UsuarioController();
			usuarioController.excluirUsuarioController(usuarioVO, emailAdmin, senhaAdmin);
			break;
		case JOptionPane.NO_OPTION:
			apresentarMenuExcluir();
			break;
		case JOptionPane.CANCEL_OPTION:
			Menu menu = new Menu();
			menu.apresentarMenu();
			break;
		}
	}

}
