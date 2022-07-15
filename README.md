# Fibo Tracking
斐波那契
FiboTracking - 帮助用户解决传统数据分析中数据割裂、用户身份难以识别统一、数据众多却难以利用的问题，通过ID-MAPPING技术，为用户打通数据孤岛，构建客户360度全景画像，提供事件分析、留存分析、漏斗分析等功能，深度赋能营销部门进行高效决策。；


### 代码结构
h5-cdp---分析中心前端代码

jar-data-cdp-project---分析中心后端代码

jar-data-flink-analysis---flink消费kafka将数据插入至clickhouse

jar-data-flume-interceptor---flume拦截器，解析flume采集的nginx日志

### 技术架构

SDK埋点SDK采用神策数据开源的SDK，采集行为数据来源终端包括iOS、安卓、Web、H5、微信小程序、支付宝小程序等。
埋点采集到的数据通过JSON数据以HTTP POST方式提交到服务端API。
服务端API由数据接入系统组成，采用Nginx来接收通过 API 发送的数据，
并且将之写到日志文件上。使用Nginx实现高可靠性与高可扩展性。 
对于Nginx打印到文件的日志，会由Flume的 Source 模块来实时读取Nginx日志，
并由Channel模块进行数据处理，最终通过Sink模块将处理结果发布到 Kafka中。使用Flink消费Kafka将数据插入到Clickhouse中

<img src="https://raw.iqiq.io/FiboAI/FiboTracking/main/image/server.png">

### 数据分析

<img src="https://raw.iqiq.io/FiboAI/FiboTracking/main/image/index.png">

#### 1)事件分析

事件，是追踪或记录的用户行为或业务过程。举例来说，一个电商产品可能包含如下事件：用户注册、浏览商品、添加购物车、支付订单等。
事件分析，是指基于事件的指标统计、属性分组、条件筛选等功能的查询分析。

<img src="https://raw.iqiq.io/FiboAI/FiboTracking/main/image/event.png">

#### 2)漏斗分析

漏斗模型主要用于分析一个多步骤过程中每一步的转化与流失情况。

举例来说，用户购买商品的完整流程可能包含以下步骤：

<b>浏览商品 - 添加购物车- 点击付款 完成付款</b>

可以将如上流程设置为一个漏斗，分析整体的转化情况

<img src="https://raw.iqiq.io/FiboAI/FiboTracking/main/image/funnel.png">


#### 3)留存分析

留存分析是一种用来分析用户参与情况/活跃程度的分析模型，考查进行初始行为后的用户中，有多少人会进行后续行为。这是衡量产品对用户价值高低的重要指标。

<img src="https://raw.iqiq.io/FiboAI/FiboTracking/main/image/Keep.png">

### 4)画像分析

暂无

### 5)元数据

元数据管理是系统中数据治理体系的一个模块，是统一管理系统所追踪数据元信息的地方。比如，修改事件的显示名、修改事件属性的显示名、修改事件属性的单位、修改用户属性的显示名等。

### 6)平台管理

管理系统的账户及角色权限。

### 7)数据回传（后端-暂未实现）

对接巨量引擎匹配数据回传策略，进行回传数据。

### 加官方微信号，进开源交流群

<img width="300px" src="https://raw.iqiq.io/FiboAI/FiboTracking/main/image/qrcode.jpg">
