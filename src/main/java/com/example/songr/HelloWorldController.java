package com.example.songr;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Locale;

@Controller
public class HelloWorldController {
    @Autowired
    private albumRepository albumRepository;

    @GetMapping("/")
    String splash(){return "splash";}

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
        model.addAttribute("albums", albums);
        return "album";
    }

    @PostMapping("/albums")
    public RedirectView addAlbum(@RequestParam String title, @RequestParam String artist, @RequestParam String imageUrl, @RequestParam int length , @RequestParam int songCounts) {
        Album album;
        album = new Album(title, artist, songCounts, length, imageUrl);
        albumRepository.save(album);
        return new RedirectView("/albums");
    }
}
