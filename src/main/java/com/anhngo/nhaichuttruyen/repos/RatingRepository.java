package com.anhngo.nhaichuttruyen.repos;

import com.anhngo.nhaichuttruyen.entity.Chapter;
import com.anhngo.nhaichuttruyen.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RatingRepository extends JpaRepository<Rating, Integer> {

    Rating findFirstByChapter(Chapter chapter);

}
