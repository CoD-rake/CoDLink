/*
 * Project: $Project
 * Copyright (c) 2021 CoDrake.
 * You cannot change/copy the code without permission
 */

package fr.cod.codlink.Model;

import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;

public class User {
    private static HashMap<String, String> passwords = new HashMap<>();
    private int[] address;
    private int bytespersec;
    private int channel;
    private String comment;
    private String context;
    private boolean deaf;
    private String identity;
    private int idlesecs;
    private boolean mute;
    private String name;
    private long onlinesecs;
    private String os;
    private String osversion;
    private boolean prioritySpeaker;
    private boolean recording;
    private String release;
    private boolean selfDeaf;
    private boolean selfMute;
    private int session;
    private boolean suppress;
    private double tcpPing;
    private boolean tcponly;
    private boolean udpPing;
    private int userid;
    private int version;

    public User() {
    }

    public int[] getAddress() {
        return address;
    }

    public int getBytespersec() {
        return bytespersec;
    }

    public int getChannel() {
        return channel;
    }

    public String getComment() {
        return comment;
    }

    public String getContext() {
        return context;
    }

    public boolean isDeaf() {
        return deaf;
    }

    public String getIdentity() {
        return identity;
    }

    public int getIdlesecs() {
        return idlesecs;
    }

    public boolean isMute() {
        return mute;
    }

    public String getName() {
        return name;
    }

    public long getOnlinesecs() {
        return onlinesecs;
    }

    public String getOs() {
        return os;
    }

    public String getOsversion() {
        return osversion;
    }

    public boolean isPrioritySpeaker() {
        return prioritySpeaker;
    }

    public boolean isRecording() {
        return recording;
    }

    public String getRelease() {
        return release;
    }

    public boolean isSelfDeaf() {
        return selfDeaf;
    }

    public boolean isSelfMute() {
        return selfMute;
    }

    public int getSession() {
        return session;
    }

    public boolean isSuppress() {
        return suppress;
    }

    public double getTcpPing() {
        return tcpPing;
    }

    public boolean isTcponly() {
        return tcponly;
    }

    public boolean isUdpPing() {
        return udpPing;
    }

    public int getUserid() {
        return userid;
    }

    public int getVersion() {
        return version;
    }

    @Override
    public String toString() {
        return "User{" +
                "address=" + Arrays.toString(address) +
                ", bytespersec=" + bytespersec +
                ", channel=" + channel +
                ", comment='" + comment + '\'' +
                ", context='" + context + '\'' +
                ", deaf=" + deaf +
                ", identity='" + identity + '\'' +
                ", idlesecs=" + idlesecs +
                ", mute=" + mute +
                ", name='" + name + '\'' +
                ", onlinesecs=" + onlinesecs +
                ", os='" + os + '\'' +
                ", osversion='" + osversion + '\'' +
                ", prioritySpeaker=" + prioritySpeaker +
                ", recording=" + recording +
                ", release='" + release + '\'' +
                ", selfDeaf=" + selfDeaf +
                ", selfMute=" + selfMute +
                ", session=" + session +
                ", suppress=" + suppress +
                ", tcpPing=" + tcpPing +
                ", tcponly=" + tcponly +
                ", udpPing=" + udpPing +
                ", userid=" + userid +
                ", version=" + version +
                '}';
    }

    //TODO
    public static User[] getUsers(int idserver){
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.getForObject("http://192.168.1.100:8080/servers/{idserver}/user",User[].class,idserver);
    }


    public static String getPassword(String name){
        if(!passwords.containsKey(name)) return null;
        return passwords.get(name);
    }

    //TODO
    public static User createUser(String name, String password){
        return null;
    }

    //TODO
    public void  deleteUser(User user){

    }

    //TODO
    public void kickUser(Server server, User user){

    }

    //TODO
    public void muteUser(Server server, User user){

    }

    //TODO
    public void unmuteUser(Server server, User user){

    }

    //TODO
    public boolean isLinked(){
        return false;
    }

}
