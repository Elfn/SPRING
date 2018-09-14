package com.web.spring.rest.mobile.app.bootstrap.loader;

import com.web.spring.rest.mobile.app.service.UserService;
import com.web.spring.rest.mobile.app.shared.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by Elimane on Sep, 2018, at 12:45
 */
@Component
public class UserBootstrap implements ApplicationListener<ContextRefreshedEvent>{


    @Autowired
    UserService userService;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

       // loadData();

    }

    public void loadData()
    {
        UserDTO userDTO = new UserDTO();
        userDTO.setEncryptedPassword("test");
        userDTO.setFirstName("Elsior");
        userDTO.setEmail("sss@");
        userDTO.setLastName("Fofana");
        userDTO.setUserId("id_");
         userService.createUser(userDTO);


    }
}
