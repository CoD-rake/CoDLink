/*
 * Project: $Project
 * Copyright (c) 2021 CoDrake.
 * You cannot change/copy the code without permission
 */

package fr.cod.codlink.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.cod.codlink.Main;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Channel {
    @JsonProperty("description")
    private String description;
    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("parent")
    private int parent;
    @JsonProperty("temporary")
    private boolean temporary;

    private Channel() {
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

    public static Channel[] getChannels(int idserver) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();

        String json =  restTemplate.getForObject("http://"+ Main.ip +":"+ Main.port +"/servers/{idserver}/channels",String.class,idserver);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(json);

        Channel[] channels = new Channel[rootNode.size()];
        int i =0;
        for(JsonNode jn : rootNode){
            channels[i]=mapper.readValue(jn.toString(),Channel.class);
            i++;
        }
        return channels;
    }

    public static Channel getChannel(int idserver,int idchannel){
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.getForObject("http://"+ Main.ip +":"+ Main.port +"/servers/{idserver}/channels/{idchannel}",Channel.class,idserver,idchannel);
    }

    public static Channel createChannel(Server server, Channel parent, String name){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("name",name);
        map.add("parent",parent.getId()+"");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        Channel channel= restTemplate.postForObject("http://"+ Main.ip +":"+ Main.port +"/servers/{idserver}/channels",request,Channel.class,server.getId());
        return channel;
    }

    public void deleteChannel(Server server){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete("http://"+ Main.ip +":"+ Main.port +"/servers/{idserver}/channels/{idchannel}",server.getId(),this.id);
    }


}
