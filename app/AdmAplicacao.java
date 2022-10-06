package app;

import static spark.Spark.*;

import service.AdmService;

public class AdmAplicacao {
	
	private static AdmService admService = new AdmService();
	
    public static void main(String[] args) {
        port(6789); //verificar porta

        post("/adm", (request, response) -> admService.insert(request, response));

        get("/adm/:id", (request, response) -> admService.get(request, response));

        get("/adm/update/:id", (request, response) -> admService.update(request, response));

        get("/adm/delete/:id", (request, response) -> admService.delete(request, response));

        //get("/adm", (request, response) -> admService.getAll(request, response));
               
    }
}