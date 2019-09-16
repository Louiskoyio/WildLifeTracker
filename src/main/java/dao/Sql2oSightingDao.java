package dao;
import models.Sighting;
import org.sql2o.*;
import java.util.List;



public class Sql2oSightingDao implements SightingDao{

    private final Sql2o sql2o;

    public Sql2oSightingDao(Sql2o sql2o) { this.sql2o = sql2o; }

    @Override
    public List<Sighting> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM sightings") //raw sql
                    .executeAndFetch(Sighting.class); //fetch a list
        }
    }

    @Override
    public void add(Sighting sighting) {
        String sql = "INSERT INTO sightings (animal_name,location,ranger_name) VALUES (:animal_name,:location,:ranger_name)";
        try(Connection con = sql2o.open()){ //try to open a connection
            con.createQuery(sql) //make a new variable
                    .executeUpdate();

        } catch (Sql2oException ex) {
            System.out.println(ex); //oops we have an error!
        }
    }
}
