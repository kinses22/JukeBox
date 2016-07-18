package com.kinses22.model;

import java.util.*;

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

    private Map<String, List<Song>> browseByArtist(){
        Map<String, List<Song>> browseByArtist = new HashMap<>();
        for(Song song: songLibrary){
            List<Song> songsByArtist = browseByArtist.get(song.getArtist());
            if(songsByArtist == null){
                songsByArtist = new ArrayList<>();
                browseByArtist.put(song.getArtist(),songsByArtist);
            }
            songsByArtist.add(song);
        }
        return browseByArtist;
    }

    public Set<String> artistBrowser(){
        return browseByArtist().keySet();
    }

    public List<Song> getSongsForArtist(String artistName){
        return browseByArtist().get(artistName);


    }

}
