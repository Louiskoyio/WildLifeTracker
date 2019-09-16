package models;

public class Sighting {
    public String animal_name;
    public String location;
    public String ranger_name;

    public Sighting(String animal_name, String location, String ranger_name) {
        this.animal_name = animal_name;
        this.location = location;
        this.ranger_name = ranger_name;
    }

    public void setId(String animal_id) {
        this.animal_name = animal_id;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setRanger_id(String ranger_id) {
        this.ranger_name = ranger_name;
    }
}
