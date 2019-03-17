package exercicio1.view;

import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

import exercicio1.controller.UsuarioController;
import exercicio1.model.vo.UsuarioVO;

public class Menu {
	
	private static final int OPCAO_MENU_CADASTRAR = 1;
	private static final int OPCAO_MENU_EXCLUIR = 2;
	private static final int OPCAO_MENU_LISTAR = 3;
	private static final int OPCAO_MENU_SAIR = 0;

	Scanner teclado = new Scanner(System.in);
	
	public void apresentarMenu() {
		String texto = OPCAO_MENU_CADASTRAR + " - Cadastrar\n"
				+ OPCAO_MENU_EXCLUIR + " - Excluir\n"
				+ OPCAO_MENU_LISTAR + " - Listar\n"
				+ OPCAO_MENU_SAIR + " - Sair\n\n"
				+ "Digite a opção: ";
		
		String opcaoInformada = JOptionPane.showInputDialog(texto);
		
		try {
			int opcao = Integer.parseInt(opcaoInformada);
			encaminharParaMenu(opcao);
		}
		catch(NumberFormatException nExp) {
			JOptionPane.showMessageDialog(null, "Informe um número inteiro");
			apresentarMenu();
		}
	}

	private void encaminharParaMenu(int opcao) {
		switch(opcao) {
		case OPCAO_MENU_CADASTRAR: {
			menuCadastrar();
			break;
		}
		case OPCAO_MENU_EXCLUIR: {
			menuExcluir();
			break;
		}
		case OPCAO_MENU_LISTAR: {
			menuListar();
			break;
		}
		case OPCAO_MENU_SAIR: {
			menuSair();
			break;
		}
		}
		
	}

	private void menuSair() {
		int opcao = JOptionPane.showConfirmDialog(null, "Deseja sair?");
		
		switch(opcao) {
		case JOptionPane.YES_OPTION:
			break;
		case JOptionPane.NO_OPTION:
			apresentarMenu();
			break;
		case JOptionPane.CANCEL_OPTION:
			apresentarMenu();
			break;
		}
	}

	private void menuListar() {
		UsuarioController usuarioController = new UsuarioController();
		//ArrayList<UsuarioVO> listaUsuarioVO = usuarioController.consultarTodosUsuariosController();

		UsuarioVO usuarioVO1 = new UsuarioVO(0, "Daiane", "daiane@hotmail.com", "123456", 1);
		UsuarioVO usuarioVO2 = new UsuarioVO(1, "Lucas", "lucas@gmail.com", "000000", 2);
		UsuarioVO usuarioVO3 = new UsuarioVO(2, "Maria", "maria@uol.com.br", "******", 1);
		
		ArrayList<UsuarioVO> listaUsuario = new ArrayList();
		listaUsuario.add(usuarioVO1);
		listaUsuario.add(usuarioVO2);
		listaUsuario.add(usuarioVO3);

		for(int i = 0; i < listaUsuario.size(); i++) {
			int cont = i + 1;
			JOptionPane.showMessageDialog(null, listaUsuario.get(i) + "\n" + cont + " de " + listaUsuario.size());
		}
	}

	private void menuExcluir() {
		UsuarioVO usuarioVO = new UsuarioVO();
		
		usuarioVO.setId(Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o ID do usuário a ser excluído: ")));
		
		int opcao = JOptionPane.showConfirmDialog(null, "Confirma a Exclusão do usuário de ID " + usuarioVO.getId() + "?");
		
		switch(opcao) {
		case JOptionPane.YES_OPTION:
			UsuarioController usuarioController = new UsuarioController();
			usuarioController.excluirUsuarioController(usuarioVO);
			break;
		case JOptionPane.NO_OPTION:
			menuExcluir();
			break;
		case JOptionPane.CANCEL_OPTION:
			apresentarMenu();
			break;
		}
	}

	private void menuCadastrar() {
		UsuarioVO usuarioVO = new UsuarioVO();
		
		usuarioVO.setNome(JOptionPane.showInputDialog(null, "Nome: "));
		usuarioVO.setEmail(JOptionPane.showInputDialog(null, "Email: "));
		usuarioVO.setSenha(JOptionPane.showInputDialog(null, "Senha: "));
		usuarioVO.setNivel(Integer.parseInt(JOptionPane.showInputDialog(null, "Nível: ")));
		
		UsuarioController usuarioController = new UsuarioController();
		usuarioController.cadastrarUsuarioController(usuarioVO);
		
		JOptionPane.showMessageDialog(null, "Usuário Cadastrado:\n\n" + usuarioVO);
	}
	
}
