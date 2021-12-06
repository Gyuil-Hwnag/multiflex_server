package com.example.demo.src.movietime;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.movietime.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieTimeProvider {

    private final MovieTimeDao movieTimeDao;

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public MovieTimeProvider(MovieTimeDao movieTimeDao){
        this.movieTimeDao = movieTimeDao;
    }

    public List<GetMovieTimeRes> getMovieTimeByParams(String showdate, int movieIdx, int branchIdx) throws BaseException {
        try{
            List<GetMovieTimeRes> getMovieTimeRes = movieTimeDao.getMovieTimesByParams(showdate, movieIdx, branchIdx);
            return getMovieTimeRes;
        }
        catch(Exception exception){
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    public int getTotalSeat(int movietimeIdx) throws BaseException{
        try{
            return movieTimeDao.getTotalSeat(movietimeIdx);
        }catch(Exception exception){
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    public int getRemainSeat(int movietimeIdx) throws BaseException{
        try{
            return movieTimeDao.getRemainSeat(movietimeIdx);
        }catch(Exception exception){
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }
}
