package org.jshand.module.registe.mapper;

import org.jshand.module.registe.domain.ApplyItem;
import org.jshand.module.registe.domain.Register;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * 诊疗信息Mapper接口
 *
 * @author 金山老师
 * @date 2023-03-29
 */
public interface RegisterMapper extends BaseMapper<Register> {


    List<ApplyItem> selectItemByRegisterId(Long registerId);



}
