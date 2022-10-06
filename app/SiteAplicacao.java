package app;

import static spark.Spark.*;

import service.SiteService;

public class SiteAplicacao {
	
	private static SiteService siteService = new SiteService();
	
    public static void main(String[] args) {
        port(6789); //verificar porta

        post("/site", (request, response) -> siteService.add(request, response));

        get("/site/:id", (request, response) -> siteService.get(request, response));

        get("/site/update/:id", (request, response) -> siteService.update(request, response));

        get("/site/delete/:id", (request, response) -> siteService.remove(request, response));

        //get("/site", (request, response) -> siteService.getAll(request, response));
               
    }
}
