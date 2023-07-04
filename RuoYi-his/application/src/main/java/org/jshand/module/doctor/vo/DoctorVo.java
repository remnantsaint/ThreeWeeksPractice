package org.jshand.module.doctor.vo;

import lombok.Data;
import org.jshand.module.doctor.domain.CheckApply;
import org.jshand.module.doctor.domain.InspectApply;

import java.util.List;


@Data
public class DoctorVo {

    private List<CheckApply> checkApplyList;
    private List<InspectApply> inspectApplyList;



}
