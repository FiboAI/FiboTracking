-- tracking.events definition

CREATE TABLE tracking.events
(

    `distinct_id` String COMMENT '登录前为设备ID，登录后为登录ID',

    `type` String COMMENT '事件类型',

    `event` String COMMENT '事件名称',

    `$lib` String COMMENT '神策sdk驱动包名',

    `$lib_version` String COMMENT '神策sdk驱动包版本',

    `$screen_height` Float64 COMMENT '屏幕高度',

    `$screen_width` Float64 COMMENT '屏幕宽度',

    `$latest_referrer` String COMMENT '最近一次站外地址',

    `$latest_referrer_host` String COMMENT '最近一次站外前向域名',

    `$latest_traffic_source_type` String COMMENT '最近一次站外流量来源类型',

    `$latest_search_keyword` String COMMENT '站外搜索引擎关键词',

    `$referrer` String COMMENT '前向地址',

    `$referrer_host` String COMMENT '前向域名',

    `$url` String COMMENT '页面URL路径',

    `$url_path` String COMMENT '页面路径',

    `$title` String COMMENT '页面title',

    `$is_first_day` String COMMENT '是否是首日',

    `$is_first_time` String COMMENT '是否是首次',

    `$event_duration` Float64 COMMENT '停留时长',

    `$element_id` String COMMENT '元素 ID',

    `$element_content` String COMMENT '元素内容',

    `$element_name` String COMMENT '元素名字',

    `$element_class_name` String COMMENT '元素样式名',

    `$element_type` String COMMENT '元素类型',

    `$element_selector` String COMMENT '元素选择器',

    `$element_target_url` String COMMENT '元素链接地址',

    `$time` DateTime64(3) COMMENT '客户端时间',

    `server_time` DateTime64(3) COMMENT '数据入库时间',

    `channel` String COMMENT '渠道',

    `channel_type` String COMMENT '渠道业务类型',

    `$page_x` String COMMENT '点击位置距网页顶部距离',

    `$page_y` String COMMENT '点击位置距网页左侧距离',

    `commodity_detail_souce` String COMMENT '模块来源',

    `commodity_id` String COMMENT '商品ID',

    `commodity_name` String COMMENT '商品名称',

    `commodity_tag` String COMMENT '商品标签',

    `first_commodity` String COMMENT '商品一级分类',

    `second_commodity` String COMMENT '商品二级分类',

    `original_price` Float64 COMMENT '商品现价',

    `present_price` Float64 COMMENT '商品现价',

    `discount_price` Float64 COMMENT '优惠金额',

    `quantity_unit` String COMMENT '数量单位',

    `monetary_unit` String COMMENT '货币单位',

    `store_id` String COMMENT '门店ID',

    `store_name` String COMMENT '门店名称',

    `supplier_id` String COMMENT '供应商ID',

    `supplier_name` String COMMENT '供应商名称',

    `service_type` String COMMENT '业务类型',

    `get_type` String COMMENT '获取类型',

    `is_success` String COMMENT '是否成功',

    `fail_reason` String COMMENT '失败原因',

    `account` String COMMENT '账号',

    `login_method` String COMMENT '登录方式',

    `is_quick_login` String COMMENT '是否快捷登录',

    `commodity_size` String COMMENT '事件名称',

    `commodity_colour` String COMMENT '颜色',

    `commodity_quantity` Int16 COMMENT '商品数量',

    `viewpoint_height` String COMMENT '视区距顶部位置',

    `entrance` String COMMENT '入口',

    `order_id` String COMMENT '订单id',

    `order_amount` Float64 COMMENT '订单金额',

    `order_actual_amount` Float64 COMMENT '订单实付金额',

    `receiver_name` String COMMENT '收货人姓名',

    `receiver_province` String COMMENT '收货人省份',

    `receiver_city` String COMMENT '收货人城市',

    `receiver_area` String COMMENT '收货人地区',

    `receiver_address` String COMMENT '收货地址',

    `transportation_costs` Float64 COMMENT '运费',

    `discount_name` String COMMENT '优惠券名称',

    `discount_amount` Float64 COMMENT '优惠券金额',

    `is_use_discount` String COMMENT '是否使用优惠券',

    `if_use_integral` String COMMENT '是否使用积分',

    `number_of_integral` Float64 COMMENT '积分数量',

    `integral_discount_amount` Float64 COMMENT '积分优惠金额',

    `is_cash_back` String COMMENT '是否有返现金额',

    `cash_back_amount` Float64 COMMENT '返现金额',

    `cash_back_name` String COMMENT '返现渠道名称',

    `commodity_discount_amount` Float64 COMMENT '商品优惠金额',

    `total_price_of_commodity` Float64 COMMENT '商品总价',

    `payment_method` String COMMENT '支付方式',

    `delivery_method` String COMMENT '配送方式',

    `page_type` String COMMENT '页面类型',

    `banner_belong_area` String COMMENT 'banner版区',

    `banner_type` String COMMENT 'banner类型',

    `banner_name` String COMMENT 'banner名称',

    `banner_id` String COMMENT 'banner_id',

    `url` String COMMENT '跳转链接',

    `banner_rank` String COMMENT 'banner排序',

    `project` String COMMENT '项目名称',

    `register_method` String COMMENT '注册方式',

    `platform_type` String COMMENT '终端来源标识',

    `anonymous_id` String COMMENT ' 匿名 ID ',

    `login_id` String COMMENT '登录ID',

    `$app_id` String COMMENT '小程序的 AppID',

    `$model` String COMMENT '设备型号',

    `$manufacturer` String COMMENT ' 设备制造商',

    `$os` String COMMENT '系统',

    `$os_version` String COMMENT '系统版本',

    `$is_login_id` String COMMENT '是否是登录ID',

    `$longitude` Float64 COMMENT '经度',

    `$latitude` Float64 COMMENT '纬度',

    `$ip` String COMMENT 'IP',

    `$country` String COMMENT '国家',

    `$province` String COMMENT '省份',

    `$city` String COMMENT '城市',

    `$network_type` String COMMENT '网络类型',

    `$browser` String COMMENT '浏览器名称，由 UA 解析得到',

    `$browser_version` String COMMENT '浏览器版本，由 UA 解析得到',

    `$scene` String COMMENT '启动场景',

    `$latest_utm_source` String COMMENT '最近一次广告系列来源',

    `$latest_utm_medium` String COMMENT '最近一次广告系列媒介',

    `$latest_utm_term` String COMMENT '最近一次广告系列字词',

    `$latest_utm_content` String COMMENT '最近一次广告系列内容',

    `$latest_utm_campaign` String COMMENT '最近一次广告系列名称',

    `$latest_scene` String COMMENT '最近一次场景值',

    `$url_query` String COMMENT '页面参数',

    `$timezone_offset` String COMMENT '时区偏移量',

    `$brand` String COMMENT '设备品牌',

    `$mp_client_app_version` String COMMENT '程序客户端应用版本号',

    `$mp_client_basic_library_version` String COMMENT '小程序客户端基础库版本号',

    `$geo_coordinate_system` String COMMENT '坐标系',

    `$app_version` String COMMENT 'app版本',

    `$wifi` String COMMENT '是否 Wifi',

    `$carrier` String COMMENT '运营商名称',

    `$device_id` String COMMENT '设备ID',

    `$app_name` String COMMENT '应用名称',

    `$screen_orientation` String COMMENT '屏幕方向',

    `user_id` String COMMENT 'fibo用户ID',

    `$identity_login_id` String COMMENT '登录id',

    `flume_time` DateTime64(3) COMMENT 'flume分析nginx日志时间',

    `_track_id` String COMMENT '上报数据ID'
)
    ENGINE = MergeTree
        ORDER BY (event,
                  flume_time)
        SETTINGS index_granularity = 8192;


-- tracking.users definition

CREATE TABLE tracking.users
(

    `user_id` String COMMENT 'Fibo埋点用户ID',

    `first_id` String COMMENT '设备ID，也可能是用户登录ID，一个手机登录多个账号时，仅第一个账号对应的是设备ID，后续账号此字段都存的是用户登录ID',

    `second_id` String COMMENT '用户登录ID',

    `$device_id_list` String COMMENT '该账号登录过的设备ID',

    `project` String,

    `server_time` DateTime,

    `status` Bool DEFAULT 0
)
    ENGINE = MergeTree
    ORDER BY user_id
    SETTINGS index_granularity = 8192;
