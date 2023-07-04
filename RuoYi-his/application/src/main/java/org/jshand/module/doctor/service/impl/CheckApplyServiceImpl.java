package org.jshand.module.doctor.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jshand.common.HisConstants;
import org.jshand.module.medical_technology.vo.ApplyQueryVo;
import org.jshand.module.medical_technology.vo.CheckApplyResultVo;
import org.jshand.module.medical_technology.vo.CheckApplyVO;
import org.jshand.module.system.domain.SysFileInfo;
import org.jshand.module.system.mapper.SysFileInfoMapper;
import org.jshand.support.common.utils.ip.IpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.jshand.module.doctor.mapper.CheckApplyMapper;
import org.jshand.module.doctor.domain.CheckApply;
import org.jshand.module.doctor.service.ICheckApplyService;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 检查申请Service业务层处理
 *
 * @author 金山老师
 * @date 2023-04-03
 */
@Service
public class CheckApplyServiceImpl extends ServiceImpl<CheckApplyMapper, CheckApply> implements ICheckApplyService {


    @Autowired
    SysFileInfoMapper sysFileInfoMapper;

    /*
    com.baomidou.mybatisplus.core.metadata.IPage
    com.baomidou.mybatisplus.core.metadata.IPage
     */
    @Override
    public Page<CheckApplyVO> listApplyByRegister(IPage<CheckApplyVO> page, ApplyQueryVo vo) {
        return getBaseMapper().selectApplyByRegister(page,vo);
    }


    /**
     * 1 插入数据（sys_file_info） 获取ID
     * 2 更新— check_apply  result、 文件ID 、状态
     * @param vo
     * @return
     */
    @Override
    @Transactional
    public boolean result(CheckApplyResultVo vo, HttpServletRequest request) {

        int count = 0;

        SysFileInfo fileInfo = new SysFileInfo();

        fileInfo.setFileName(vo.getFileName());
        fileInfo.setFilePath(vo.getFilePath());
        fileInfo.setOrgName(vo.getOrgName());
        fileInfo.setIp(IpUtils.getIpAddr(request));

        count += sysFileInfoMapper.insert(fileInfo);



        //当前service 表对应的Mapper

        CheckApply checkApply = new CheckApply();

        checkApply.setResult(vo.getResult());
        checkApply.setFileId(fileInfo.getFileId());
        checkApply.setStatus(HisConstants.DOCTOR_CHECK_APPLY_STATUS_3);

        checkApply.setId(vo.getId());
        count += getBaseMapper().updateById(checkApply);




        return count == 2 ;
    }
}