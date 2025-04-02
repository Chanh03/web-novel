package com.anhngo.nhaichuttruyen.repos;

import com.anhngo.nhaichuttruyen.entity.Novel;
import com.anhngo.nhaichuttruyen.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NovelRepository extends JpaRepository<Novel, Integer> {

    Novel findFirstByAuthor(User user);

}
