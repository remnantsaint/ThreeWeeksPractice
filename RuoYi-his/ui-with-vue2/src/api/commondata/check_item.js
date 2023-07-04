import request from '@/utils/request'

// 查询检查项目管理列表
export function listCheck_item(query) {
    return request({
        url: '/commondata/check_item/list',
        method: 'get',
        params: query
    })
}

// 查询检查项目管理详细
export function getCheck_item(id) {
    return request({
        url: '/commondata/check_item/' + id,
        method: 'get'
    })
}

// 新增检查项目管理
export function addCheck_item(data) {
    return request({
        url: '/commondata/check_item',
        method: 'post',
        data: data
    })
}

// 修改检查项目管理
export function updateCheck_item(data) {
    return request({
        url: '/commondata/check_item',
        method: 'put',
        data: data
    })
}

// 删除检查项目管理
export function delCheck_item(id) {
    return request({
        url: '/commondata/check_item/' + id,
        method: 'delete'
    })
}
