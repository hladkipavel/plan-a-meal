package pl.coderslab.model;

import com.mysql.fabric.xmlrpc.base.Data;
import pl.coderslab.dao.PlanDao;

public class Plan {

    private int id;
    private String name;
    private String description;
    private String created;

    public Plan(String name, String description, String created) {
        this.name = name;
        this.description = description;
        this.created = created;
    }

    public Plan() {
    }

    @Override
    public String toString() {
        return "Plan [id=" + id + ", name=" + name + ", description=" + description + ", created=" + created + "]";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}
