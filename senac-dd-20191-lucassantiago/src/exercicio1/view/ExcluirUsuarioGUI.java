package exercicio1.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import exercicio1.controller.UsuarioController;
import exercicio1.model.vo.UsuarioVO;

import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class ExcluirUsuarioGUI {

	private JFrame frmExcluirUsuario;
	private JTextField txtEmail;
	private JPasswordField pfSenha;
	private ArrayList<UsuarioVO> usuarios;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExcluirUsuarioGUI window = new ExcluirUsuarioGUI();
					window.frmExcluirUsuario.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ExcluirUsuarioGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		consultarUsuarios();
		
		frmExcluirUsuario = new JFrame();
		frmExcluirUsuario.setTitle("Excluir Usuário");
		frmExcluirUsuario.setBounds(100, 100, 405, 215);
		frmExcluirUsuario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmExcluirUsuario.getContentPane().setLayout(null);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(20, 20, 55, 15);
		frmExcluirUsuario.getContentPane().add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(76, 16, 299, 22);
		frmExcluirUsuario.getContentPane().add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(20, 48, 55, 15);
		frmExcluirUsuario.getContentPane().add(lblSenha);
		
		pfSenha = new JPasswordField();
		pfSenha.setBounds(76, 48, 299, 22);
		frmExcluirUsuario.getContentPane().add(pfSenha);
		
		JLabel lblUsuario = new JLabel("Usuário:");
		lblUsuario.setBounds(20, 76, 56, 15);
		frmExcluirUsuario.getContentPane().add(lblUsuario);
		
		JComboBox cbUsuarios = new JComboBox();
		
		cbUsuarios.setBounds(76, 76, 299, 22);
		frmExcluirUsuario.getContentPane().add(cbUsuarios);
		cbUsuarios.setModel(new DefaultComboBoxModel(usuarios.toArray()));
		cbUsuarios.setSelectedIndex(-1);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = txtEmail.getText();
				String senha = new String(pfSenha.getPassword());
				UsuarioVO usuarioVO = (UsuarioVO) cbUsuarios.getModel().getSelectedItem();
				
				UsuarioController controller = new UsuarioController();
				JOptionPane.showMessageDialog(frmExcluirUsuario, controller.excluirUsuarioController(usuarioVO, email, senha));
			}
		});
		btnExcluir.setBounds(20, 117, 160, 35);
		frmExcluirUsuario.getContentPane().add(btnExcluir);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtEmail.setText("");
				pfSenha.setText("");
				cbUsuarios.setSelectedIndex(-1);
			}
		});
		btnLimpar.setBounds(215, 117, 160, 35);
		frmExcluirUsuario.getContentPane().add(btnLimpar);
	}

	private void consultarUsuarios() {
		UsuarioController controller = new UsuarioController();
		usuarios = controller.consultarTodosUsuariosController();
	}
}
