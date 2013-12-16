/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.mensajeria.compiler;

/**
 *
 * @author ce
 */
public class Sim {

    private final String name;
    private final String type;
    private final int pos;

    public Sim(String name, String type, int pos) {
        this.name = name;
        this.type = type;
        this.pos = pos;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getPos() {
        return pos;
    }

}
