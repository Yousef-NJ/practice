package com.training.adapter.repositories.itemrepository.mysqlitemrepoimp;

import com.training.adapter.repositories.itemrepository.ItemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "com.training.mysql",name = "enabled",matchIfMissing = true)
public class MysqlItemRepoConfig {
	
	@Bean
	public ItemRepository mysqlItemRepo(MysqlRepoJPA repo, ModelMapper mapper) {
		return new MysqlItemRepo(repo, mapper);
	}

}
