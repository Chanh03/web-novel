package com.anhngo.nhaichuttruyen.repos;

import com.anhngo.nhaichuttruyen.entity.Bookmark;
import com.anhngo.nhaichuttruyen.entity.Chapter;
import com.anhngo.nhaichuttruyen.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookmarkRepository extends JpaRepository<Bookmark, Integer> {

    Bookmark findFirstByUser(User user);

    Bookmark findFirstByChapter(Chapter chapter);

}
