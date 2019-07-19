package com.sh.shdemo.Service.ServiceImpl;

import com.sh.shdemo.Service.DriverService;
import com.sh.shdemo.dao.DriverMsgRepository;
import com.sh.shdemo.entity.driverMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {
    @Autowired
    DriverMsgRepository dmr;
    @Override
    public List<driverMsg> getAll() {
        return dmr.getAll();
    }

    @Override
    public List<driverMsg> getPage(int i, int j) {
        return dmr.getPage(i,j);
    }

    @Override
    @Cacheable(value = "driver", key = "#id")
    public driverMsg getDriverById(long id){
        return dmr.getDriverById(id);
    }

    @Override
    public List<driverMsg> getPageByPageLimitNameTelStudy(String name, String tel, String study, int i, int j) {
        return dmr.getLimitLikePageLimitNameTelStudy(name, tel, study,  i,  j);
    }

    @Override
    public List<driverMsg> getAllByNameTelStudy(String name, String tel, String study) {
        return dmr.getAllLikePageLimitNameTelStudy(name,tel,study);
    }

    @Override
    public void upDriverStateStart(long id) {
        this.dmr.upDriverStateStart(id);
    }

    @Override
    public void upDriverStateEnd(long id) {
        this.dmr.upDriverStateEnd(id);
    }

    @Override
    @CacheEvict(value = "driver",key = "#p0")
    public void del(long id) {
        dmr.del(id);
    }

    @Override
    @CachePut(value = "driver",key = "#dm.getId()")
    public driverMsg save(driverMsg dm){
        return dmr.save(dm);
    }

}
