package com.example.takehome.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {
	
	/*
	@Bean
	public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
	    return (builder) -> builder
	      .withCacheConfiguration("rateLimitCache",
	        RedisCacheConfiguration.defaultCacheConfig());
	}
	
	@Bean
	public RedisCacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory,
	                                      ResourceLoader resourceLoader) {
	    RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager
	            .builder(redisConnectionFactory)
	            .cacheDefaults(RedisCacheConfiguration.defaultCacheConfig())
	            .withCacheConfiguration(TakeHomeConstants.RATE_LIMITER_CACHE_NAME, RedisCacheConfiguration.defaultCacheConfig());
	    	    return builder.build();
	}
	*/
}
