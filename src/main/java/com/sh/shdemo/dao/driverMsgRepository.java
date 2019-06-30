package com.sh.shdemo.dao;

import com.sh.shdemo.entity.driverMsg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface driverMsgRepository extends JpaRepository<driverMsg,Long> {
    @Query(value = "select * from drivermsg ORDER BY id",nativeQuery = true)
    List<driverMsg> getAll();
    @Query(value = "select * from drivermsg limit :i,:j ",nativeQuery = true)
    List<driverMsg> getPage(@Param("i")int i, @Param("j")int j);

    @Query(value = "select * from drivermsg where tel = :tel and study = :study",nativeQuery = true)
    List<driverMsg> getPageByTelStudyAll(@Param("tel")String tel, @Param("study")String study);
    @Query(value = "select * from drivermsg where tel = :tel and study = :study limit :i,:j ",nativeQuery = true)
    List<driverMsg> getPageByTelStudy(@Param("tel")String tel, @Param("study")String study,@Param("i")int i, @Param("j")int j);

    @Query(value = "select * from drivermsg where name = :name and study = :study",nativeQuery = true)
    List<driverMsg> getPageByNameStudyAll(@Param("name")String name, @Param("study")String study);
    @Query(value = "select * from drivermsg where name = :name and study = :study limit :i,:j ",nativeQuery = true)
    List<driverMsg> getPageByNameStudy(@Param("name")String name, @Param("study")String study,@Param("i")int i, @Param("j")int j);

    @Query(value = "select * from drivermsg where name = :name and tel = :tel",nativeQuery = true)
    List<driverMsg> getPageByNameTelAll(@Param("name")String name, @Param("tel")String tel);
    @Query(value = "select * from drivermsg where name = :name and tel = :tel limit :i,:j ",nativeQuery = true)
    List<driverMsg> getPageByNameTel(@Param("name")String name, @Param("tel")String tel,@Param("i")int i, @Param("j")int j);

    @Query(value = "select * from drivermsg where username = :name",nativeQuery = true)
    List<driverMsg> getPageByNameAll(@Param("name")String name);
    @Query(value = "select * from drivermsg where username = :name limit :i,:j ",nativeQuery = true)
    List<driverMsg> getPageByName(@Param("name")String name, @Param("i")int i, @Param("j")int j);

    @Query(value = "select * from drivermsg where study = :study",nativeQuery = true)
    List<driverMsg> getPageByStudyAll(@Param("study")String study);
    @Query(value = "select * from drivermsg where study = :study limit :i,:j ",nativeQuery = true)
    List<driverMsg> getPageByStudy(@Param("study")String study, @Param("i")int i, @Param("j")int j);

    @Query(value = "select * from drivermsg where tel = :tel",nativeQuery = true)
    List<driverMsg> getPageByTelAll(@Param("tel")String tel);
    @Query(value = "select * from drivermsg where tel = :tel limit :i,:j ",nativeQuery = true)
    List<driverMsg> getPageByTel(@Param("tel")String tel, @Param("i")int i, @Param("j")int j);

    @Query(value = "select * from drivermsg where username = :name and tel = :tel and study = :study",nativeQuery = true)
    List<driverMsg> getPageByNameTelStudyAll(@Param("name")String name,@Param("tel")String tel,@Param("study")String study);
    @Query(value = "select * from drivermsg where username = :name and tel = :tel and study = :study limit :i,:j ",nativeQuery = true)
    List<driverMsg> getPageByNameTelStudy(@Param("name")String name,@Param("tel")String tel,@Param("study")String study, @Param("i")int i, @Param("j")int j);

    @Modifying
    @Transactional
    @Query(value = "update drivermsg set state = '启用' where id = :id",nativeQuery = true)
    void upStateStart(@Param("id") Integer id);
    @Modifying
    @Transactional
    @Query(value = "update drivermsg set state = '禁用' where id = :id",nativeQuery = true)
    void upStateEnd(@Param("id") Integer id);
    @Modifying
    @Transactional
    @Query(value = "delete from drivermsg where id = :id",nativeQuery = true)
    void del(@Param("id") Integer id);
}
