package exercicio1.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import exercicio1.model.vo.NivelVO;

public class NivelDAO {
	
	public ArrayList<NivelVO> listarNivelVO() {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		ArrayList<NivelVO> listaNiveis = new ArrayList<NivelVO>();
		String query = "SELECT IDNIVEL, DESCRICAO FROM NIVEL";
		try {
			resultado = stmt.executeQuery(query);
			while(resultado.next()) {
				NivelVO nivel = new NivelVO();
				nivel.setIdNivel(Integer.parseInt(resultado.getString(1)));
				nivel.setDescricao(resultado.getString(2));
				listaNiveis.add(nivel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return listaNiveis;
	}

}
