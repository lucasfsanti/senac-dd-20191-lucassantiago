package exercicio1.view;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import exercicio1.controller.UsuarioController;
import exercicio1.model.vo.UsuarioVO;

public class MenuListar {

	public void apresentarMenuListar() {
		UsuarioController usuarioController = new UsuarioController();
		ArrayList<Object> listaUsuarioVO = usuarioController.consultarTodosUsuariosController();

		//UsuarioVO usuarioVO1 = new UsuarioVO(0, "Daiane", "daiane@hotmail.com", "123456", 1);
		//UsuarioVO usuarioVO2 = new UsuarioVO(1, "Lucas", "lucas@gmail.com", "000000", 2);
		//UsuarioVO usuarioVO3 = new UsuarioVO(2, "Maria", "maria@uol.com.br", "******", 1);
		
		ArrayList<UsuarioVO> listaUsuario = new ArrayList();
		//listaUsuario.add(usuarioVO1);
		//listaUsuario.add(usuarioVO2);
		//listaUsuario.add(usuarioVO3);

		for(int i = 0; i < listaUsuario.size(); i++) {
			int cont = i + 1;
			JOptionPane.showMessageDialog(null, listaUsuario.get(i) + "\n" + cont + " de " + listaUsuario.size());
		}
	}

}
