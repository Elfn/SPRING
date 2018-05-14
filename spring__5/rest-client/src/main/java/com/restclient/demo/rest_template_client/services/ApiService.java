package com.restclient.demo.rest_template_client.services;

import com.restclient.demo.domain.User;

import java.util.List;

/**
 * Created by Elimane on May, 2018, at 03:37
 */
public interface ApiService {

    public List<User> getUsers(Integer limit);
}
