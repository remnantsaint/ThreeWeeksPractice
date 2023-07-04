package org.jshand.module.doctor.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jshand.module.medical_technology.vo.ApplyQueryVo;
import org.jshand.module.medical_technology.vo.CheckApplyVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CheckApplyMapperTest {



    @Autowired
    CheckApplyMapper checkApplyMapper;


    @Test
    void selectApplyByRegister() {

        IPage<CheckApplyVO> page  = new Page<CheckApplyVO>();
        page.setCurrent(1);
        page.setSize(1);

        ApplyQueryVo queryVo = new ApplyQueryVo();
        queryVo.setRegisterId(3L);
        queryVo.setItemName("CT");
        page = checkApplyMapper.selectApplyByRegister(page ,queryVo);

        page.getRecords().forEach(System.out::println);


        System.out.println("\r\r");



        page.setCurrent(2);
        page.setSize(1);
        page = checkApplyMapper.selectApplyByRegister(page ,queryVo);

        page.getRecords().forEach(System.out::println);

    }
}