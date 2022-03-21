package pl.coderslab.dao;

public class Plan {

    private int id;
    private String name;
    private String description;
    private String created;


    public Plan() {
    }

    public Plan(int id, String name, String description, String created) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.created = created;

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

    public String getCreated() {
        return created;
    }

    public Plan setCreated(String created) {
        this.created = created;
        return this;
    }

    @Override
    public String toString() {
        return "Plan{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", created=" + created +
                '}';
    }
}

