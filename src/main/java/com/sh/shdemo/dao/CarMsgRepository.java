package com.sh.shdemo.dao;

import com.sh.shdemo.entity.RegMsg;
import com.sh.shdemo.entity.driverMsg;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@CacheConfig(cacheNames="regMsgRepository")
public interface CarMsgRepository extends JpaRepository<RegMsg,Long> {

    @Query(value = "select * from regmsg limit :i,:j ",nativeQuery = true)
    List<RegMsg> getPage(@Param("i")int i, @Param("j")int j);

    @Query(value = "select * from regmsg ORDER BY id",nativeQuery = true)
    List<RegMsg> getAll();

    @Modifying
    @Transactional
    @Query(value = "delete from regmsg where id = :id",nativeQuery = true)
    void del(@Param("id") long id);

    @Query(value = "select * from regmsg where brand = :brand and color = :color and ctype = :ctype limit :i,:j ",nativeQuery = true)
    List<RegMsg> getPageByBrandColorCtype(@Param("brand")String brand, @Param("color")String color, @Param("ctype")String ctype, @Param("i")int i, @Param("j")int j);

    @Query(value = "select * from regmsg where id = :id",nativeQuery = true)
    RegMsg getCarById(@Param("id") long id);

    @Query(value = "select * from regmsg where brand LIKE :brand and color LIKE :color and ctype LIKE :ctype",nativeQuery = true)
    List<RegMsg> getAllLikeBrandColorCtype(String brand, String color, String ctype);

    @Transactional
    @Modifying
    @Query(value = "update regmsg set state = '启用'where id = :id",nativeQuery = true)
    void upStateStart(@Param("id") long id);

    @Transactional
    @Modifying
    @Query(value = "update regmsg set state = '禁用'where id = :id",nativeQuery = true)
    void upStateEnd(@Param("id") long id);
}
