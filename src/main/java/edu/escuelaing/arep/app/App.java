package edu.escuelaing.arep.app;

import edu.escuealing.arep.HttpService.HttpService;

import static spark.Spark.*;

public class App {

    public static void main(String[] args) {
        HttpService service = new HttpService();
        port(getPort());
        staticFiles.location("/public");
        secure("keyStores/ecikeystore.p12", "123456", null, null);
        get("/hello", (req, res) -> "Hello World");
        post("/login",(request, response) -> {
            String res = "Error";
            String req = request.body();
            String[] json = req.replace("\"", "").replace("[", "").replace("]", "")
                    .replace("{", "").replace("}","").replace("user:", "")
                    .replace("password:","").split(",");

            for (int i = 0; i < json.length; i++) {
                System.out.println(json[i]);
            }
            if (json[0].equals("edwin@eci") && json[1].equals("123456")) {
                request.session(true).attribute("isLogin", true);
                res = "Paso";
            } else {
                response.status(401);
            }
            return res;
        });

        get("/islogin", (request, response) -> {
            request.session(true);
            if (request.session().isNew()) {
                request.session().attribute("isLogin", false);
            }
            boolean bool = (request.session().attribute("isLogin"));
            System.out.println(bool);
            return String.valueOf(bool);
        });

        get("/off", (request, response) -> {
            request.session(true);
            if (request.session().isNew()) {
                request.session().attribute("isLogin", false);
            }
            System.out.println("llego get");
            request.session().attribute("isLogin", false);
            return "salio satisfactoriamente";
        });

        get("/palabras", (request, response) -> {
            return service.getPalabras();
        });
    }

    /**
     * Da el puerto solicitado por la aplicación
     * @return puerto
     */

    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}