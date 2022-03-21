package pl.coderslab.dao;

public class Plan {

    private int id;
    private String name;
    private String description;
    private String date;


    public Plan() {
    }

    public Plan(int id, String name, String description, String date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;

    }

    public int getId() {
        return id;
    }

    public Plan setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Plan setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Plan setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getDate() {
        return date;
    }

    public Plan setDate(String date) {
        this.date = date;
        return this;
    }

    @Override
    public String toString() {
        return "Plan{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                '}';
    }
}

