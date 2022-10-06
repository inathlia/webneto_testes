package app;

import static spark.Spark.*;

import service.TutorialService;

public class TutorialAplicacao {
	
	private static TutorialService tutorialService = new TutorialService();
	
    public static void main(String[] args) {
        port(6789); //verificar porta

        post("/tutorial", (request, response) -> tutorialService.insert(request, response));

        get("/tutorial/:id", (request, response) -> tutorialService.get(request, response));

        get("/tutorial/update/:id", (request, response) -> tutorialService.update(request, response));

        get("/tutorial/delete/:id", (request, response) -> tutorialService.delete(request, response));

        //get("/tutorial", (request, response) -> tutorialService.getAll(request, response));
               
    }
}