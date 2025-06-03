package com.popcornmate.repository;

public interface UserGenresProjection {

    Integer getGenreCode();
    String getName();
    Long getGenreCount();
    Long getTimeLike();
    Long getWait();
}
