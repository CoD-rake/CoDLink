/*
 * Project: $Project
 * Copyright (c) 2021 CoDrake.
 * You cannot change/copy the code without permission
 */

package fr.cod.codlink.Model;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

public class Server {
    private String address;
    private String host;
    private String humanize_uptime;
    private int id;
    private int log_length;
    private int maxusers;
    private String name;
    private String password;
    private int port;
    private boolean running;
    private int user_count;
    private String welcometext;

    public Server() {
    }

    public String getAddress() {
        return address;
    }

    public String getHost() {
        return host;
    }

    public String getHumanize_uptime() {
        return humanize_uptime;
    }

    public int getId() {
        return id;
    }

    public int getLog_length() {
        return log_length;
    }

    public int getMaxusers() {
        return maxusers;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getPort() {
        return port;
    }

    public boolean isRunning() {
        return running;
    }

    public int getUser_count() {
        return user_count;
    }

    public String getWelcometext() {
        return welcometext;
    }

    @Override
    public String toString() {
        return "Server{" +
                "address='" + address + '\'' +
                ", host='" + host + '\'' +
                ", humanize_uptime='" + humanize_uptime + '\'' +
                ", id=" + id +
                ", log_length=" + log_length +
                ", maxusers=" + maxusers +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", port=" + port +
                ", running=" + running +
                ", user_count=" + user_count +
                ", welcometext='" + welcometext + '\'' +
                '}';
    }

    /**
     * Server[] servers = Server.getServer();
     *         if (servers != null) {
     *             for (Server e : servers) {
     *                 System.out.println(e.toString());
     *             }
     *         }
     */
    public static Server[] getServer(){
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.getForObject("http://192.168.1.100:8080/servers/",Server[].class);
    }


    /**
     * Server server = Server.getInfo(2);
     * System.out.println(server);
     */
    public static Server getInfo(int id){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("http://192.168.1.100:8080/servers/{id}/",Server.class,id);

    }

    //TODO
    public static Server createServer(){
        return null;
    }

    //TODO
    public void start(){

    }

    //TODO
    public void stop(){

    }

    //TODO
    public void delete(){

    }




}
