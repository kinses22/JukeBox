package com.kinses22.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen on 16/07/2016.
 */
public class SongLibrary {

    private List<Song> songLibrary;

    public SongLibrary(){
        songLibrary = new ArrayList<>();
    }

    public void addSong(Song song){
        songLibrary.add(song);

    }

    public int getSongCount(){
        return songLibrary.size();
    }
}
