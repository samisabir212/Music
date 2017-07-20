package com.samisabir;

import com.samisabir.model.Artist;
import com.samisabir.model.Datasource;
import com.samisabir.model.SongArtist;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        Datasource datasource = new Datasource();
        if (!datasource.open()) {
            System.out.println("cant open datasource");
            return;
        }


        //create an instance of a list of Artist class
        List<Artist> artists = datasource.queryArtists(3);

        if (artists == null) {
            System.out.println(" no artists ");
            return;
        }

        /*
        looping through all the artists
         */
        for (Artist artist : artists) {
            System.out.println("ID = " + artist.getId() + ", Name = " + artist.getName());

        }
        List<String> albumsForArtist =
                datasource.queryAlbumsForArtist("Iron Maiden", Datasource.ORDER_BY_ASC);

        for(String album : albumsForArtist) {
            System.out.println(album);
        }

        List<SongArtist> songArtists = datasource.queryArtistsForSong("Go Your Own Way", Datasource.ORDER_BY_ASC);
        if(songArtists == null) {
            System.out.println("Couldn't find the artist for the song");
            return;
        }

        for(SongArtist artist : songArtists) {
            System.out.println("Artist name = " + artist.getArtistName() +
                    " Album name = " + artist.getAlbumName() +
                    " Track = " + artist.getTrack());
        }

        datasource.querySongsMetadata();


        int count = datasource.getCount(Datasource.TABLE_SONGS);
        System.out.println("total number of songs: " + count);



        datasource.close();




    }
}
