import request from '@/utils/request'

// 查询单表管理列表
export function listTest(query) {
    return request({
        url: '/system/test/list',
        method: 'get',
        params: query
    })
}

// 查询单表管理详细
export function getTest(id) {
    return request({
        url: '/system/test/' + id,
        method: 'get'
    })
}

// 新增单表管理
export function addTest(data) {
    return request({
        url: '/system/test',
        method: 'post',
        data: data
    })
}

// 修改单表管理
export function updateTest(data) {
    return request({
        url: '/system/test',
        method: 'put',
        data: data
    })
}

// 删除单表管理
export function delTest(id) {
    return request({
        url: '/system/test/' + id,
        method: 'delete'
    })
}
