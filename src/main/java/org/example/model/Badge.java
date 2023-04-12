package org.example.model;

/**
 * a simple Badge class, each badge has an id, a name, and can be one of those 4 types (BRONZE, SILVER,GOLD, OR HOF)
 */
public class Badge {
    private String id;
    private String name;
    private Type type;

    public enum Type {
        BRONZE,
        SILVER,
        GOLD,
        HOF;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Badge(String id, String name, Type type) {
        this.name = name;
        this.type = type;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
