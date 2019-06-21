package com.sh.shdemo.dao;

import com.sh.shdemo.entity.SysRole;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableConfigurationProperties()
public interface SysRoleRepository extends JpaRepository<SysRole,Long> {
    @Query(value="select * from sysrole where id = any(select roles_id from sysuser_sysrole where SysUser_id = :id)",nativeQuery = true)
    List<SysRole> selectByPrimaryKey(@Param("id")Long id);
}
