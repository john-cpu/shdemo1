package com.sh.shdemo.Service;

import com.sh.shdemo.entity.RegMsg;
import com.sh.shdemo.entity.driverMsg;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarService {
    List<RegMsg> getPage(int i, int j);
    List<RegMsg> getAll();
    void del(long id);
    List<RegMsg> getPageByBrandColorCtype(String brand, String color, String ctype, int i, int j);
    RegMsg getCarById(long id);
    RegMsg save(RegMsg rm);
    void upStateStart(long id);
    void upStateEnd(long id);
    List<RegMsg> getAllLikeBrandColorCtype(String brand, String color, String ctype);
}
