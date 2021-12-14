/*
 * Project: $Project
 * Copyright (c) 2021 CoDrake.
 * You cannot change/copy the code without permission
 */

package fr.cod.codlink.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.cod.codlink.Main;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class User {
    private static HashMap<String, String> passwords = new HashMap<>();
    @JsonProperty("address")
    private int[] address;
    @JsonProperty("bytespersec")
    private int bytespersec;
    @JsonProperty("channel")
    private int channel;
    @JsonProperty("comment")
    private String comment;
    @JsonProperty("context")
    private String context;
    @JsonProperty("deaf")
    private boolean deaf;
    @JsonProperty("identity")
    private String identity;
    @JsonProperty("idlesecs")
    private int idlesecs;
    @JsonProperty("mute")
    private boolean mute;
    @JsonProperty("name")
    private String name;
    @JsonProperty("onlinesecs")
    private long onlinesecs;
    @JsonProperty("os")
    private String os;
    @JsonProperty("osversion")
    private String osversion;
    @JsonProperty("prioritySpeaker")
    private boolean prioritySpeaker;
    @JsonProperty("recording")
    private boolean recording;
    @JsonProperty("release")
    private String release;
    @JsonProperty("selfDeaf")
    private boolean selfDeaf;
    @JsonProperty("selfMute")
    private boolean selfMute;
    @JsonProperty("session")
    private int session;
    @JsonProperty("suppress")
    private boolean suppress;
    @JsonProperty("tcpPing")
    private double tcpPing;
    @JsonProperty("tcponly")
    private boolean tcponly;
    @JsonProperty("udpPing")
    private long udpPing;
    @JsonProperty("userid")
    private int userid;
    @JsonProperty("version")
    private int version;


    private User() {
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

    public long isUdpPing() {
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

    public static User[] getUsers(int idserver) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();

        String json =  restTemplate.getForObject("http://"+ Main.ip +":"+ Main.port +"/servers/{idserver}/user",String.class,idserver);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(json);

        User[] users = new User[rootNode.size()];
        int i =0;
        for(JsonNode jn : rootNode){
            users[i]=mapper.readValue(jn.toString(),User.class);
            i++;
        }
        return users;
    }


    public static String getPassword(String name){
        if(!passwords.containsKey(name)) return "";
        return passwords.get(name);
    }

    public static Message createUser(Server server, String name, String password){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("username",name);
        map.add("password",password);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        Message message= restTemplate.postForObject("http://"+ Main.ip +":"+ Main.port +"/servers/{idserver}/user",request,Message.class,server.getId());
        return message;
    }

    public void  deleteUser(Server server){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete("http://"+ Main.ip +":"+ Main.port +"/servers/{idserver}/user/{iduser}",server.getId(),this.userid);
    }

    /**
     * Rendre muet un utilisateur, pour celà l'utilisateur doit être enregistré
     * @param server Serveur où se trouve l'utilisateur
     * @return @Message (muted)
     */
    public Message muteUser(Server server){
        RestTemplate restTemplate = new RestTemplate();
        Message result= restTemplate.postForObject("http://"+ Main.ip +":"+ Main.port +"/servers/{idserver}/user/{iduser}/mute",null,Message.class,server.getId(),this.userid);
        return result;
    }

    /**
     * Rendre muet un utilisateur, pour cela l'utilisateur doit être enregistré
     * @param server Serveur où se trouve l'utilisateur
     * @return @Message (muted)
     */
    public Message unmuteUser(Server server){
        RestTemplate restTemplate = new RestTemplate();
        Message result= restTemplate.postForObject("http://"+ Main.ip +":"+ Main.port +"/servers/{idserver}/user/{iduser}/unmute",null,Message.class,server.getId(),this.userid);
        return result;
    }

    /**
     *
     * @return @{@link Map} Key{context / identity}
     */
    public Map<String, String> isLinked(){
        Map<String, String> results = new HashMap<>();
        results.put("context",context);
        results.put("identity",identity);
        return results;
    }

}
