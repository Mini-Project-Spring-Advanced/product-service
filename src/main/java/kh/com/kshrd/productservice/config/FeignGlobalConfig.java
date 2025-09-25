package kh.com.kshrd.productservice.config;

import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignGlobalConfig {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new FeignClientErrorDecoder();
    }

}
