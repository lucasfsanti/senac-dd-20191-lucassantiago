package exercicio1.view;

import java.util.Scanner;
import javax.swing.JOptionPane;

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
			break;
		}
		case OPCAO_MENU_EXCLUIR: {
			break;
		}
		case OPCAO_MENU_LISTAR: {
			break;
		}
		case OPCAO_MENU_SAIR: {
		break;
		}
		}
		
	}
	
}
