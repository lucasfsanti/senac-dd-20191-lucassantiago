package exercicio05;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import exercicio1.controller.UsuarioController;
import exercicio1.model.bo.NivelBO;
import exercicio1.model.vo.NivelVO;

public class CadastroUsuarioComGroupLayout extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtEmail;
	private JPasswordField pwdSenha;
	private JPasswordField pwdConfirmacao;
	private JComboBox cbxNivel;
	private ArrayList<NivelVO> niveis;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroUsuarioComGroupLayout frame = new CadastroUsuarioComGroupLayout();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CadastroUsuarioComGroupLayout() {

		setTitle("Cadastro de Usuários");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 315);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblNome = new JLabel("Nome:");

		txtNome = new JTextField();
		txtNome.setColumns(10);

		JLabel lblEmail = new JLabel("Email:");

		txtEmail = new JTextField();
		txtEmail.setColumns(10);

		JLabel lblSenha = new JLabel("Senha:");

		pwdSenha = new JPasswordField();

		JLabel lblConfirmacao = new JLabel("Confirmação:");

		pwdConfirmacao = new JPasswordField();

		JLabel lblNve = new JLabel("Nível:");

		cbxNivel = new JComboBox();
		consultarNiveis();
		cbxNivel.setModel(new DefaultComboBoxModel(niveis.toArray()));

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String senha = new String(pwdSenha.getPassword());
				String confirmacaoSenha = new String(pwdConfirmacao.getPassword());
				NivelVO nivel = (NivelVO) cbxNivel.getModel().getSelectedItem();

				UsuarioController controller = new UsuarioController();
				JOptionPane.showMessageDialog(null, controller.cadastrarUsuarioController(txtNome.getText(),
						txtEmail.getText(), senha, confirmacaoSenha, nivel));
				consultarNiveis();
			}
		});

		JButton btnSalvar = new JButton("Salvar");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addGroup(gl_contentPane
				.createSequentialGroup().addGap(6)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addGap(89)
								.addComponent(btnLimpar, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
								.addGap(49)
								.addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(gl_contentPane.createSequentialGroup().addGroup(gl_contentPane
								.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNve, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
								.addComponent(lblSenha, GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
								.addComponent(lblEmail, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 51,
										Short.MAX_VALUE)
								.addComponent(lblNome, GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)).addGap(20)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(txtEmail, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 347,
												Short.MAX_VALUE)
										.addComponent(txtNome, GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
										.addGroup(gl_contentPane.createSequentialGroup()
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
														.addComponent(cbxNivel, 0, 347, Short.MAX_VALUE)
														.addGroup(gl_contentPane.createSequentialGroup()
																.addComponent(pwdSenha, GroupLayout.DEFAULT_SIZE, 137,
																		Short.MAX_VALUE)
																.addPreferredGap(ComponentPlacement.RELATED)
																.addComponent(lblConfirmacao).addGap(20)
																.addComponent(pwdConfirmacao, GroupLayout.DEFAULT_SIZE,
																		122, Short.MAX_VALUE)))))
								.addPreferredGap(ComponentPlacement.RELATED)))
				.addGap(0)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addGap(6).addComponent(txtNome,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(9).addComponent(lblNome)))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEmail))
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
						.createSequentialGroup().addGap(6)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(pwdConfirmacao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(pwdSenha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblConfirmacao))))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(9).addComponent(lblSenha)))
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addGap(31)
								.addComponent(cbxNivel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGap(56)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(btnLimpar)
										.addComponent(btnSalvar)))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(34).addComponent(lblNve)))));
		contentPane.setLayout(gl_contentPane);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String senha = new String(pwdSenha.getPassword());
				String confirmacaoSenha = new String(pwdConfirmacao.getPassword());
				NivelVO nivel = (NivelVO) cbxNivel.getModel().getSelectedItem();

				UsuarioController controller = new UsuarioController();
				JOptionPane.showMessageDialog(null, controller.cadastrarUsuarioController(txtNome.getText(),
						txtEmail.getText(), senha, confirmacaoSenha, nivel));
				consultarNiveis();
			}
		});
	}

	protected void consultarNiveis() {
		NivelBO bo = new NivelBO();
		niveis = bo.listarNiveis();
		cbxNivel.setModel(new DefaultComboBoxModel(niveis.toArray()));
		cbxNivel.setSelectedIndex(-1);
	}

}
