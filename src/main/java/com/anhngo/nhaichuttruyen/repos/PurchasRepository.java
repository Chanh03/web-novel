package com.anhngo.nhaichuttruyen.repos;

import com.anhngo.nhaichuttruyen.entity.Chapter;
import com.anhngo.nhaichuttruyen.entity.Purchase;
import com.anhngo.nhaichuttruyen.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PurchasRepository extends JpaRepository<Purchase, Integer> {

    Purchase findFirstByUser(User user);

    Purchase findFirstByChapter(Chapter chapter);

}
