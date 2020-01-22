package com.training.adapter.repositories.itemrepository.mysqlitemrepoimp;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.training.adapter.repositories.itemrepository.ItemRepository;
import com.training.adapter.repositories.itemrepository.mysqlitemrepoimp.entity.ItemEntity;
import com.training.model.Item;

class MysqlItemRepo implements ItemRepository{
	private final MysqlRepoJPA repo;
	private final ModelMapper mapper;

	public MysqlItemRepo(MysqlRepoJPA repo,ModelMapper mapper) {
		this.repo = repo;
		this.mapper = mapper;
	}

	@Override
	public Item addItem(Item item) {
		repo.save(ItemToItemEntity(item));
		return item;
	}

	@Override
	public Page<Item> loadItems(int pageIndex, int pageSize) {
		PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
		return repo.findAll(pageRequest).map(this::ItemEntityToItem);
	}

	@Override
	public Optional<Item> loadItem(String itemNo) {
		if(repo.existsById(itemNo)) {
			ItemEntity entity=repo.findById(itemNo).get();
			return Optional.of(ItemEntityToItem(entity));
		}
		return Optional.empty();
	}

	private Item ItemEntityToItem(ItemEntity itemEntity) {
		return mapper.map(itemEntity, Item.class);
	}

	private ItemEntity ItemToItemEntity(Item item) {
		return mapper.map(item, ItemEntity.class);
	}
}
