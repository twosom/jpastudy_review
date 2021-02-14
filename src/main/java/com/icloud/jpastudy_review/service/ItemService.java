package com.icloud.jpastudy_review.service;

import com.icloud.jpastudy_review.domain.item.Item;
import com.icloud.jpastudy_review.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository itemRepository;


    /* 상품 등록 */
    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    /* 모든 상품 조회 */
    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
