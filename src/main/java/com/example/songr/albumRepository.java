package com.example.songr;

import org.springframework.data.jpa.repository.JpaRepository;

public interface albumRepository extends JpaRepository<Album, Long> {
}
