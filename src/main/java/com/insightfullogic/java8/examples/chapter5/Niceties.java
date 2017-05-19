package com.insightfullogic.java8.examples.chapter5;

import com.insightfullogic.java8.examples.chapter1.Album;
import com.insightfullogic.java8.examples.chapter1.Artist;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Niceties {

    abstract class ArtistService {

        protected Map<String, Artist> artistCache = new HashMap<>();

        public abstract Artist getArtist(String name);

        protected Artist readArtistFromDB(String name) {
            return new Artist(name, "UK");
        }
    }

    class OldArtistService extends ArtistService {
        public Artist getArtist(String name) {
            Artist artist = artistCache.get(name);
            if (artist == null) {
                artist = readArtistFromDB(name);
                artistCache.put(name, artist);
            }
            return artist;
        }
    }

    class Java8ArtistService extends ArtistService {
        public Artist getArtist(String name) {
            return artistCache.computeIfAbsent(name, this::readArtistFromDB);
        }
    }


    class ImperativeCount {

        public Map<Artist, Integer> countAlbums(Map<Artist, List<Album>> albumsByArtist) {
            Map<Artist, Integer> countOfAlbums = new HashMap<>();
            for (Map.Entry<Artist, List<Album>> entry : albumsByArtist.entrySet()) {
                Artist artist = entry.getKey();
                List<Album> albums = entry.getValue();
                countOfAlbums.put(artist, albums.size());
            }
            return countOfAlbums;
        }
    }

    class Java8Count {
        public Map<Artist, Integer> countAlbums(Map<Artist, List<Album>> albumsByArtist) {
            Map<Artist, Integer> countOfAlbums = new HashMap<>();
            albumsByArtist.forEach((artist, albums) -> {
                countOfAlbums.put(artist, albums.size());
            });
            return countOfAlbums;
        }
    }


}
