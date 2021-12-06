package com.example.demo.src.branch;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.branch.model.*;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchProvider {

    private final BranchDao branchDao;

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    public BranchProvider(BranchDao branchDao){
        this.branchDao = branchDao;
    }

    public List<GetBranchRes> getBranches() throws BaseException{
        try{
            List<GetBranchRes> getBranchRes = branchDao.getBranches();
            return getBranchRes;
        }
        catch(Exception exception){
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    public List<GetLocationRes> getLocations() throws BaseException{
        try{
            List<GetLocationRes> getLocationRes = branchDao.getLocations();
            return getLocationRes;
        }
        catch(Exception exception){
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    public List<GetBranchRes> getBranchesByLocation(int locationIdx) throws BaseException{
        try{
            List<GetBranchRes> getBranchRes = branchDao.getBranchesByLocation(locationIdx);
            return getBranchRes;
        }
        catch(Exception exception){
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }
}
