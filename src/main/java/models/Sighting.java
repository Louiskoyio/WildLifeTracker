package models;

public class Sighting {
    public int animal_id;
    public String location;
    public String ranger_name;

    public Sighting(int animal_id, String location, String ranger_name) {
        this.animal_id = animal_id;
        this.location = location;
        this.ranger_name = ranger_name;
    }

    public void setId(int animal_id) {
        this.animal_id = animal_id;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setRanger_id(String ranger_id) {
        this.ranger_name = ranger_name;
    }
}
