package org.jshand.module.commondata.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.jshand.module.commondata.mapper.CheckItemMapper;
import org.jshand.module.commondata.domain.CheckItem;
import org.jshand.module.commondata.service.ICheckItemService;

/**
 * 检查项目管理Service业务层处理
 *
 * @author 金山老师
 * @date 2023-03-31
 */
@Service
public class CheckItemServiceImpl extends ServiceImpl<CheckItemMapper, CheckItem> implements ICheckItemService {

}