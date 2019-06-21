package com.sh.shdemo.dao;

import com.sh.shdemo.entity.RegMsg;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@EnableConfigurationProperties()
public interface RegMsgRepository extends JpaRepository<RegMsg,Long> {
}
