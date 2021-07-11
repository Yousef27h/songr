package com.example.songr;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SongrApplicationTests {
	// create new object instance of Album class
	Album album = new Album("myAlbum","Yousef",25,170,"Any URL");
	// test getters methods of Album class
	@Test
	void gettersTest() {
		Assertions.assertEquals("myAlbum",album.getTitle());
		Assertions.assertEquals("Yousef",album.getArtist());
		Assertions.assertEquals(25 , album.getSongCount());
		Assertions.assertEquals(170 , album.getLength());
		Assertions.assertEquals("Any URL",album.getImageUrl());
	}
	//test for setters methods of Album class
	@Test
	void settersTest(){
		album.setArtist("Salem");
		Assertions.assertEquals("Salem",album.getArtist());
		album.setLength(200);
		Assertions.assertEquals(200, album.getLength());
	}

}
