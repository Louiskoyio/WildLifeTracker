package dao;

import models.Endangered;

import java.util.List;

public interface EndangeredDao {


    List<Endangered> getAll();

    Endangered findById(int id);
}
