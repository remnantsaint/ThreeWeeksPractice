import request from '@/utils/request'

// 查询患者列表(本人、本科室)
export function listRegister(type) {
    return request({
        url: '/doctor/listRegister/'+type,
        method: 'get',
    })
}


// 修改诊疗信息
export function saveRegister(data) {
  return request({
    url: '/doctor/saveRegister',
    method: 'put',
    data: data
  })
}



// 查询检查项目管理列表
export function listCheck_item(query) {
  return request({
    url: '/doctor/check_item/list',
    method: 'get',
    params: query
  })
}



// 查询检验项目管理列表
export function listInspectItem(query) {
  return request({
    url: '/doctor/inspect_item/list',
    method: 'get',
    params: query
  })
}


export function saveCheckList(data) {
  return request({
    url: '/doctor/saveCheckList',
    method: 'post',
    data: data
  })
}

export function saveInspectList(data) {
  return request({
    url: '/doctor/saveInspectList',
    method: 'post',
    data: data
  })
}


//根据患者id查询检查、检验项目
export function getInfo(id) {
  return request({
    url: '/doctor/getInfo/'+id,
    method: 'get'
  })
}


