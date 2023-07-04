package org.jshand.module.registe.mapper;

import org.jshand.module.registe.domain.ApplyItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RegisterMapperTest {


    @Autowired
    RegisterMapper registerMapper;


    @Test
    void selectItemByRegisterId() {

        long registerId = 3;
        List<ApplyItem> applyItems = registerMapper.selectItemByRegisterId(registerId);

        applyItems.forEach(System.out::println);

    }
}