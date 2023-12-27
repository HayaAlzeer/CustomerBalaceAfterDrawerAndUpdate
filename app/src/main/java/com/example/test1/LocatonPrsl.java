package com.example.test1;

import java.util.Objects;

public class LocatonPrsl {
    String id;
    String name;


    public LocatonPrsl(String id, String name) {
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
        if(obj instanceof LocatonPrsl){
            LocatonPrsl c = (LocatonPrsl) obj;
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
        return   id ;
    }
}
