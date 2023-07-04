package org.jshand.module.registe.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jshand.common.HisConstants;
import org.jshand.module.doctor.domain.CheckApply;
import org.jshand.module.doctor.domain.InspectApply;
import org.jshand.module.doctor.mapper.CheckApplyMapper;
import org.jshand.module.doctor.mapper.InspectApplyMapper;
import org.jshand.module.registe.domain.ApplyItem;
import org.jshand.module.registe.vo.RegisterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.jshand.module.registe.mapper.RegisterMapper;
import org.jshand.module.registe.domain.Register;
import org.jshand.module.registe.service.IRegisterService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 诊疗信息Service业务层处理
 *
 * @author 金山老师
 * @date 2023-03-29
 */
@Service
public class RegisterServiceImpl extends ServiceImpl<RegisterMapper, Register> implements IRegisterService {



    @Autowired
    CheckApplyMapper checkApplyMapper;


    @Autowired
    InspectApplyMapper inspectApplyMapper;



    @Override
    public List<ApplyItem> selectItemByRegisterId(Long registerId) {
        return getBaseMapper().selectItemByRegisterId(registerId);
    }

    @Override
    @Transactional
    public boolean fee(List<RegisterVo> registVoList) {
        int count = 0;

        for (RegisterVo registerVo : registVoList) {
            if("check".equals(registerVo.getType())){
                CheckApply checkApply = new CheckApply();
                checkApply.setId(registerVo.getId());
                checkApply.setStatus(HisConstants.DOCTOR_CHECK_APPLY_STATUS_2);
                count += checkApplyMapper.updateById(checkApply);
            }else if ("inspect".equals(registerVo.getType())){
                InspectApply inspectApply = new InspectApply();
                inspectApply.setId(registerVo.getId());
                inspectApply.setStatus(HisConstants.DOCTOR_INSPECT_APPLY_STATUS_2);
                count += inspectApplyMapper.updateById(inspectApply);
            }
        }


        return registVoList.size() == count;
    }

    @Override
    @Transactional
    public boolean refund(List<RegisterVo> registVoList) {
        int count = 0;

        for (RegisterVo registerVo : registVoList) {
            if("check".equals(registerVo.getType())){
                CheckApply checkApply = new CheckApply();
                checkApply.setId(registerVo.getId());
                checkApply.setStatus(HisConstants.DOCTOR_CHECK_APPLY_STATUS_4);
                count += checkApplyMapper.updateById(checkApply);
            }else if ("inspect".equals(registerVo.getType())){
                InspectApply inspectApply = new InspectApply();
                inspectApply.setId(registerVo.getId());
                inspectApply.setStatus(HisConstants.DOCTOR_INSPECT_APPLY_STATUS_4);
                count += inspectApplyMapper.updateById(inspectApply);
            }
        }

        return registVoList.size() == count;
    }



    @Override
    public boolean revert(long id) {
        //1 new 实体（ID 状态）

        //2 先查询后更新


        Register register = getBaseMapper().selectById(id);


        register.setStatus(HisConstants.REGIST_STATUS_4);
        ;




        return getBaseMapper().updateById(register)>0;
    }
}