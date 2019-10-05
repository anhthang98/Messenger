package com.example.messenger;

import java.io.Serializable;

public class Contact implements Serializable {
    String name;
    String mess;
    boolean icon;

    public Contact(String name, boolean icon) {
        this.name = name;
        this.icon = icon;
    }

    public Contact(String name, String mess, boolean icon) {
        this.name = name;
        this.mess = mess;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public boolean isIcon() {
        return icon;
    }

    public void setIcon(boolean icon) {
        this.icon = icon;
    }
}
