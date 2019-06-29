package exercicio1.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import exercicio1.controller.UsuarioController;
import exercicio1.model.bo.NivelBO;
import exercicio1.model.vo.NivelVO;
import exercicio1.model.vo.UsuarioVO;
import net.miginfocom.swing.MigLayout;

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

		consultarNiveis(); // TODO alterar esta chamada AQUI

		frmCadastroDeUsuarios = new JFrame();
		frmCadastroDeUsuarios.setTitle("Consulta de usu�rios");
		frmCadastroDeUsuarios.setBounds(100, 100, 585, 405);
		frmCadastroDeUsuarios.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCadastroDeUsuarios.getContentPane()
				.setLayout(new MigLayout("", "[290px][80px][160px]", "[30px][30px][30px][230px]"));

		JLabel lblNome = new JLabel("Nome:");
		frmCadastroDeUsuarios.getContentPane().add(lblNome, "cell 0 0,alignx left,aligny center");

		JLabel lblNivel = new JLabel("N�vel:");
		frmCadastroDeUsuarios.getContentPane().add(lblNivel, "cell 0 1,alignx left,aligny center");

		txtNome = new JTextField();
		frmCadastroDeUsuarios.getContentPane().add(txtNome, "cell 0 0 2 1,grow");
		txtNome.setColumns(10);

		cbNivel = new JComboBox();
		cbNivel.setModel(new DefaultComboBoxModel(niveis.toArray()));

		cbNivel.setSelectedIndex(-1);
		frmCadastroDeUsuarios.getContentPane().add(cbNivel, "cell 0 1 2 1,grow");

		JButton btnConsultarPorNivel = new JButton("Consultar por n�vel");
		btnConsultarPorNivel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UsuarioController controller = new UsuarioController();
				NivelVO nivel = (NivelVO) cbNivel.getModel().getSelectedItem();
				ArrayList<UsuarioVO> usuarios = new ArrayList<UsuarioVO>();
				usuarios = controller.consultarPorNivel(nivel);
				limparTabelaUsuarios();
				atualizarTabelaUsuarios(usuarios);
			}
		});
		frmCadastroDeUsuarios.getContentPane().add(btnConsultarPorNivel, "cell 2 1,grow");

		JButton btnConsultarPorNome = new JButton("Consultar por nome");
		btnConsultarPorNome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UsuarioController controller = new UsuarioController();
				ArrayList<UsuarioVO> usuarios = new ArrayList<UsuarioVO>();
				usuarios = controller.consultarPorNome(txtNome.getText());
				limparTabelaUsuarios();
				atualizarTabelaUsuarios(usuarios);
			}
		});
		frmCadastroDeUsuarios.getContentPane().add(btnConsultarPorNome, "cell 2 0,grow");

		JButton btnConsultarTodos = new JButton("Consultar todos");
		frmCadastroDeUsuarios.getContentPane().add(btnConsultarTodos, "cell 0 2,grow");
		btnConsultarTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UsuarioController controller = new UsuarioController();
				ArrayList<UsuarioVO> usuarios = new ArrayList<UsuarioVO>();
				usuarios = controller.consultarTodosUsuariosController();
				limparTabelaUsuarios();
				atualizarTabelaUsuarios(usuarios);
			}
		});

		JButton btnLimpar = new JButton("Limpar");
		frmCadastroDeUsuarios.getContentPane().add(btnLimpar, "cell 1 2 2 1,grow");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNome.setText("");
				cbNivel.setSelectedIndex(-1);
				limparTabelaUsuarios();
			}
		});

		tblUsuarios = new JTable();
		tblUsuarios.setVisible(true);

		tblUsuarios
				.setModel(new DefaultTableModel(new Object[][] { { "id", "Nome" }, }, new String[] { "id", "Nome" }));
		frmCadastroDeUsuarios.getContentPane().add(tblUsuarios, "cell 0 3 3 1,grow");
	}

	protected void limparTabelaUsuarios() {
		DefaultTableModel model = (DefaultTableModel) tblUsuarios.getModel();
		model.setRowCount(0);
	}

	protected void atualizarTabelaUsuarios(ArrayList<UsuarioVO> usuarios) {
		DefaultTableModel model = (DefaultTableModel) tblUsuarios.getModel();

		Object novaLinha[] = new Object[2];
		for (UsuarioVO usuario : usuarios) {
			novaLinha[0] = usuario.getId();
			novaLinha[1] = usuario.getNome();
			model.addRow(novaLinha);
		}
	}

	private void consultarNiveis() {
		// TODO trocar para uma chamada ao BO de Nivel
		NivelBO nivelBO = new NivelBO();
		niveis = nivelBO.listarNiveis();
	}
}