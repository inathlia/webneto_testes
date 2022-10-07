package app;

import dao.DAO;
import dao.AdmDAO;
import model.Adm;

public class Principal {
	public static void main(String[] args) {
		DAO dao = new DAO();
		
		dao.conectar();
		
		AdmDAO admDAO = new AdmDAO();
		
		Adm adm = new Adm(-1, "amanda", "234");
		if(admDAO.insert(adm) == true) {
			System.out.println("Inserção com sucesso -> " + adm.toString());
		}
	}
}
