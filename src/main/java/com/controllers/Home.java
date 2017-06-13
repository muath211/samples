package com.controllers;

import com.model.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class Home {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/index")
    public String home() {
        return "index";
    }

    @RequestMapping("/offers")
    @ResponseBody
    public String offers() {
        Example quote = restTemplate.getForObject("https://offersvc.expedia.com/offers/v2/getOffers" +
                "?scenario=deal-finder&page=foo&uid=foo&productType=Hotel", Example.class);
        return quote.getOffers().getHotel().get(0).getDestination().getCity();
    }

}
