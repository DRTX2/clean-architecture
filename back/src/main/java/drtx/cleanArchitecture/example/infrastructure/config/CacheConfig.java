package drtx.cleanArchitecture.example.infrastructure.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig { // si no se define un proveedor o cache manager, se usa el por defecto de Spring (ConcurrentMapCacheManager)

    // springboot autoconfigura redis
//    @Bean
//    public org.springframework.cache.CacheManager cacheManager() {
//        return new ConcurrentMapCacheManager("users");
//    }
}
