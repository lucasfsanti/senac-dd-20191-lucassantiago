package exercicio05;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.github.lgooddatepicker.components.DatePicker;

import exercicioConsultaComPaginacao.Produto;
import exercicioConsultaComPaginacao.ProdutoController;
import exercicioConsultaComPaginacao.ProdutoSeletor;
import net.miginfocom.swing.MigLayout;

public class ListagemProdutosComMigLayout extends JFrame {

	private static final String COR_AZUL = "Azul";
	private static final String COR_AMARELO = "Amarelo";
	private static final String COR_PRETO = "Preto";
	private static final String COR_VERDE = "Verde";
	private static final String COR_VERMELHO = "Vermelho";
	private static final int TAMANHO_PAGINA = 5;

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtPeso;
	private JTable tblProdutos;
	private JComboBox cbCor;
	private JButton btnConsultar;
	private DatePicker dtInicioCadastro;
	private DatePicker dtFimCadastro;
	private int paginaAtual = 1;
	private int paginaFinal = 1;
	private JButton btnProximo;
	private JButton btnAnterior;
	private JLabel lblPaginaAtual;
	private List<Produto> produtosConsultados;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListagemProdutosComMigLayout frame = new ListagemProdutosComMigLayout();
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
	public ListagemProdutosComMigLayout() {
		setTitle("Consulta de Produtos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 551);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][160px:160px:160px,grow][][160px:160px:160px]",
				"[30px:30px:30px][30px:30px:30px][30px:30px:30px][][30px:30px:30px][30px:30px:30px][30px:30px:30px][][grow][][]"));

		JLabel lblFiltrosParaPesquisa = new JLabel("Filtros para Pesquisa:");
		contentPane.add(lblFiltrosParaPesquisa, "cell 1 0");

		JLabel lblNome = new JLabel("Nome:");
		contentPane.add(lblNome, "cell 0 1,alignx left");

		txtNome = new JTextField();
		contentPane.add(txtNome, "flowx,cell 1 1,grow");
		txtNome.setColumns(10);

		JLabel lblCor = new JLabel("Cor:");
		contentPane.add(lblCor, "cell 2 1,alignx trailing");

		cbCor = new JComboBox();
		cbCor.setModel(new DefaultComboBoxModel(
				new String[] { "---Selecione---", ListagemProdutosComMigLayout.COR_AZUL, ListagemProdutosComMigLayout.COR_AMARELO,
						ListagemProdutosComMigLayout.COR_PRETO, ListagemProdutosComMigLayout.COR_VERDE, ListagemProdutosComMigLayout.COR_VERMELHO }));
		contentPane.add(cbCor, "cell 3 1,grow");

		JLabel lblPeso = new JLabel("Peso:");
		contentPane.add(lblPeso, "cell 0 2,alignx left");

		txtPeso = new JTextField();
		contentPane.add(txtPeso, "cell 1 2,grow");
		txtPeso.setColumns(10);

		JLabel lblPeriodoDeCadastro = new JLabel("Período de Cadastro:");
		contentPane.add(lblPeriodoDeCadastro, "cell 1 4");

		JLabel lblDe = new JLabel("De:");
		contentPane.add(lblDe, "cell 0 5");

		JLabel lblAte = new JLabel("Até");
		contentPane.add(lblAte, "cell 0 6");

		dtInicioCadastro = new DatePicker();
		dtInicioCadastro.getComponentDateTextField().setFont(new Font("Verdana", Font.PLAIN, 22));
		dtInicioCadastro.getComponentToggleCalendarButton().setFont(new Font("Verdana", Font.PLAIN, 22));
		getContentPane().add(dtInicioCadastro, "cell 1 5");

		dtFimCadastro = new DatePicker();
		dtFimCadastro.getComponentDateTextField().setFont(new Font("Verdana", Font.PLAIN, 22));
		dtFimCadastro.getComponentToggleCalendarButton().setFont(new Font("Verdana", Font.PLAIN, 22));
		getContentPane().add(dtFimCadastro, "cell 1 6");

