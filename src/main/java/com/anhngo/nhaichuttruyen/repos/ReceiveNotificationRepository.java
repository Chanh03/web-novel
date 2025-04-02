package com.anhngo.nhaichuttruyen.repos;

import com.anhngo.nhaichuttruyen.entity.Novel;
import com.anhngo.nhaichuttruyen.entity.ReceiveNotification;
import com.anhngo.nhaichuttruyen.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReceiveNotificationRepository extends JpaRepository<ReceiveNotification, Integer> {

    ReceiveNotification findFirstByUser(User user);

    ReceiveNotification findFirstByNovel(Novel novel);

}
