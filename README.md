##### 下载后运行步骤
1. 点击克隆/下载，复制`ssh`
2. 在本地一个目录下右键打开`git bash`，输入`git clone {鼠标中键粘贴}`
3. 导入数据库`his.sql`，数据库名为`ruoyi`
4. 开启`redis`：在redis目录下运行`redis-server.exe`
5. 修改后端文件`application\src\main\resources\application-druid.yml`，讲数据库名改为`ruoyi`，将密码改成自己的
6. 在前端目录下打开终端，运行`npm install --registry=https://registry.npmmirror.com`
7. 运行后端：`ProjectApplication.java`
8. 运行前端：在前端目录下`npm run dev`
9. 登录不同账号
##### 项目流程
超级管理员添加挂号->门诊医生初诊->挂号管理员缴费->医技医生处理 / 挂号管理员退费->挂号管理员退号

##### 账号密码
超级管理员 
admin admin123

挂号收费员 
bianque 123456

门诊医生 
sunsimiao 123456
huatuo 123456

医技医生
yiji 123456

##### 默认科室id及医生id
* 科室：101消化科  
	- (无医生)
* 科室：102心内科  
	- 101sunsimiao 
	- 102huatuo