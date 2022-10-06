package service;

import dao.AdmDAO;
import model.Adm;
import spark.Request;
import spark.Response;


public class AdmService {

	private AdmDAO admDAO;

	public AdmService(){
		admDAO = new AdmDAO(); 
	}

	public Object add(Request request, Response response) {
		String nome = request.queryParams("nome");
		String senha = request.queryParams("senha");

		//int id = admDAO.getMaxId() + 1; //criar maxId nas classes de DAO

		Adm adm = new Adm(-1, nome, senha); //Adm(-1, nome, senha) -> PASSA O ID COMO PARAMETRO MAS QUANDO DER O INSERT VAI IGNORAR POR SER SERIAL

		admDAO.insert(adm); //AQUI É INSERT

		response.status(201); // 201 Created
		return nome;
	}

	public Object get(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));
		
		Adm adm = (Adm) admDAO.get(id);
		
		if (adm != null) {
    	    response.header("Content-Type", "application/xml");
    	    response.header("Content-Encoding", "UTF-8");

            return "<adm>\n" + 
            		"\t<id>" + adm.getId() + "</id>\n" +
            		"\t<nome>" + adm.getNome() + "</nome>\n" +
            		"\t<senha>" + adm.getSenha() + "</senha>\n" +
            		"</adm>\n";
        } else {
            response.status(404); // 404 Not found
            return "Administrador " + id + " não encontrado.";
        }

	}

	public Object update(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        
		Adm adm = (Adm) admDAO.get(id);

        if (adm != null) {
        	adm.setNome(request.queryParams("nome"));
        	adm.setSenha(request.queryParams("senha"));

        	admDAO.update(adm);
        	
            return id;
        } else {
            response.status(404); // 404 Not found
            return "Administrador não encontrado.";
        }

	}

	public Object remove(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));

        Adm adm = (Adm) admDAO.get(id);

        if (adm != null) {

            admDAO.delete(id); //DELETE

            response.status(200); // success
        	return id;
        } else {
            response.status(404); // 404 Not found
            return "Adminsitrador não encontrado.";
        }
	}

	// public Object getAll(Request request, Response response) {
	// 	StringBuffer returnValue = new StringBuffer("<adm type=\"array\">");
	// 	for (Adm adm : admDAO.getAll()) {
	// 		returnValue.append("\n<adm>\n" + 
    //         		"\t<id>" + adm.getId() + "</id>\n" +
    //         		"\t<nome>" + adm.getNome() + "</nome>\n" +
    //         		"\t<senha>" + adm.getSenha() + "</senha>\n" +
    //         		"</adm>\n");
	// 	}
	// 	returnValue.append("</adm>");
	//     response.header("Content-Type", "application/xml");
	//     response.header("Content-Encoding", "UTF-8");
	// 	return returnValue.toString();
	// }
}