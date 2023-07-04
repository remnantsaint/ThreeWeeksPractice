import request from '@/utils/request'

// 查询检查申请列表
export function listCheck_apply(data) {

  return request({
    url: '/doctor/check_apply/listByRegister',
    method: 'get',
    params: data
  })
}

// 查询检查申请详细
export function getCheck_apply(id) {
  return request({
    url: '/doctor/check_apply/' + id,
    method: 'get'
  })
}

// 新增检查申请
export function addCheck_apply(data) {
  return request({
    url: '/doctor/check_apply',
    method: 'post',
    data: data
  })
}

// 修改检查申请
export function updateCheck_apply(data) {
  return request({
    url: '/doctor/check_apply',
    method: 'put',
    data: data
  })
}

// 删除检查申请
export function delCheck_apply(id) {
  return request({
    url: '/doctor/check_apply/' + id,
    method: 'delete'
  })
}


// 填写检查结果
export function result(data) {
  return request({
    url: '/doctor/check_apply/result',
    method: 'post',
    data: data
  })
}
