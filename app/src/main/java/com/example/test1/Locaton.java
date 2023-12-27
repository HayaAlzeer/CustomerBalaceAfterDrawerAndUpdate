package com.example.test1;

import java.util.Objects;

public class Locaton {
    String id;
    String name;


    public Locaton(String id, String name) {
        this.id = id;
        this.name = name;
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

    public boolean equals(Object obj) {
        if(obj instanceof Locaton){
            Locaton c = (Locaton ) obj;
            if(c.getName().equals(name) && c.getId()==id ) return true;
        }

        return false;
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return  id +
                "/ " + name ;
    }
}
