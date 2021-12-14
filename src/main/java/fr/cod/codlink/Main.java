package fr.cod.codlink;

import fr.cod.codlink.Model.Server;
import fr.cod.codlink.Model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Main {

    //https://o7planning.org/11647/spring-boot-restful-client-with-resttemplate
    //https://spring.io/guides/gs/rest-service/


    //mumble://[username[:password]@]<address>[:port]/[channelpath]?version=<serverversion>[&title=<servername>][&url=<serverurl>]
    //mumble://test@192.168.1.100/c1
    //mumble://username:password@ip:port/channel

    private static final Logger log = LoggerFactory.getLogger(Main.class);


    public static void main(String[] args) {
     //SpringApplication.run(DemoApplication.class, args);

//        Server server = Server.getInfo(2);
        /*
          User[] users = User.getUser(2);
                  if (users != null) {
                      for (User e : users) {
                          System.out.println(e.toString());
                      }
                  }
*/
        System.out.println(User.getUser(2));


    }

}