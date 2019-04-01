package exercicio1.model.bo;

import java.util.ArrayList;

import exercicio1.model.dao.NivelDAO;

public class NivelBO {
	
	public ArrayList<Object> listarNiveis() {
		NivelDAO nivelDAO = new NivelDAO();
		return nivelDAO.listarNivelVO();
	}

}
