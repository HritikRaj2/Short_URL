package com.url.Shortner.Services;

import com.url.Shortner.Entities.ShortUrl;
import com.url.Shortner.Repository.ShortUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ShortUrlService {
    @Autowired
    private ShortUrlRepository shortUrlRepository;

    private static final String CHARACTERS =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    private final SecureRandom random=new SecureRandom();

    private String generateRandomKey(int length){
        StringBuilder key=new StringBuilder();

        for(int i=0; i< length; i++){
            int index= random.nextInt(CHARACTERS.length());
            key.append(CHARACTERS.charAt(index));
        }

        return key.toString();
    }

    private String generateUniqueShortKey(){

        String shortKeyy;

        do{
            shortKeyy=generateRandomKey(6);
        } while (shortUrlRepository.findByShortkey(shortKeyy).isPresent());

        return shortKeyy;
    }

    public ShortUrl sortKey(String originalUrl){
        String sortKey= generateUniqueShortKey();

        ShortUrl shortUrl=new ShortUrl();
        shortUrl.setOriginalUrl(originalUrl);
        shortUrl.setShortkey(sortKey);
        shortUrl.setCreatedAt(LocalDateTime.now());
        shortUrl.setClickCount(0L);
        shortUrl.setIsPrivate(false);

        return shortUrlRepository.save(shortUrl);
    }

    public Optional<ShortUrl> getByShortKey(String shortKey) {

        return shortUrlRepository.findByShortkey(shortKey);
    }

}
