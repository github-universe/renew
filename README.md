# renew
A machine learning project to predict and improve renewal rate of SaaS production

起项目，java机器学习， python数据清洗，前端项目  
定数据的特征

佳音，什么样的套餐续约率最高

问下 BI 续约率， 数据从哪儿来  
Gain sights 他是比较死的，他有个nps调研问卷，花人力成本来获得续约可能，我们这个是0成本判断是否续约，提高续约。减少开发，开发功能点不一定提高续约和销售成本  
Gross team 改界面也不完全有用  

数据特征可用字段  
公司账号数， 公司ip段（0，1），时区（分片区，中国， 欧洲，美洲）， 注册数上限，实际注册数， 同时登录数， 登录次数， 踢出数（公司下所有账号的被踢次数），状态（0，1 有一个账号是活跃账号）， 
Package/role（接下来我们卖package，老数据是role， 知道了role来设置package，把老的销售模式映射到新的销售模式，取top20 的角色）， 
（席位，optional），
 销售（名字）， 账号类型（普通，IP注册，IP匿名）， 独立规则（独立规则账号求和）， cs数据统计（按年，每个公司单独计算）  
why， 做， 迭代  

基于role 来分析  

使用统计数据来分析，按月来进行统计  
按年取登录，导出总量，试用账号数据排除，清洗掉  

为公司提供模型， 为公司预测续约  



已经训练好模型， 给指定公司得出是否续约的值  
合同后使用一段时间，计算当下这个公司的续约可能性  
公司健康度，可以参考gainsights健康度  

Weka，以及其他模型  
做成的样子  
使用数据展现， 某些指标没有达到正常水平，每个参数的结果  
加载30秒再出结果  
先查询使用数据，然后得出结论是否续约，  
统计摸个角色的续约率  
本来得出结果是否， 手动修改某些值，把这个结果变成是  
我们建议需要做什么操作来提高续签率  
1.搜索，续约率预测  
2.每个role的续约率  
寻找市面上的产品或工具， 我们这个是定制化的  


#### 说明 

test 里面的 HorseColicDataMinTest.java 是通过weka客户端训练得到模型后进行java代码测试  

WekaClient类里面需要实现传入参数获取预测值


#### APIs
##### Get Company mappings
curl "http://localhost:8888/company/mappings"

##### Get Company Base
curl "http://localhost:8888/company/base/fe1bb88fe69a42f1a5e3c39b5ee62f72"

##### Get Company Statistics
curl "http://localhost:8888/company/statistics/11d8ec2b2fac461fa4aeacc24c478e7a"


###


### weka start 
java -Xmx1000M -cp weka-3-9-3/weka.jar:/home/toryzhou/Program/weka-3-9-3/mysql-connector-java-8.0.16.jar weka.gui.GUIChooser  

https://cloud.tencent.com/developer/article/1384091