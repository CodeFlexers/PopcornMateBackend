package com.popcornmate.recommender.dto;

import lombok.Data;
import java.util.List;

@Data
public class TmdbMovieResponse {

    private List<TmdbMovieDTO> results;

}
