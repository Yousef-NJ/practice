package com.training.adapter.repositories.cartrepository.mysqlcartrepoimp;


import com.training.adapter.repositories.cartrepository.CartRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "com.training.mysql",name = "enabled",matchIfMissing = false)
public class MysqlCartRepoConfig {
    @Bean
    public CartRepository mysqlCartRepo(MysqlCartRepoJPA repo, ModelMapper mapper) {
        return new MysqlCartRepoImp(repo, mapper);
    }

}
