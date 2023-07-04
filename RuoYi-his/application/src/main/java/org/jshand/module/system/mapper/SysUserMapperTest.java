package org.jshand.module.system.mapper;

import org.jshand.support.common.core.domain.entity.SysUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SysUserMapperTest {


    @Autowired
    SysUserMapper sysUserMapper;


    @Test
    void selectDoctorByDeptAndRegistLevel() {

        Long deptId = 101L;
        Integer registLevel = 2;
        List<SysUser> sysUsers = sysUserMapper.selectDoctorByDeptAndRegistLevel(deptId, registLevel);
        sysUsers.forEach(System.out::println);


        System.out.println();
        System.out.println();
        System.out.println();


        registLevel = 3;
        sysUsers = sysUserMapper.selectDoctorByDeptAndRegistLevel(deptId, registLevel);
        sysUsers.forEach(System.out::println);

    }
}