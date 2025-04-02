package com.anhngo.nhaichuttruyen.repos;

import com.anhngo.nhaichuttruyen.entity.Genre;
import com.anhngo.nhaichuttruyen.entity.GenreNovel;
import com.anhngo.nhaichuttruyen.entity.Novel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GenreNovelRepository extends JpaRepository<GenreNovel, Integer> {

    GenreNovel findFirstByGenre(Genre genre);

    GenreNovel findFirstByNovel(Novel novel);

}
