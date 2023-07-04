import request from '@/utils/request'

// 查询检验项目管理列表
export function listInspect_item(query) {
    return request({
        url: '/commondata/inspect_item/list',
        method: 'get',
        params: query
    })
}

// 查询检验项目管理详细
export function getInspect_item(id) {
    return request({
        url: '/commondata/inspect_item/' + id,
        method: 'get'
    })
}

// 新增检验项目管理
export function addInspect_item(data) {
    return request({
        url: '/commondata/inspect_item',
        method: 'post',
        data: data
    })
}

// 修改检验项目管理
export function updateInspect_item(data) {
    return request({
        url: '/commondata/inspect_item',
        method: 'put',
        data: data
    })
}

// 删除检验项目管理
export function delInspect_item(id) {
    return request({
        url: '/commondata/inspect_item/' + id,
        method: 'delete'
    })
}
