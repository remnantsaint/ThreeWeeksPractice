import request from '@/utils/request'

// 查询学生管理列表
export function listStu(query) {
    return request({
        url: '/system/stu/list',
        method: 'get',
        params: query
    })
}

// 查询学生管理详细
export function getStu(id) {
    return request({
        url: '/system/stu/' + id,
        method: 'get'
    })
}

// 新增学生管理
export function addStu(data) {
    return request({
        url: '/system/stu',
        method: 'post',
        data: data
    })
}

// 修改学生管理
export function updateStu(data) {
    return request({
        url: '/system/stu',
        method: 'put',
        data: data
    })
}

// 删除学生管理
export function delStu(id) {
    return request({
        url: '/system/stu/' + id,
        method: 'delete'
    })
}
