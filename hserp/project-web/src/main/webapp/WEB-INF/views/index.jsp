<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <!-- 引入交互js-->

    <meta charset="UTF-8">
    <meta name=”viewport” content=”width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no″>
    <meta name="Keywords" content="IO"/>
    <meta name="description" content="海思数据中心IO管理系统"/>
    <title>海思数据中心IO管理系统</title>

    <link rel="stylesheet" href="/static/css/style.css">
    <link rel="stylesheet" href="http://at.alicdn.com/t/font_562689_5z3m5awhnbb21emi.css">
    <jsp:include page="base.jsp"/>
</head>
<!--禁止复制，禁止鼠标右键-->
<body>
<!-- 主页面div-->
<div class="system-admin-main">
    <!-- 头部开始 -->
    <div class="header">
        <!-- 头部导航开始 -->
        <div class="system-nav">
            <!-- 头部logo -->
            <div class="system-logo">
                <img src="static/img/icon/icon-headimg.png">海思数据中心IO管理系统 HASS DB CENTER
            </div>
            <!-- 导航右侧菜单开始 -->
            <div class="system-layout-right">
                <ul>
                    <!-- 导航菜单项 -->
                    <!-- <li class="system-layout-item">
                        <a href="javascript:;">
                            <span class="item-hint-info">99</span>
                            <img src="img/icon/icon-remind.png" alt="" class="system-layout-item-nav-img"> 消息</a>
                    </li>
                    <li class="system-layout-item"><a href="javascript:;"><span class="item-hint-point"></span>皮肤</a></li> -->
                    <!-- <li class="system-layout-item"><a href="javascript:;">设置</a></li> -->
                    <li class="system-layout-item">
                        <a href="javascript:;">账号<span class="square-icon"></span></a>
                        <!-- 导航子菜单，可以在每一个菜单项的a标签后面添加 -->
                        <ul class="system-layout-item-child">
                            <li class="system-layout-item-child-li"><a href="javascript:;">安全设置</a></li>
                            <li class="system-layout-item-child-li"><a href="javascript:;">个人中心</a></li>
                            <li class="system-layout-item-child-li"><a href="javascript:;">资料详情</a></li>
                            <li class="system-layout-item-child-li"><a href="javascript:;">子菜单1</a></li>
                            <li class="system-layout-item-child-li"><a href="javascript:;">子菜单2</a></li>
                        </ul>
                    </li>
                    <li class="system-layout-item">
                        <a href="javascript:;"><img src="static/img/icon/icon-exit.png" alt="" class="system-layout-item-nav-img"> 退出</a>
                    </li>
                </ul>
            </div>
        </div>
        <!-- 导航结束 -->
    </div>
    <!-- header结束 -->
    <!-- 左侧边栏导航开始 -->
    <div class="system-side">
        <!-- 导航菜单开关按钮 -->
        <div class="system-side-menu-switch">
            <img src="static/img/icon/icon-more.png">
        </div>
        <!-- 导航菜单项开始 -->
        <div class="side-menu-items">
            <!-- 带文字的导航菜单项 -->
            <div class="side-menu-item">
                <div class="side-menu-item-title"><span class="side-menu-square"></span>行政管理</div>
                <ul>
                    <li><a onclick="createTab({title:'考勤管理',isShowClose:true,url:'http://www.hao123.com'})" href="javascript:;"><i class="iconfont icon-kaoqin"></i>考勤管理</a></li>
                    <li><a onclick="createTab({title:'报销管理',isShowClose:true,url:'http://www.ifeng.com/'})" href="javascript:;"><i class="iconfont icon-baoxiao"></i>报销管理</a></li>
                    <li><a onclick="createTab({title:'公章管理',isShowClose:true,url:'http://www.4399.com/'})" href="javascript:;"><i class="iconfont icon-baoxiao"></i>公章管理</a></li>
                    <li><a onclick="createTab({title:'注册资料管理',isShowClose:true,url:'http://www.cctv.com/'})" href="javascript:;"><i class="iconfont icon-ziliaoguanli"></i>注册资料管理</a></li>

                    <li><a onclick="createTab({title:'会议管理',isShowClose:true,url:'http://www.panda.tv/'})" href="javascript:;"><i class="iconfont icon-huiyi"></i>会议管理</a></li>
                </ul>
            </div>
            <div class="side-menu-item">
                <div class="side-menu-item-title"><span class="side-menu-square"></span>人事管理</div>
                <ul>
                    <li><a onclick="createTab({title:'职员管理',isShowClose:true,url:'human/human'})" href="javascript:;"><i class='iconfont icon-34'></i>职员管理</a></li>
                    <li><a onclick="createTab({title:'部门管理',isShowClose:true,url:'human/dept'})" href="javascript:;"><i class='iconfont icon-yuangongruzhi'></i>部门管理</a></li>
                    <li><a onclick="createTab({title:'储备人才库管理',isShowClose:true,url:'human/human'})" href="javascript:;"><i class='iconfont icon-kehu'></i>储备人才库管理</a></li>
                    <li><a onclick="createTab({title:'离职人员管理',isShowClose:true,url:'http://mil.eastday.com/'})" href="javascript:;"><i class='iconfont icon-yuangonglizhi'></i>离职人员管理</a></li>
                    <li><a onclick="createTab({title:'通讯录管理',isShowClose:true,url:'http://mil.eastday.com/'})" href="javascript:;"><i class='iconfont icon-tongxunlu'></i>通讯录管理</a></li>
                    <li><a onclick="createTab({title:'待遇管理',isShowClose:true,url:'http://mil.eastday.com/'})" href="javascript:;"><i class='iconfont icon-qian'></i>待遇管理</a></li>

                </ul>
            </div>
            <div class="side-menu-item">
                <div class="side-menu-item-title"><span class="side-menu-square"></span>财务管理</div>
                <ul>
                    <li><a onclick="createTab({title:'固定资产管理',isShowClose:true,url:'http://mil.eastday.com/'})" href="javascript:;"><i class='iconfont icon-gudingzichanguanli'></i>固定资产管理</a></li>
                    <li><a onclick="createTab({title:'资质管理',isShowClose:true,url:'http://mil.eastday.com/'})" href="javascript:;"><i class='iconfont icon-zichan'></i>资质管理</a></li>

                    <li><a onclick="createTab({title:'入账管理',isShowClose:true,url:'http://mil.eastday.com/'})" href="javascript:;"><i class='iconfont icon-ruku'></i>入账管理</a></li>

                    <li><a onclick="createTab({title:'出账管理',isShowClose:true,url:'http://mil.eastday.com/'})" href="javascript:;"><i class='iconfont icon-chuku'></i>出账管理</a></li>
                </ul>
            </div>
            <div class="side-menu-item">
                <div class="side-menu-item-title"><span class="side-menu-square"></span>项目管理</div>
                <ul>
                    <li><a onclick="createTab({title:'客户资源管理',isShowClose:true,url:'system/project/getView?viewPage=custom/customer'})" href="javascript:;"><i class='iconfont icon-kehu'></i>客户资源管理</a></li>
                    <li><a onclick="createTab({title:'项目管理',isShowClose:true,url:'system/project/getView?viewPage=project/project'})" href="javascript:;"><i class='iconfont icon-jindu'></i>项目管理</a></li>

                </ul>
            </div>
            <div class="side-menu-item">
                <div class="side-menu-item-title"><span class="side-menu-square"></span>系统设置</div>
                <ul>
                    <li><a onclick="createTab({title:'角色管理',isShowClose:true,url:'http://mil.eastday.com/'})" href="javascript:;"><i class='iconfont icon-jiaoseguanli'></i>角色管理</a></li>
                    <li><a onclick="createTab({title:'账户管理',isShowClose:true,url:'http://mil.eastday.com/'})" href="javascript:;"><i class='iconfont icon-zhanghuguanli'></i>账户管理</a></li>
                </ul>
            </div>
            <div class="side-menu-item">
                <div class="side-menu-item-title"><span class="side-menu-square"></span>评估系统</div>
                <ul>
                    <li><a onclick="createTab({title:'评估中心',isShowClose:true,url:'performance/evaluation'})" href="javascript:;"><i class="iconfont icon-pingjia"></i>评估中心</a></li>

                </ul>
            </div>
        </div>
        <!-- 带文字的导航菜单项结束 -->
        <!-- 关闭后小图标菜单项开始 -->
        <div class="side-menu-small-items">
            <!-- 小图标菜单项 -->
            <div class="side-menu-small-item">
                <div data-toast="行政管理" class="side-menu-small-item-icon"><span class="side-menu-small-square" ></span></div>
                <ul>
                    <li data-toast="考勤管理"><a href="javascript:;"><i class="iconfont icon-kaoqin"></i></a></li>
                    <li data-toast="报销管理"><a href="javascript:;"><i class="iconfont icon-baoxiao "></i></a></li>
                    <li data-toast="公章管理"><a href="javascript:;"><i class="iconfont icon-baoxiao "></i></a></li>
                    <li data-toast="注册资料管理"><a href="javascript:;"><i class="iconfont icon-ziliaoguanli"></i></a></li>
                    <li data-toast="会议管理"><a href="javascript:;"><i class="iconfont icon-huiyi"></i></a></li>
                </ul>
            </div>
            <div class="side-menu-small-item">
                <div data-toast="人事管理" class="side-menu-small-item-icon"><span class="side-menu-small-square" ></span></div>
                <ul>
                    <li data-toast="部门管理"><a href="javascript:;"><i class='iconfont icon-yuangongruzhi'></i></a></li>
                    <li data-toast="职员管理"><a href="javascript:;"><i class='iconfont icon-34'></i></a></li>
                    <li data-toast="储备人才库管理"><a href="javascript:;"><i class='iconfont icon-kehu'></i></a></li>
                    <li data-toast="离职人员管理"><a href="javascript:;"><i class='iconfont icon-yuangonglizhi'></i></a></li>
                    <li data-toast="通讯录管理"><a href="javascript:;"><i class='iconfont icon-tongxunlu'></i></a></li>
                    <li data-toast="待遇管理"><a href="javascript:;"><i class='iconfont icon-qian'></i></a></li>
                </ul>
            </div>
            <div class="side-menu-small-item">
                <div data-toast="财务管理" class="side-menu-small-item-icon"><span class="side-menu-small-square" ></span></div>
                <ul>
                    <li data-toast="固定资产管理"><a href="javascript:;"><i class='iconfont icon-gudingzichanguanli'></i></a></li>
                    <li data-toast="资质管理"><a href="javascript:;"><i class='iconfont icon-zichan'></i></a></li>
                    <li data-toast="入账管理"><a href="javascript:;"><i class='iconfont icon-ruku'></i></a></li>
                    <li data-toast="出账管理"><a href="javascript:;"><i class='iconfont icon-chuku'></i></a></li>
                </ul>
            </div>
            <div class="side-menu-small-item">
                <div data-toast="项目管理" class="side-menu-small-item-icon"><span class="side-menu-small-square" ></span></div>
                <ul>
                    <li data-toast="客户资源管理"><a href="javascript:;"><i class='iconfont icon-kehu'></i></a></li>
                    <li data-toast="项目管理"><a href="javascript:;"><i class='iconfont icon-jindu'></i></a></li>

                </ul>
            </div>
            <div class="side-menu-small-item">
                <div data-toast="系统设置" class="side-menu-small-item-icon"><span class="side-menu-small-square"></span></div>
                <ul>
                    <li data-toast="角色管理"><a href="javascript:;"><i class='iconfont icon-jiaoseguanli'></i></a></li>
                    <li data-toast="账户管理"><a href="javascript:;"><i class='iconfont icon-zhanghuguanli'></i></a></li>
                </ul>
            </div>
            <div class="side-menu-small-item">
                <div data-toast="评估系统" class="side-menu-small-item-icon"><span class="side-menu-small-square"></span></div>
                <ul>
                    <li data-toast="评估中心"><a href="javascript:;"><i class="iconfont icon-pingjia"></i></a></li>
                </ul>
            </div>
        </div>
        <!-- 小图标导航菜单项结束 -->
    </div>
    <!-- 侧边栏导航结束 -->
    <!-- 主面板开始 -->
    <div class="system-content">
        <!-- tab选项卡开始 -->
        <div class="system-tab">
            <!-- tab选项卡导航开始 -->
            <div class="system-tab-nav">
                <ul>
                    <!-- 导航项 -->
                    <li class="system-tab-nav-item system-tab-nav-item-selected" data-name="首页">首页</li>
                </ul>
            </div>
            <!-- 导航项具体内容 -->
            <div class="system-content-iframe">
                <div class="system-content-show">
                    首页
                </div>
            </div>
        </div>
    </div>
    <!-- footer开始 -->
    <div class="system-footer">
        <span>版权所有©合肥海思数据分析有限公司</span>
    </div>
    <!-- footer结束 -->
</div>
</body>
</html>