package com.sh.shdemo.Service.ServiceImpl;

import com.sh.shdemo.Service.CarService;
import com.sh.shdemo.dao.CarMsgRepository;
import com.sh.shdemo.entity.RegMsg;
import com.sh.shdemo.entity.driverMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CarServiceImpl implements CarService {
    @Autowired
    CarMsgRepository cmr;

    @Override
    public List<RegMsg> getPage(int i, int j) {
        return cmr.getPage(i, j);
    }

    @Override
    public List<RegMsg> getAll() {
        return cmr.getAll();
    }
    @Override
    public List<RegMsg> getAllLikeBrandColorCtype(String brand, String color, String ctype){
        return this.cmr.getAllLikeBrandColorCtype(brand,color,ctype);
    }
    public List<RegMsg> getPageByBrandColorCtypeAll(String brand, String color, String ctype) {

        if (brand.isEmpty() && color.isEmpty() && ctype.isEmpty()) {
            return cmr.getAll();
        }
        RegMsg rm = new RegMsg();
        rm.setBrand(brand);
        rm.setColor(color);
        rm.setCtype(ctype);
        Example<RegMsg> example = Example.of(rm);
        return cmr.findAll(example);
    }

    @Override
    public List<RegMsg> getPageByBrandColorCtype(String brand, String color, String ctype, int i, int j) {
        if (brand.isEmpty() && color.isEmpty() && ctype.isEmpty()) {
            return cmr.getPage(i, j);
        }
        return cmr.getPageByBrandColorCtype(brand, color, ctype, i, j);
    }

    @Override
    @Cacheable(value = "car",key = "#id")
    public RegMsg getCarById(long id) {
        return cmr.getCarById(id);
    }

    @Override
    @CachePut(value = "car", key = "#p0.getId()")
    public RegMsg save(RegMsg rm) {
        return cmr.save(rm);
    }

    @Override
    public void upStateStart(long id) {
        this.cmr.upStateStart(id);
    }

    @Override
    public void upStateEnd(long id) {
        this.cmr.upStateEnd(id);
    }

    @Override
    @CacheEvict(value = "car",key = "#p0")
    public void del(long id) {
        cmr.del(id);
    }
}
