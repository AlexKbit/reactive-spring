package com.alexkbit.intro.reactivespring.rest;

import java.util.UUID;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoResource {

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String getVersion() {
        return UUID.randomUUID().toString();
    }

}
