package service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import dao.SiteDAO;
import model.Site;
import spark.Request;
import spark.Response;


public class SiteService {

	private SiteDAO siteDAO;

	public SiteService() throws IOException {
		siteDAO = new SiteDAO(); // o que é produto.dat?
	}

	public Object add(Request request, Response response) {
		String link = request.queryParams("link");
		String logo = request.queryParams("logo");
		String nome = request.queryParams("nome");

		//int id = siteDAO.getMaxId() + 1;

		Site site = new Site(-1, link, logo, nome);

		siteDAO.insert(site);

		response.status(201); // 201 Created
		return nome;
	}

	public Object get(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));
		
		Site site = (Site) siteDAO.get(id);
		
		if (site != null) {
    	    response.header("Content-Type", "application/xml");
    	    response.header("Content-Encoding", "UTF-8");

            return "<site>\n" + 
            		"\t<id>" + site.getId() + "</id>\n" +
            		"\t<link>" + site.getLink() + "</link>\n" +
            		"\t<logo>" + site.getLogo() + "</logo>\n" +
					"\t<nome>" + site.getNome() + "</nome>\n" +
            		"</site>\n";
        } else {
            response.status(404); // 404 Not found
            return "Site " + id + " não encontrado.";
        }

	}

	public Object update(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        
		Site site = (Site) siteDAO.get(id);

        if (site != null) {
        	site.setLink(request.queryParams("link"));
        	site.setLogo(request.queryParams("logo"));
			site.setNome(request.queryParams("nome"));

        	siteDAO.update(site);
        	
            return id;
        } else {
            response.status(404); // 404 Not found
            return "Site não encontrado.";
        }

	}

	public Object remove(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));

        Site site = (Site) siteDAO.get(id);

        if (site != null) {

            siteDAO.delete(id);

            response.status(200); // success
        	return id;
        } else {
            response.status(404); // 404 Not found
            return "Site não encontrado.";
        }
	}

	// public Object getAll(Request request, Response response) {
	// 	StringBuffer returnValue = new StringBuffer("<adm type=\"array\">");
	// 	for (Site site : siteDAO.getAll()) {
	// 		returnValue.append("\n<site>\n" + 
    //         		"\t<id>" + site.getId() + "</id>\n" +
    //         		"\t<link>" + site.getLink() + "</link>\n" +
    //         		"\t<logo>" + adm.getLogo() + "</logo>\n" +
	// 							"\t<nome>" + adm.getNome() + "</nome>\n" +
    //         		"</site>\n");
	// 	}
	// 	returnValue.append("</site>");
	//     response.header("Content-Type", "application/xml");
	//     response.header("Content-Encoding", "UTF-8");
	// 	return returnValue.toString();
	// }
}
