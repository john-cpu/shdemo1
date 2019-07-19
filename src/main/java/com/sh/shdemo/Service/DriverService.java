package com.sh.shdemo.Service;

import com.sh.shdemo.entity.driverMsg;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DriverService {
    List<driverMsg> getAll();
    List<driverMsg> getPage(int i, int j);
    void del(long id);
    driverMsg save(driverMsg dm);
    driverMsg getDriverById(long id);

    List<driverMsg> getPageByPageLimitNameTelStudy(String name, String tel, String study, int i, int j);

    List<driverMsg> getAllByNameTelStudy(String name, String tel, String study);
    void upDriverStateStart(long id);
    void upDriverStateEnd(long id);
}
