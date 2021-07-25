package com.example.songr;

import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class SongsController {

    @Autowired
    private SongRepository  songRepository;

    @Autowired
    private albumRepository albumRepository;


    /**
     *
     * @param id
     * @return "song" object contain song found by id.
     */
    @GetMapping("/songs/{id}")
    public ResponseEntity<Song> getOneSong(@PathVariable Long id){
        Song song = songRepository.findById(id).orElseThrow();
        return new ResponseEntity<>(song, HttpStatus.OK);
    }

    /**
     *
     * @param model
     * @return "songs" view with model "songs" contains all songs we have
     */
    @GetMapping("/songs")
    public String getSongs(Model model){
        Iterable<Song> songs = songRepository.findAll();
        model.addAttribute("songs", songs);
        return "songs";
    }

    /**
     *
     * @param albumId
     * @param model
     * @return redirects view to  "/songsRelated/{albumId}"
     */
    @PostMapping("/albumSongs")
    public RedirectView postSongsFromAlbum(Long albumId, Model model){
        return new RedirectView("/songsRelated/"+albumId);
    }

    /**
     *
     * @param title
     * @param length
     * @param trackNumber
     * @return
     */
    @PostMapping("/songs")
    ResponseEntity<Song> addSong(String title, int  length, int trackNumber){
        Song song = new Song(title, length, trackNumber);
        songRepository.save(song);
        return  new ResponseEntity<>(song, HttpStatus.CREATED);
    }

}
