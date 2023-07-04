package org.jshand.module.commondata.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.jshand.module.commondata.mapper.InspectItemMapper;
import org.jshand.module.commondata.domain.InspectItem;
import org.jshand.module.commondata.service.IInspectItemService;

/**
 * 检验项目管理Service业务层处理
 *
 * @author 金山
 * @date 2023-03-31
 */
@Service
public class InspectItemServiceImpl extends ServiceImpl<InspectItemMapper, InspectItem> implements IInspectItemService {

}