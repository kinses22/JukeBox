package com.kinses22.model;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Created by Stephen on 16/07/2016.
 */
public class JukeBoxMenu {

    private SongLibrary songLibrary;
    private BufferedReader reader;
    private Map<String, String> menu;
    private Queue<Song> songQueue;

    public JukeBoxMenu(SongLibrary songLibrary) {
        this.songLibrary = songLibrary;
        reader = new BufferedReader(new InputStreamReader(System.in));
        songQueue = new ArrayDeque<Song>();
        menu = new HashMap<>();
        menu.put("Add", " - if you would like to add a song");
        menu.put("Quit", " - if you would like to exit");
        menu.put("Choose"," - if you would like to request a song");
        menu.put("Play"," - if you would like to play the nex song in the queue");

    }

    private String promptAction() throws IOException {
        System.out.println();
        System.out.printf("There are %d songs available and %d in the queue, your options are: %n",
                songLibrary.getSongCount(), songQueue.size());
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
                    case  "choose":
                        String artist = promptForArtist();
                        Song artistSong = promptForSong(artist);
                        songQueue.add(artistSong);
                        System.out.printf("You chose: %s %n", artistSong);
                        break;
                    case "play" :
                        playNex();
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

    private String promptForArtist() throws IOException{
        System.out.println("Available artists: ");
        List<String> artists = new ArrayList<>(songLibrary.artistBrowser());
        int index = promptForIndex(artists);
        return artists.get(index);
    }

    private Song promptForSong(String artist) throws IOException{
        List<Song> songs = songLibrary.getSongsForArtist(artist);
        List<String> songTitles = new ArrayList<>();
        for(Song song: songs){
            songTitles.add(song.getTitle());
        }
        System.out.printf("Available songs for %s: %n", artist);
        int index = promptForIndex(songTitles);
        return  songs.get(index);
    }

    private int promptForIndex(List<String> options) throws IOException{

        int count = 1;

        for(String option: options){
            System.out.printf("%d:   %s %n", count, option);
            count ++;
        }
        System.out.println("Your Choice is: ");
        String optionAsString = reader.readLine();
        int choice = Integer.parseInt(optionAsString.trim());
        return choice - 1;
    }

    public void playNex(){
        Song song = songQueue.poll();
        if(song == null){
            System.out.println("Sorry no songs in the queue, select choose to add songs to the queue");
        } else {
            System.out.printf("%n%n%n open %s to hear %s by %s %n%n%n",
                                song.getUrl(),
                                song.getTitle(),
                                song.getArtist());
        }

    }


}