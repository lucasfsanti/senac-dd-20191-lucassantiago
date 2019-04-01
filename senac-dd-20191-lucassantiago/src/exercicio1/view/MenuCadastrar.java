package exercicio1.view;

import javax.swing.JOptionPane;

import exercicio1.controller.UsuarioController;
import exercicio1.model.vo.NivelVO;
import exercicio1.model.vo.UsuarioVO;

public class MenuCadastrar {

	public void apresentarMenuCadastrar() {
		UsuarioVO usuarioVO = new UsuarioVO();
			
		String nome = JOptionPane.showInputDialog(null, "Nome: ");
		String email = JOptionPane.showInputDialog(null, "Email: ");
		String senha = JOptionPane.showInputDialog(null, "Senha: ");
		String confirmacaoSenha = JOptionPane.showInputDialog(null, "Digite sua senha Novamente: ");
		NivelVO nivelVO = new NivelVO();
		nivelVO.setIdNivel(Integer.parseInt(JOptionPane.showInputDialog(null, "Nível: ")));
		usuarioVO.setNivel(nivelVO);
		
		UsuarioController usuarioController = new UsuarioController();
		usuarioController.cadastrarUsuarioController(nome, email, senha, confirmacaoSenha, nivelVO);
		
		JOptionPane.showMessageDialog(null, "Usuário Cadastrado:\n\n" + usuarioVO);
	}
		

		
}


