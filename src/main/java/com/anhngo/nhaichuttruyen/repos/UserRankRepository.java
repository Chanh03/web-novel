package com.anhngo.nhaichuttruyen.repos;

import com.anhngo.nhaichuttruyen.entity.Rank;
import com.anhngo.nhaichuttruyen.entity.User;
import com.anhngo.nhaichuttruyen.entity.UserRank;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRankRepository extends JpaRepository<UserRank, Integer> {

    UserRank findFirstByRank(Rank rank);

    UserRank findFirstByUser(User user);

}
