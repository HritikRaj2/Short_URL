package com.url.Shortner.Controller;


import com.url.Shortner.Entities.ShortUrl;
import com.url.Shortner.Entities.User;
import com.url.Shortner.Repository.ShortUrlRepository;
import com.url.Shortner.Repository.UserRepository;
import com.url.Shortner.Services.ShortUrlService;
import com.url.Shortner.Services.Validity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ShortUrlController {

    @Autowired
    private Validity validity;

    @Autowired
    private ShortUrlRepository shortUrlRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShortUrlService shortUrlService;

    @PostMapping("/sendurl")
    public ResponseEntity<?> shorturl(@RequestParam String url){


        if(!validity.isValid(url)){
            return ResponseEntity.badRequest()
                    .body("URL is not Valid");
        }
        ShortUrl shortUrl = shortUrlService.sortKey(url);
        return ResponseEntity
                .ok()
                .body("URL shortened successfully: " + shortUrl.getShortkey());


    }


    @PostMapping("/saveuser")
    public ResponseEntity<?> saveUser(@RequestBody User response){
        User user=new User();
        user.setEmail(response.getEmail());
        user.setCreatedAt(LocalDateTime.now());
        user.setName(response.getName());
        user.setPassword(response.getPassword());
        userRepository.save(user);
        return ResponseEntity.ok().body("Registered Successfully");
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password ){
        Optional<User> user=userRepository.findByEmail(email);

        if(user.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Please Enter Valid Email");
        }
        if(!user.get().getPassword().equals(password)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Email and Password");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Login successfully");

    }
}
