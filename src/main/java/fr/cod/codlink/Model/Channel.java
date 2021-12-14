/*
 * Project: $Project
 * Copyright (c) 2021 CoDrake.
 * You cannot change/copy the code without permission
 */

package fr.cod.codlink.Model;

public class Channel {
    private String description;
    private int id;
    private String name;
    private int parent;
    private boolean temporary;

    public Channel() {
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getParent() {
        return parent;
    }

    public boolean isTemporary() {
        return temporary;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "description='" + description + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", parent=" + parent +
                ", temporary=" + temporary +
                '}';
    }

    //TODO
    public Channel createChannel(Channel parent, String name){

        return null;
    }
}
