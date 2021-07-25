package com.example.songr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Locale;

@Controller
public class AlbumController {

    @Autowired
    private albumRepository albumRepository;

    @Autowired
    private SongRepository songRepository;

    @GetMapping("/hello")
    String greet(){
        return "greeting";
    }

    @GetMapping("/capitalize/{input}")
    String capitalize(@PathVariable String input, Model model){
        input = input.toUpperCase(Locale.ROOT);
        model.addAttribute("input",input);
        return "capitalize";
    }

    @GetMapping("/albums")
    String record(Model model){
        Album album1 = new Album("album1","Yousef",3,130,"url1");
        Album album2 = new Album("album2","Fakhri",4,140,"url2");
        Album album3 = new Album("album3","Salem",5,150,"url3");
        Album[] albums = new Album[3];
        albums[0] = album1;
        albums[1] = album2;
        albums[2] = album3;
        Iterable<Album> albumsRepo = albumRepository.findAll();
        model.addAttribute("albums2", albumsRepo);
        model.addAttribute("albums1", albums);
        return "album";
    }

    // Home page, displays all albums
    @GetMapping("/")
    String splash(Model model){
        List<Album> albums = albumRepository.findAll();
        model.addAttribute("albums", albums);
        return "splash";
    }

    // To add new albums

    /**
     *
     * @param model
     * @param title
     * @param artist
     * @param imageUrl
     * @param length
     * @param songCount
     * @return redirect view to homepage after saving new object to album repository
     */
    @PostMapping("/albums")
    RedirectView addAlbum( Model model, String title,  String artist,  String imageUrl,  int length , int songCount) {
        Album album = new Album(title, artist, songCount, length, imageUrl);
        albumRepository.save(album);
        return new RedirectView("/");
    }

    @GetMapping("/getSingleAlbum")
    public String oneAlbum(){
        return "singleAlbumForm";
    }

    @PostMapping("/getSingleAlbum")
    public RedirectView oneAlbum(String title){
        return new RedirectView("singleAlbum/"+title);
    }

    @GetMapping("/singleAlbum/{albumTitle}")
    public String viewOneAlbum(Model model, @PathVariable String albumTitle){
        Album oneAlbum = albumRepository.findAlbumByTitle(albumTitle);
        model.addAttribute("oneAlbum", oneAlbum);
        return "singleAlbum";
    }

    // Add songs to a specific album
    /**
     *
     * @param albumId
     * @param songId
     * @return returns "album" object contains album after adding specific song to it
     */
    @PostMapping("/albums/{albumId}")
    ResponseEntity<Album> addSing(@PathVariable Long albumId, Long songId){
        Album album = albumRepository.findById(albumId).orElseThrow();
        Song song = songRepository.findById(songId).orElseThrow();
        song.setAlbum(album);
        songRepository.save(song);
        return new ResponseEntity<>(album , HttpStatus.OK );
    }

    @GetMapping("/songToAlbum")
    public String album1(Model model){
        Iterable<Album> albums = albumRepository.findAll();
        model.addAttribute("albums",albums);
        return "songsToAlbumForm";
    }

    @PostMapping("/songToAlbum")
    RedirectView viewSingleAlbum(String albumTitle, String songTitle, int songLength, int trackNumber){
        Album album = albumRepository.findAlbumByTitle(albumTitle);
        Song song = new Song(songTitle, songLength, trackNumber);
        song.setAlbum(album);
        songRepository.save(song);
        return new RedirectView("/songToAlbum");
    }
//
//    @GetMapping("/songToAlbum/{albumTitle}")
//    public String album(Model model,@PathVariable String albumTitle){
////        Album albums = (Album) albumRepository.findAll();
////        model.addAttribute("albums",albums);
//        Album album = albumRepository.findAlbumByTitle(albumTitle);
//        model.addAttribute("album", album);
//        return "songsToAlbum";
//    }
//    @PostMapping("/songToAlbum/addSong")
//    ResponseEntity<Album>  addSongToAlbum(String songTitle){
//        Song song = songRepository.findSongByTitle(songTitle);
//
//    }



//    @PostMapping("/addSongToAlbum")
//    ResponseEntity<Album> addSongToAlbum(String albumTitle, String songTitle){
//        Album album = albumRepository.findAlbumByTitle(albumTitle);
//        Song song = songRepository.findSongByTitle(songTitle);
//        song.setAlbum(album);
//        songRepository.save(song);
//        return new ResponseEntity<>(album , HttpStatus.OK );
//    }

}
