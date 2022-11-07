package com.example.takehome.config;

import javax.cache.CacheManager;
import javax.cache.Caching;

import org.redisson.config.Config;
import org.redisson.jcache.configuration.RedissonConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.takehome.util.TakeHomeConstants;

import io.github.bucket4j.distributed.proxy.ProxyManager;
import io.github.bucket4j.grid.jcache.JCacheProxyManager;

@Configuration
public class RedisConfiguration {

	@Value("${redis.host}")
	String redisHost;
	
	@Value("${redis.port}")
	String redisPort;
	
	@Value("${redis.password}")
	String redisPassword;
	
	@Bean
    public Config config() {
		String address = "redis://" + redisHost + ":" + redisPort;
        Config config = new Config();
        config.useSingleServer()
        	.setAddress(address)
        	.setPassword(redisPassword);
        return config;
    }
    
    @Bean
    public CacheManager cacheManager(Config config) {
        CacheManager manager = Caching.getCachingProvider().getCacheManager();
        manager.createCache(TakeHomeConstants.RATE_LIMITER_CACHE_NAME, RedissonConfiguration.fromConfig(config));
        return manager;
    }

    @Bean
    public ProxyManager<String> proxyManager(CacheManager cacheManager) {
        return new JCacheProxyManager<>(cacheManager.getCache(TakeHomeConstants.RATE_LIMITER_CACHE_NAME));
    }
   
}
