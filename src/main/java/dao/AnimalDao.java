package dao;
import models.Animal;
import java.util.List;

public interface AnimalDao {


    List<Animal> getAll();

    Animal findById(int id);

}
