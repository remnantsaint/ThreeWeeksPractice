package org.jshand.module.doctor.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.jshand.module.doctor.domain.CheckApply;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jshand.module.medical_technology.vo.ApplyQueryVo;
import org.jshand.module.medical_technology.vo.CheckApplyVO;

import java.util.List;

/**
 * 检查申请Mapper接口
 *
 * @author 金山老师
 * @date 2023-04-03
 */
public interface CheckApplyMapper extends BaseMapper<CheckApply> {


    /**
     *
     *  root{
     *      arg0           IPage<CheckApplyVO> page
     *      arg1     vo     ApplyQueryVo vo
     *
     *      param1        IPage<CheckApplyVO> page
     *      param2  vo(@Param("vo"))    ApplyQueryVo vo
     *  }
     *
     *
     * @param page
     * @param vo
     * @return
     */


//    List<CheckApplyVO>  selectApplyByRegister();

    //改造成支持分页的

    Page<CheckApplyVO> selectApplyByRegister(IPage<CheckApplyVO> page , @Param("vo") ApplyQueryVo vo);





}
