package org.jshand.module.system.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.jshand.module.system.mapper.SysStuMapper;
import org.jshand.module.system.domain.SysStu;
import org.jshand.module.system.service.ISysStuService;

/**
 * 学生管理Service业务层处理
 *
 * @author 张金山
 * @date 2023-03-24
 */
@Service
public class SysStuServiceImpl extends ServiceImpl<SysStuMapper, SysStu> implements ISysStuService {

}