package com.sh.shdemo.dao;

import com.sh.shdemo.entity.driverMsg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DriverMsgRepository extends JpaRepository<driverMsg,Long> {

    @Query(value = "select * from drivermsg ORDER BY id",nativeQuery = true)
    List<driverMsg> getAll();

    @Query(value = "select * from drivermsg ORDER BY id limit :i,:j ",nativeQuery = true)
    List<driverMsg> getPage(@Param("i")int i, @Param("j")int j);

    @Query(value = "select * from drivermsg where username LIKE :name and tel LIKE :tel and study LIKE :study limit :i,:j ",nativeQuery = true)
    List<driverMsg> getLimitLikePageLimitNameTelStudy(String name, String tel, String study, int i, int j);

    @Query(value = "select * from drivermsg where username LIKE :name and tel LIKE :tel and study LIKE :study",nativeQuery = true)
    List<driverMsg> getAllLikePageLimitNameTelStudy(String name, String tel, String study);

    @Modifying
    @Transactional
    @Query(value = "delete from drivermsg where id = :id",nativeQuery = true)
    void del(@Param("id") long id);

    @Query(value="select * from drivermsg where id = :id",nativeQuery = true)
    driverMsg getDriverById(@Param("id") long id);

    @Transactional
    @Modifying
    @Query(value = "update drivermsg set state = '启用'where id = :id",nativeQuery = true)
    void upDriverStateStart(@Param("id") long id);

    @Transactional
    @Modifying
    @Query(value = "update drivermsg set state = '禁用'where id = :id",nativeQuery = true)
    void upDriverStateEnd(@Param("id") long id);

}
