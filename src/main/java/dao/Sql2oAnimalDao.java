package dao;
import  models.Animal;
import org.sql2o.*;
import java.util.List;

public class Sql2oAnimalDao implements AnimalDao {

    private final Sql2o sql2o;

    public Sql2oAnimalDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public List<Animal> getAll() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM animals") //raw sql
                   .executeAndFetch(Animal.class); //fetch a list
        }
    }

    @Override
    public Animal findById(int id) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM animals WHERE id = :id")
                    .addParameter("id", id) //key/value pair, key must match above
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(Animal.class); //fetch an individual item
        }

    }

    @Override
    public void makeEndangered(int animal_id, String health, String age) {
        String sql = "INSERT INTO endangered (animal_id,health,age) VALUES (:animal_id,:health,:age)";
        try(Connection con = sql2o.open()){ //try to open a connection
            con.createQuery(sql)
                    .addParameter("animal_id", animal_id)
                    .addParameter("health", health)
                    .addParameter("age", age)
                    .executeUpdate();

        } catch (Sql2oException ex) {
            System.out.println(ex); //oops we have an error!
        }

    }
}