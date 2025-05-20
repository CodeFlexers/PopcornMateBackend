package com.popcornmate.track.service;


import com.popcornmate.entity.MovieHistory;
import com.popcornmate.entity.User;
import com.popcornmate.entity.UserActivity;
import com.popcornmate.track.dto.PageOnTime;
import com.popcornmate.track.repository.MovieHistoryRepository;
import com.popcornmate.track.repository.UserActivityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserActivityService {

    private final UserActivityRepository userActivityRepository;
    private final MovieHistoryRepository movieHistoryRepository;

    public UserActivityService(UserActivityRepository userActivityRepository, MovieHistoryRepository movieHistoryRepository) {
        this.userActivityRepository = userActivityRepository;
        this.movieHistoryRepository = movieHistoryRepository;
    }

    @Transactional
    public String recordUserEnter(Long movieCode, Integer userCode, PageOnTime pageOnTime) {

        try{
             userActivityRepository.save(new UserActivity(movieCode, userCode, pageOnTime.getTime()));
             return "사용자 활동 기록 성공";
        } catch (Exception e){
            throw new RuntimeException("들어갈 때 기록 실패");
        }
    }

    public String recordUserMovie(Integer userCode, Long movieCode) {

        try{
            movieHistoryRepository.save(new MovieHistory(userCode, movieCode));
            return "리뷰 등록 시 시청한 영화 등록 성공!!";
        } catch (Exception e) {
            throw new RuntimeException("리뷰 등록 시 시청한 영화 등록 실패!!!");
        }

    }
}
