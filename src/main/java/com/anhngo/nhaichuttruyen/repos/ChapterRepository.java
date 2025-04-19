package com.anhngo.nhaichuttruyen.repos;

import com.anhngo.nhaichuttruyen.entity.Chapter;
import com.anhngo.nhaichuttruyen.entity.Novel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ChapterRepository extends JpaRepository<Chapter, Integer> {

    Chapter findFirstByNovel(Novel novel);

}
