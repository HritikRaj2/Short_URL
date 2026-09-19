package com.url.Shortner.Services;


import org.springframework.stereotype.Service;

import java.net.URI;

@Service
public class Validity {

    public boolean isValid(String url){

        if(url.isEmpty() || url.isBlank()|| url.equals(null)){
            return false;
        }
        try{
            URI uri=new URI(url);
            return (uri.getScheme().equalsIgnoreCase("http")
                    || uri.getScheme().equalsIgnoreCase("https")
                      && uri.getHost()!= null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
