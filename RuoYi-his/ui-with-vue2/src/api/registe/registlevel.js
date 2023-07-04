import request from '@/utils/request'

// 查询挂号级别列表
export function listRegistlevel(query) {
    return request({
        url: '/registe/registlevel/list',
        method: 'get',
        params: query
    })
}

// 查询挂号级别详细
export function getRegistlevel(id) {
    return request({
        url: '/registe/registlevel/' + id,
        method: 'get'
    })
}

// 新增挂号级别
export function addRegistlevel(data) {
    return request({
        url: '/registe/registlevel',
        method: 'post',
        data: data
    })
}

// 修改挂号级别
export function updateRegistlevel(data) {
    return request({
        url: '/registe/registlevel',
        method: 'put',
        data: data
    })
}

// 删除挂号级别
export function delRegistlevel(id) {
    return request({
        url: '/registe/registlevel/' + id,
        method: 'delete'
    })
}