		btnConsultar = new JButton("Consultar");
		contentPane.add(btnConsultar, "cell 3 6,grow");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				calcularPaginas();
				consultarProdutos();
			}
		});

		JSeparator separator = new JSeparator();
		contentPane.add(separator, "cell 0 7 4 1,grow");

		tblProdutos = new JTable();
		tblProdutos.setModel(new DefaultTableModel(new String[][] { { "#", "Nome", "Marca", "Peso", "Dt. Cadastro" }, },
				new String[] { "#", "Nome", "Marca", "Peso", "Dt. Cadastro" }));
		contentPane.add(tblProdutos, "cell 0 8 4 2,grow");

		btnAnterior = new JButton(" < Anterior");
		btnAnterior.setEnabled(false);
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (paginaAtual > 1) {
					paginaAtual--;
					consultarProdutos();
				}
			}
		});
		contentPane.add(btnAnterior, "flowx,cell 1 10,alignx right");

		btnProximo = new JButton("Próximo >");
		btnProximo.setEnabled(false);
		btnProximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (paginaAtual < paginaFinal) {
					paginaAtual++;
					consultarProdutos();
				}
			}
		});
		contentPane.add(btnProximo, "cell 2 10 2 1");

		lblPaginaAtual = new JLabel("1 / 1");
		lblPaginaAtual.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblPaginaAtual, "cell 1 10,alignx right");
	}

	void atualizarBotoes() {
		if (paginaAtual == 1) {
			btnAnterior.setEnabled(false);
		} else {
			btnAnterior.setEnabled(true);
		}
		if (paginaAtual == paginaFinal) {
			btnProximo.setEnabled(false);
		} else {
			btnProximo.setEnabled(true);
		}
	}

	protected void calcularPaginas() {

		ProdutoController controlador = new ProdutoController();
		ProdutoSeletor seletor = new ProdutoSeletor();

		if (cbCor.getSelectedIndex() > 0) {
			seletor.setCorProduto(cbCor.getSelectedItem().toString());
		}

		seletor.setNomeProduto(txtNome.getText());

		if (txtPeso.getText() != null && !txtPeso.getText().isEmpty()) {
			seletor.setPesoProduto(Double.parseDouble(txtPeso.getText()));
		} else {
			seletor.setPesoProduto(-1.0);
		}

		seletor.setDataInicioCadastro(dtInicioCadastro.getDate());
		seletor.setDataFimCadastro(dtFimCadastro.getDate());

		int totalProdutos = controlador.contarProdutos(seletor);

		if (totalProdutos == 0) {
			paginaFinal = 1;
		} else if (totalProdutos % TAMANHO_PAGINA == 0) {
			paginaFinal = totalProdutos / TAMANHO_PAGINA;
		} else {
			paginaFinal = (totalProdutos / TAMANHO_PAGINA) + 1;
		}
		lblPaginaAtual.setText(paginaAtual + " / " + paginaFinal);
	}

	protected void consultarProdutos() {
		lblPaginaAtual.setText(paginaAtual + " / " + paginaFinal);

		ProdutoController controlador = new ProdutoController();
		ProdutoSeletor seletor = new ProdutoSeletor();

		seletor.setPagina(paginaAtual);

		seletor.setLimite(TAMANHO_PAGINA);

		if (cbCor.getSelectedIndex() > 0) {
			seletor.setCorProduto(cbCor.getSelectedItem().toString());
		}

		seletor.setNomeProduto(txtNome.getText());

		if (txtPeso.getText() != null && !txtPeso.getText().isEmpty()) {
			seletor.setPesoProduto(Double.parseDouble(txtPeso.getText()));
		} else {
			seletor.setPesoProduto(-1.0);
		}

		seletor.setDataInicioCadastro(dtInicioCadastro.getDate());
		seletor.setDataFimCadastro(dtFimCadastro.getDate());

		List<Produto> produtos = controlador.listarProdutos(seletor);
		atualizarTabelaProdutos(produtos);
		atualizarBotoes();

	}

	protected void atualizarTabelaProdutos(List<Produto> produtos) {
		produtosConsultados = produtos;

		tblProdutos.setModel(new DefaultTableModel(new String[][] { { "#", "Nome", "Marca", "Peso", "Dt. Cadastro" }, },
				new String[] { "#", "Nome", "Marca", "Peso", "Dt. Cadastro" }));

		DefaultTableModel modelo = (DefaultTableModel) tblProdutos.getModel();

		for (Produto produto : produtos) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String dataFormatada = produto.getDataCadastro().format(formatter);

			String[] novaLinha = new String[] { produto.getId() + "", produto.getNome(), produto.getFabricante(),
					produto.getPeso() + "", dataFormatada };
			modelo.addRow(novaLinha);
		}
	}

}
