package dao;
import models.Animal;
import models.Sighting;

import java.util.List;

public interface AnimalDao {


    List<Animal> getAll();

    Animal findById(int id);

    void makeEndangered(int animal_id, String health, String age);

}
