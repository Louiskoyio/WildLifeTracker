package dao;

import models.Endangered;
import org.sql2o.*;

import java.util.List;

public class Sql2oEndangeredDao implements EndangeredDao{

    private final Sql2o sql2o;

    public Sql2oEndangeredDao(Sql2o sql2o) { this.sql2o = sql2o; }

    @Override
    public List<Endangered> getAll() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM players") //raw sql
                    .throwOnMappingFailure(false).executeAndFetch(Endangered.class); //fetch a list
        }
    }

    @Override
    public Endangered findById(int id) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM players WHERE id = :id")
                    .addParameter("id", id) //key/value pair, key must match above
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(Endangered.class); //fetch an individual item
        }

    }
}
