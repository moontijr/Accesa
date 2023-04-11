package org.example.model;

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
        this.type=type;
        this.id=id;
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
