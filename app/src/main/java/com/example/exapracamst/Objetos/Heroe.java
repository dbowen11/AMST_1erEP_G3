package com.example.exapracamst.Objetos;

import java.io.Serializable;

public class Heroe implements Serializable {
    private String id;
    private String name;
    private Habilidades habidad;

    public Heroe(String id, String name, Habilidades habidad) {
        this.id = id;
        this.name = name;
        this.habidad = habidad;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Habilidades getHabidad() {
        return habidad;
    }

    public void setHabidad(Habilidades habidad) {
        this.habidad = habidad;
    }

    @Override
    public String toString() {
        return "Heroe{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", habidad=" + habidad +
                '}';
    }
}
