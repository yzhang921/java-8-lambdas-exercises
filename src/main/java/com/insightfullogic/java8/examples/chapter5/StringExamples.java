package com.insightfullogic.java8.examples.chapter5;

import com.insightfullogic.java8.examples.chapter1.Artist;

import java.util.List;
import java.util.stream.Collectors;

public class StringExamples {

    public static String formatArtists(List<Artist> artists) {
        String result =
                artists.stream()
                        .map(Artist::getName)
                        .collect(Collectors.joining(", ", "[", "]"));
        return result;
    }

    public static String formatArtistsForLoop(List<Artist> artists) {
        StringBuilder builder = new StringBuilder("[");
        for (Artist artist : artists) {
            if (builder.length() > 1)
                builder.append(", ");

            String name = artist.getName();
            builder.append(name);
        }
        builder.append("]");
        String result = builder.toString();
        return result;
    }

    public static String formatArtistsRefactor1(List<Artist> artists) {
        StringBuilder builder = new StringBuilder("[");
        artists.stream()
                .map(Artist::getName)
                .forEach(name -> {
                    if (builder.length() > 1)
                        builder.append(", ");

                    builder.append(name);
                });
        builder.append("]");
        String result = builder.toString();
        return result;
    }

    public static String formatArtistsRefactor2(List<Artist> artists) {
        StringBuilder reduced =
                artists.stream()
                        .map(Artist::getName)
                        .reduce(new StringBuilder(), (builder, name) -> {
                            if (builder.length() > 0)
                                builder.append(", ");

                            builder.append(name);
                            return builder;
                        }, (left, right) -> left.append(right));

        reduced.insert(0, "[");
        reduced.append("]");
        String result = reduced.toString();
        return result;
    }

    public static String formatArtistsRefactor3(List<Artist> artists) {
        StringCombiner combined =
                artists.stream()
                        .map(Artist::getName)
                        .reduce(new StringCombiner(", ", "[", "]"),
                                StringCombiner::add,
                                StringCombiner::merge);

        String result = combined.toString();
        return result;
    }

    public static String formatArtistsRefactor4(List<Artist> artists) {
        String result =
                artists.stream()
                        .map(Artist::getName)
                        .reduce(new StringCombiner(", ", "[", "]"),
                                StringCombiner::add,
                                StringCombiner::merge)
                        .toString();
        return result;
    }

    public static String formatArtistsRefactor5(List<Artist> artists) {
        String result =
                artists.stream()
                        .map(Artist::getName)
                        .collect(new StringCollector(", ", "[", "]"));
        return result;
    }

    public static String formatArtistsReducing(List<Artist> artists) {
        String result =
                artists.stream()
                        .map(Artist::getName)
                        .collect(Collectors.reducing(
                                new StringCombiner(", ", "[", "]"),
                                name -> new StringCombiner(", ", "[", "]").add(name),
                                StringCombiner::merge))
                        .toString();
        return result;
    }

    /**
     * compare with reducing with reduce
     * @param artists
     * @return
     */
    public static String formatArtistsReduce(List<Artist> artists) {
        StringCombiner combined = artists.stream()
                .map(Artist::getName)
                .reduce(new StringCombiner(",", "[", "]"),
                        StringCombiner::add,
                        StringCombiner::merge);
       return combined.toString();
    }

}
