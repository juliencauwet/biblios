package com.openclassrooms.config;

import com.openclassrooms.biblioback.ws.test.AppUserAddRequest;
import com.openclassrooms.biblioback.ws.test.TestPort;
import com.openclassrooms.biblioback.ws.test.TestPortService;
import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;


@Configuration
public class CLR implements CommandLineRunner{

    TestPortService service = new TestPortService();
    TestPort port = service.getTestPortSoap11();

    private static final Logger log = Logger.getLogger(CLR.class);

    public void run(String... args) throws Exception {
        AppUserAddRequest request = new AppUserAddRequest();

        request.setFirstName("Julien");
        request.setName("Cauwet");
        request.setEmail("juliencauwet@yahoo.fr");
        request.setPassword(toHashPassword("12345"));
        request.setIsAdmin(true);

      //  u2.setFirstName();
      //  u1.setName();
      //  u1.setEmail();
      //  u1.setPassword(toHashPassword());
      //  u1.setIsAdmin();
      //
      //  u3.setFirstName();
      //  u1.setName();
      //  u1.setEmail();
      //  u1.setPassword(toHashPassword());
      //  u1.setIsAdmin();
      //
      //  u4.setFirstName();
      //  u1.setName();
      //  u1.setEmail();
      //  u1.setPassword(toHashPassword());
      //  u1.setIsAdmin();



    }

    private String toHashPassword(String password){
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        return hashed;
    }
}
