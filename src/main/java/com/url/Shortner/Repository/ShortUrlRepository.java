package com.url.Shortner.Repository;

import com.url.Shortner.Entities.ShortUrl;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShortUrlRepository extends JpaRepository<ShortUrl, Long> {
    Optional<ShortUrl> findByShortkey(String shortkey);
}
