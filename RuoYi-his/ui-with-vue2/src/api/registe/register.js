import request from '@/utils/request'

// 查询诊疗信息列表
export function listRegister(query) {
    return request({
        url: '/registe/register/list',
        method: 'get',
        params: query
    })
}

// 查询诊疗信息详细
export function getRegister(id) {
    return request({
        url: '/registe/register/' + id,
        method: 'get'
    })
}

// 新增诊疗信息
export function addRegister(data) {
    return request({
        url: '/registe/register',
        method: 'post',
        data: data
    })
}

// 修改诊疗信息
export function updateRegister(data) {
    return request({
        url: '/registe/register',
        method: 'put',
        data: data
    })
}

// 删除诊疗信息
export function delRegister(id) {
    return request({
        url: '/registe/register/' + id,
        method: 'delete'
    })
}


// 新增诊疗信息
export function getInfo() {
  return request({
    url: '/registe/register/getInfo',
    method: 'get',
  })
}


//listDoctor 拉取医生信息
export function listDoctor(deptId,registLevel) {
  return request({
    url: '/registe/register/queryDoctor?deptId='+deptId+'&registLevel='+registLevel,
    method: 'get',
  })
}


//listDoctor 拉取医生信息
export function revert(id) {
  return request({
    url: '/registe/register/revert/'+id,
    method: 'get',
  })
}
