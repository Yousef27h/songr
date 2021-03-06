package com.example.songr;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface albumRepository extends JpaRepository<Album, Long> {
    Album findAlbumByTitle(String title);
}
