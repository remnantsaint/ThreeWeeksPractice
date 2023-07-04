package org.jshand.module.registe.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jshand.module.registe.domain.ApplyItem;
import org.jshand.module.registe.domain.Register;
import org.jshand.module.registe.vo.RegisterVo;

import java.util.List;

/**
 * 诊疗信息Service接口
 *
 * @author 金山老师
 * @date 2023-03-29
 */
public interface IRegisterService extends IService<Register> {

    List<ApplyItem> selectItemByRegisterId(Long registerId);

    boolean fee(List<RegisterVo> registVoList);
    boolean refund(List<RegisterVo> registVoList);

    boolean revert(long id);
}