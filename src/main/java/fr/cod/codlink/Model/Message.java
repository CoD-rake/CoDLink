/*
 * Project: $Project
 * Copyright (c) 2021 CoDrake.
 * You cannot change/copy the code without permission
 */

package fr.cod.codlink.Model;

public class Message {
    private String message;
    private String muted;
    private String last_active;
    private String user_id;
    private String username;


    private Message() {
    }

    public String getMessage() {
        return message;
    }


    public String getMuted() {
        return muted;
    }

    public String getLast_active() {
        return last_active;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "Message{" +
                "message='" + message + '\'' +
                ", muted='" + muted + '\'' +
                ", last_active='" + last_active + '\'' +
                ", user_id='" + user_id + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
