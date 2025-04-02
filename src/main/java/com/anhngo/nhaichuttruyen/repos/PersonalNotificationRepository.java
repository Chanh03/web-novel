package com.anhngo.nhaichuttruyen.repos;

import com.anhngo.nhaichuttruyen.entity.PersonalNotification;
import com.anhngo.nhaichuttruyen.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PersonalNotificationRepository extends JpaRepository<PersonalNotification, Integer> {

    PersonalNotification findFirstByUser(User user);

}
