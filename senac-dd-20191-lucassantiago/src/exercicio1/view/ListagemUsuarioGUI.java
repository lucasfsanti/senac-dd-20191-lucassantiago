package exercicio1.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import exercicio1.controller.UsuarioController;
import exercicio1.model.bo.NivelBO;
import exercicio1.model.vo.NivelVO;
import exercicio1.model.vo.UsuarioVO;

/**
 * 
 * Tela de listagem de usu�rios (item 1c da Atividade 4)
 * 
 * @author Vilmar C�sar Pereira J�nior
 *
 */
public class ListagemUsuarioGUI {

	private JFrame frmCadastroDeUsuarios;
	private JTextField txtNome;
	private JComboBox cbNivel;
	private List<NivelVO> niveis;
	private JTable tblUsuarios;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListagemUsuarioGUI window = new ListagemUsuarioGUI();
					window.frmCadastroDeUsuarios.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ListagemUsuarioGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		//TODO consultar os n�veis no banco (criei na m�o aqui :D)
		consultarNiveis(); //TODO alterar esta chamada AQUI

		frmCadastroDeUsuarios = new JFrame();
		frmCadastroDeUsuarios.setTitle("Consulta de usu�rios");
		frmCadastroDeUsuarios.setBounds(100, 100, 585, 405);
		frmCadastroDeUsuarios.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCadastroDeUsuarios.getContentPane().setLayout(null);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(20, 20, 55, 15);
		frmCadastroDeUsuarios.getContentPane().add(lblNome);

		JLabel lblNivel = new JLabel("N�vel:");
		lblNivel.setBounds(20, 55, 55, 15);
		frmCadastroDeUsuarios.getContentPane().add(lblNivel);

		txtNome = new JTextField();
		txtNome.setBounds(70, 15, 320, 28);
		frmCadastroDeUsuarios.getContentPane().add(txtNome);
		txtNome.setColumns(10);

		//Novo componente: Combobox
		cbNivel = new JComboBox();
		cbNivel.setModel(new DefaultComboBoxModel(niveis.toArray()));

		//Inicia sem nada selecionado no combo
		cbNivel.setSelectedIndex(-1);

		cbNivel.setBounds(70, 50, 320, 28);
		frmCadastroDeUsuarios.getContentPane().add(cbNivel);

		JButton btnConsultarPorNivel = new JButton("Consultar por n�vel");
		btnConsultarPorNivel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UsuarioController controller = new UsuarioController();
				ArrayList<UsuarioVO> usuarios = new ArrayList<UsuarioVO>();
				NivelVO nivel = (NivelVO) cbNivel.getModel().getSelectedItem();
				usuarios = controller.consultarPorNivel(nivel);
			}
		});
		btnConsultarPorNivel.setBounds(390, 49, 160, 30);
		frmCadastroDeUsuarios.getContentPane().add(btnConsultarPorNivel);

		JButton btnConsultarPorNome = new JButton("Consultar por nome");
		btnConsultarPorNome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UsuarioController controller = new UsuarioController();
				ArrayList<UsuarioVO> usuarios = new ArrayList<UsuarioVO>();
				usuarios = controller.consultarPorNome(txtNome.getText());
				//Chamar sempre que for atualizar a tabela com os usu�rios
				atualizarTabelaUsuarios(usuarios);
			}
		});
		btnConsultarPorNome.setBounds(390, 14, 160, 30);
		frmCadastroDeUsuarios.getContentPane().add(btnConsultarPorNome);

		JButton btnConsultarTodos = new JButton("Consultar todos");
		btnConsultarTodos.setBounds(70, 85, 240, 30);
		frmCadastroDeUsuarios.getContentPane().add(btnConsultarTodos);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(310, 85, 240, 30);
		frmCadastroDeUsuarios.getContentPane().add(btnLimpar);

		//Novo componente: tabela
		tblUsuarios = new JTable();
		tblUsuarios.setVisible(true);

		//Cria a tabela vazia apenas com as colunas
		tblUsuarios.setModel(new DefaultTableModel(
			new Object[][] {
				{"id", "Nome"},
			},
			new String[] {
				"id", "Nome"
			}
		));

		tblUsuarios.setBounds(70, 120, 480, 230);
		frmCadastroDeUsuarios.getContentPane().add(tblUsuarios);
	}

	/**
	 * Atualiza o JTable de usu�rios.
	 * @param usuarios
	 */
	protected void atualizarTabelaUsuarios(ArrayList<UsuarioVO> usuarios) {
		DefaultTableModel model = (DefaultTableModel) tblUsuarios.getModel();
		
		Object novaLinha[] = new Object[2];
		for(UsuarioVO usuario: usuarios){
			novaLinha[0] = usuario.getId();
			novaLinha[1] = usuario.getNome();
			model.addRow(novaLinha);
		}
	}

	private void consultarNiveis() {
		//TODO trocar para uma chamada ao BO de Nivel	
		NivelBO nivelBO = new NivelBO();
		niveis = nivelBO.listarNiveis();
	}
}