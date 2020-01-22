package com.training.adapter.repositories.itemrepository.mysqlitemrepoimp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.training.adapter.repositories.itemrepository.mysqlitemrepoimp.entity.ItemEntity;

@Repository
interface MysqlRepoJPA extends JpaRepository<ItemEntity, String>{

}
