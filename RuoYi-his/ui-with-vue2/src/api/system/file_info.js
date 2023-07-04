import request from '@/utils/request'

// 查询文件信息列表
export function listFile_info(query) {
    return request({
        url: '/system/file_info/list',
        method: 'get',
        params: query
    })
}

// 查询文件信息详细
export function getFile_info(fileId) {
    return request({
        url: 'http://localhost:9090/system/file_info/' + fileId,
        method: 'get'
    })
}

// 新增文件信息
export function addFile_info(data) {
    return request({
        url: '/system/file_info',
        method: 'post',
        data: data
    })
}

// 修改文件信息
export function updateFile_info(data) {
    return request({
        url: '/system/file_info',
        method: 'put',
        data: data
    })
}

// 删除文件信息
export function delFile_info(fileId) {
    return request({
        url: '/system/file_info/' + fileId,
        method: 'delete'
    })
}
