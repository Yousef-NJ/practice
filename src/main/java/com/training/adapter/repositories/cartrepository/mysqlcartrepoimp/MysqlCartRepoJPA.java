package com.training.adapter.repositories.cartrepository.mysqlcartrepoimp;

import com.training.adapter.repositories.cartrepository.mysqlcartrepoimp.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MysqlCartRepoJPA extends JpaRepository<CartEntity, String> {
}
