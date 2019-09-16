import dao.*;
import models.Animal;
import models.Endangered;
import models.Sighting;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.*;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        String connectionString = "jdbc:postgresql://localhost:5432/wildlife_tracker"; //connect to todolist, not todolist_test!
        Sql2o sql2o = new Sql2o(connectionString, "louis", "23Armin23");
/*        String connectionString = "jdbc:postgresql://ec2-174-129-227-128.compute-1.amazonaws.com:5432/d8dr0ci5o2fkco";
        Sql2o sql2o = new Sql2o(connectionString, "swyprogbkfbebh", "fc11b54ff1084e1985e9d1bbb0dbccf47cb8172da33f86525b97c1a9161a4624");*/
        Sql2oAnimalDao animalDao= new Sql2oAnimalDao(sql2o);
        Sql2oEndangeredDao endangeredDao= new Sql2oEndangeredDao(sql2o);
        Sql2oSightingDao sightingDao=new Sql2oSightingDao(sql2o);


        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Animal> animals = animalDao.getAll();
            model.put("animals", animals);
            return new ModelAndView(model, "animals.hbs");
        }, new HandlebarsTemplateEngine());

        get("/sightings", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Sighting> sighting = sightingDao.getAll();
            model.put("sighting", sighting);
            return new ModelAndView(model, "sightings.hbs");
        }, new HandlebarsTemplateEngine());

        get("/endangered", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Endangered> endangered = endangeredDao.getAll();
            model.put("endangered", endangered);
            return new ModelAndView(model, "endangered.hbs");
        }, new HandlebarsTemplateEngine());

        get("/sightings/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Sighting> sighting = sightingDao.getAll();
            List<Animal> animal = animalDao.getAll();
            model.put("sighting", sighting);
            model.put("animal", animal);
            return new ModelAndView(model, "sightings-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/sightings", (req, res) -> { //URL to make new task on POST route
            Map<String, Object> model = new HashMap<>();
            String animal_name = req.queryParams("animal_name");
            String ranger_name =  req.queryParams("ranger_name");
            String location = req.queryParams("location");
            System.out.println(location);
            Sighting newSighting = new Sighting(animal_name,location,ranger_name);
            sightingDao.add(newSighting);
            res.redirect("/sightings");
            return null;
        }, new HandlebarsTemplateEngine());
    }


}
