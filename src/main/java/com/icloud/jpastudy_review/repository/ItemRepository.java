package com.icloud.jpastudy_review.repository;

import com.icloud.jpastudy_review.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    /*
        상품 등록, 이 메서드 하나로 저장과 수정(병합)을 다 처리.
        코드를 보고 식별자 값이 없으면 새로운 엔티티로 판단해서 영속화,
        이미 식별자 값이 있으면 영속화 된 엔티티로 판단해서 merge()로 수정(병합)한다.
     */
    public void save(Item item) {
        if (item.getId() == null) {
            em.persist(item);
        } else {
            em.merge(item);
        }
    }

    /*
        상품 한개 찾기
     */
    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    /*
        상품 여러개 찾기
     */
    public List<Item> findAll() {
        return em.createQuery(
                "select i " +
                        "from Item i ", Item.class)
                .getResultList();
    }
}
