package com.example.demo.src.movie;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.movie.model.GetMovieRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/movies")
public class MovieController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final MovieProvider movieProvider;
    @Autowired
    private final MovieService movieService;


    public MovieController(MovieProvider movieProvider, MovieService movieService){
        this.movieProvider = movieProvider;
        this.movieService = movieService;
    }

    /**
     * 회원 조회 API
     * [GET] /customers
     * 회원 번호 및 이메일 검색 조회 API
     * @return BaseResponse<List<GetUserRes>>
     */
    //Query String
    @ResponseBody
    @GetMapping("") // (GET) 127.0.0.1:9000/app/users
    public BaseResponse<List<GetMovieRes>> getMovies() {
        try{
            // Get Users
            List<GetMovieRes> getMovieRes = movieProvider.getMovies();
            return new BaseResponse<>(getMovieRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 회원 1명 조회 API
     * [GET] /customer/:Idx
     * @return BaseResponse<GetCustomerRes>
     */
    // Path-variable
    @ResponseBody
    @GetMapping("/{Idx}") // (GET) 127.0.0.1:9000/app/users/:userIdx
    public BaseResponse<GetMovieRes> getMovie(@PathVariable("Idx") int Idx) {
        // Get Users
        try{
            GetMovieRes getMovieRes = movieProvider.getMovie(Idx);
            return new BaseResponse<>(getMovieRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}
