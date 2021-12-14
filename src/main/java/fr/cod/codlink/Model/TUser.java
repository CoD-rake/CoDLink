package fr.cod.codlink.Model;

import org.springframework.web.client.RestTemplate;

public class TUser {
    private String x;

    public TUser(String x) {
        this.x = x;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public static User[] getUser(int idserver){
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.getForObject("http://192.168.1.100:8080/servers/{idserver}/user",User[].class,idserver);
    }
}
