package exercicio1.model.bo;

import java.util.ArrayList;

import exercicio1.model.dao.NivelDAO;
import exercicio1.model.vo.NivelVO;

public class NivelBO {
	
	public ArrayList<NivelVO> listarNiveis() {
		NivelDAO nivelDAO = new NivelDAO();
		return nivelDAO.listarNivelVO();
	}

}
