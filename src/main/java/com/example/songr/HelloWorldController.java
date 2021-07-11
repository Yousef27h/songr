package com.example.songr;

import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Locale;

@Controller
public class HelloWorldController {
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
        String[] titles = new String[3];
        String[] artists = new String[3];
        int[] songCounts = new int[3];
        int[] lengths = new int[3];
        String[] imageUrls = new String[3];

        albums[0] = album1;
        albums[1] = album2;
        albums[2] = album3;

        for (int i=0; i<3;i++){
            titles[i] = albums[i].getTitle();
            artists[i] = albums[i].getArtist();
            songCounts[i] = albums[i].getSongCount();
            lengths[i] = albums[i].getLength();
            imageUrls[i] = albums[i].getImageUrl();
        }
        model.addAttribute("albums", albums);
        model.addAttribute("titles", titles);
        model.addAttribute("artists", artists);
        model.addAttribute("songCounts",songCounts );
        model.addAttribute("lengths", lengths);
        model.addAttribute("imageUrls", imageUrls);
        return "album";
    }

}
