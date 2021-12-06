package com.example.demo.src.movietime;

import com.example.demo.src.movietime.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class MovieTimeDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){this.jdbcTemplate = new JdbcTemplate(dataSource);}

    public List<GetMovieTimeRes> getMovieTimesByParams(String showdate,int movieIdx, int branchIdx){
        String getMovieTimeQuery = "select movietime.Idx, movieIdx, theaterIdx, showdate, startTime, endTime\n" +
                                    "from movietime inner join theater\n" +
                                    "on movietime.theaterIdx = theater.Idx\n" +
                                    "where movietime.showdate = ? and movietime.movieIdx = ? and theater.branchIdx = ?";
        String getMovieTimeParam0 = showdate;
        int getMovieTimeParam1 = movieIdx;
        int getMovieTimeParam2 = branchIdx;
        return this.jdbcTemplate.query(getMovieTimeQuery,
                (rs, rowNum) -> new GetMovieTimeRes(
                        rs.getInt("Idx"),
                        rs.getInt("movieIdx"),
                        rs.getInt("theaterIdx"),
                        rs.getString("showdate"),
                        rs.getString("startTime"),
                        rs.getString("endTime")),
                getMovieTimeParam0, getMovieTimeParam1,getMovieTimeParam2);
    }

    public int getTotalSeat(int movietimeIdx){
        String getTotalCountQuery = "select count(*) from seatinfo where movietimeIdx = ?";
        int getTotalCountParams = movietimeIdx;
        return this.jdbcTemplate.queryForObject(getTotalCountQuery,
                int.class,
                getTotalCountParams);
    }

    public int getRemainSeat(int movietimeIdx){
        String getTotalCountQuery = "select count(*) from seatinfo where movietimeIdx = ? and status = 0";
        int getTotalCountParams = movietimeIdx;
        return this.jdbcTemplate.queryForObject(getTotalCountQuery,
                int.class,
                getTotalCountParams);
    }
}
