package com.softserveinc.if052_webapp.controller;

import com.softserveinc.if052_webapp.domain.UserRole;
import com.softserveinc.if052_webapp.service.RestServiceTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestOperations;

import java.net.URI;

/**
 * Created by Hata on 08.03.2015.
 */

@Controller
public class OauthTestController {

    @Autowired
    RestServiceTest restServiceTest;

    @RequestMapping("/profile")
    public String getProfilePage(Model model){
        UserRole userRole = restServiceTest.getUserRole();
        //UserRole userRole = restServiceTest.getUserRole1();
        model.addAttribute("role", userRole);
        return "profile";
    }

    @RequestMapping("/profile/redirect")
    public String getProfilePageRedirect(Model model){
        UserRole userRole = restServiceTest.getUserRole();
        //UserRole userRole = restServiceTest.getUserRole1();
        model.addAttribute("role", userRole);
        return "profile";
    }


    @RequestMapping("/login")
    public String getLogInPage(){
        return "login";
    }
}
