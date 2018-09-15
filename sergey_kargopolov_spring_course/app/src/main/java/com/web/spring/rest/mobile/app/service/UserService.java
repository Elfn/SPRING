package com.web.spring.rest.mobile.app.service;

import com.web.spring.rest.mobile.app.shared.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by Elimane on Sep, 2018, at 03:58
 */

//UserDetails is The interface which requires only one read-only method(loadUserByUsername()),
// which simplifies support for new data-access strategies.
public interface UserService extends UserDetailsService{

    UserDTO createUser(UserDTO userDTO);
}
