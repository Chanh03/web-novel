package com.anhngo.nhaichuttruyen.repos;

import com.anhngo.nhaichuttruyen.entity.Favorite;
import com.anhngo.nhaichuttruyen.entity.Novel;
import com.anhngo.nhaichuttruyen.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {

    Favorite findFirstByUser(User user);

    Favorite findFirstByNovel(Novel novel);

}
