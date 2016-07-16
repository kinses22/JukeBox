package com.kinses22.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Stephen on 16/07/2016.
 */
public class JukeBoxMenu {

    private SongLibrary songLibrary;
    private BufferedReader reader;
    private Map<String, String> menu;

    public JukeBoxMenu(SongLibrary songLibrary) {
        this.songLibrary = songLibrary;
        reader = new BufferedReader(new InputStreamReader(System.in));
        menu = new HashMap<>();
        menu.put("Add", " - if you would like to add a song");
        menu.put("Quit", " - if you would like to exit");
    }

    private String promptAction() throws IOException {
        System.out.println();
        System.out.printf("There are %d songs available, your options are: %n",
                songLibrary.getSongCount());
        for (Map.Entry<String, String> options : menu.entrySet()) {
            System.out.printf("%s - %s %n", options.getKey(), options.getValue());
        }
        System.out.println("What would you like to do?");
        String choice = reader.readLine();
        return choice.trim().toLowerCase();
    }

    public void run() {
        String choice = "";
        do {
            try {
                choice = promptAction();
                switch (choice) {
                    case "add":
                        Song song = promptForNewSong();
                        songLibrary.addSong(song);
                        System.out.printf("%s added", song);
                        System.out.println();
                        break;
                    case "quit":
                        System.out.println("Thanks for playing");
                        break;
                    default:
                        System.out.printf("Invalid Selection %s - Try again %n%n%n", choice);
                }
            } catch (IOException e) {
                System.out.println("Invalid Selection");
                e.printStackTrace();
            }
        }
        while (!choice.equals("quit"));
    }


    private Song promptForNewSong() throws IOException {

        System.out.println("Enter artist name: ");
        String artist = reader.readLine();
        System.out.println("Enter title: ");
        String title = reader.readLine();
        System.out.println("Enter video url: ");
        String url = reader.readLine();
        return new Song(artist, title, url);

    }

}