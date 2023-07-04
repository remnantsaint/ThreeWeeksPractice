import request from '@/utils/request'



// 根据患者ID查询
export function getRegisterApplyItems(id) {
    return request({
        url: '/registe/register/queryItemByRegisterId/' + id,
        method: 'get'
    })
}


// 根据患者ID查询
export function fee(data) {
  return request({
    url: '/registe/register/fee',
    method: 'post',
    data:data
  })
}


// 根据患者ID退费
export function refund(data) {
  return request({
    url: '/registe/register/refund',
    method: 'post',
    data:data
  })
}
