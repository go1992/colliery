 **基于layui的树表格-treeGrid** 



 **效果图：** 

![输入图片说明](https://gitee.com/uploads/images/2018/0316/100352_d1ed2454_1613602.jpeg "2529912_1520831170866_56726.jpg")


 **调用例子：** 
```
<body class="layui-layout-body">
<table class="layui-hidden" id="treeTable" lay-filter="treeTable"></table>
<script>
    layui.use(['element', 'layer', 'form', 'upload', 'treeGrid'], function () {
        var treeGrid = layui.treeGrid; //很重要
        var treeTable =treeGrid.render({
            elem: '#treeTable'
            ,url:'data.json'
            ,cellMinWidth: 100
            ,treeId:'id'//树形id字段名称
            ,treeUpId:'pId'//树形父id字段名称
            ,treeShowName:'name'//以树形式显示的字段
            ,cols: [[
                {field:'name', edit:'text',width:'400', title: '水果'}
                ,{field:'id', edit:'text',width:'100', title: 'id'}
                ,{field:'pId', edit:'text',width:'100', title: 'pId'}
            ]]
            ,page:false
        });
    });
</script>


{
    "msg":"","code":0,
    "data":[
        {"id":"1", "pId":"0", "name":"水果"},
        {"id":"101", "pId":"1", "name":"苹果"},
        {"id":"102", "pId":"1", "name":"香蕉"},
        {"id":"103", "pId":"1", "name":"梨"},
        {"id":"10101", "pId":"101", "name":"红富士苹果"},
        {"id":"10102", "pId":"101", "name":"红星苹果"},
        {"id":"10103", "pId":"101", "name":"嘎拉"},
        {"id":"10104", "pId":"101", "name":"桑萨"},
        {"id":"10201", "pId":"102", "name":"千层蕉"},
        {"id":"10202", "pId":"102", "name":"仙人蕉"},
        {"id":"10203", "pId":"102", "name":"吕宋蕉"}
    ]
    ,"count":29,"is":true,"tip":"操作成功！"
}





 **使用说明：** 

请自行下载web目录，layui配置文件需要修改

在layAll.js中增加调用
 ```
//内置模块
  ,modules = {
    ,treeGrid: 'modules/treeGrid'//增加此行
  };
```


