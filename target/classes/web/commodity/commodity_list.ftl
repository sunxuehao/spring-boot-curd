<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>首页</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <#include "../common/common.ftl" />
</head>
<body>
<div class="sidebar-navigation hidden-sm hidden-xs">
    <div class="logo">
        <a href="#content">Sun<em>xuehao</em></a>
    </div>
    <nav>
        <ul>
            <li>
                <a href="#commodity">
                    <span class="rect"></span>
                    <span class="circle"></span>
                    列表1
                </a>
            </li>
            <li>
                <a href="#contact">
                    <span class="rect"></span>
                    <span class="circle"></span>
                    列表2
                </a>
            </li>
            <li>
                <a href="#contact2">
                    <span class="rect"></span>
                    <span class="circle"></span>
                    列表3
                </a>
            </li>
        </ul>
    </nav>
</div>
<div id="content" class="page-content">
    <section id="commodity" class="content-section">
        <div class="section-heading">
            <h1>商品<br><em>列表</em></h1>
        </div>
        <div class="section-content">
            <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
                <button type="button" class="btn btn-info" onclick="edit(0);"
                        style="position: relative;float: left;margin-right: 10px;">
                    添加
                </button>
                <button id="selectAll" type="button" class="btn btn-success"
                        style="position: relative;float: left;margin-right: 10px;">
                    全选
                </button>
                <button id="unSelectAll" type="button" class="btn btn-danger"
                        style="position: relative;float: left;margin-right: 10px;">
                    全不选
                </button>
                <button id="selectReverse" type="button" class="btn btn-secondary"
                        style="position: relative;float: left;margin-right: 10px;">
                    反选
                </button>
                <button id="selectReverse" type="button" class="btn btn-warning"
                        style="position: relative;float: left;margin-right: 10px;"
                        onclick="deleteByChoose()">
                    删除选中
                </button>
                <form class="form-inline" style="float: right">
                    <input id="inputForSearch" class="form-control" type="text" placeholder="Search">
                    <button class="btn btn-success" type="button" onclick="searchFor()">Search</button>
                </form>
            </nav>
            <table id="commodity_table" class="table table-striped">
                <tr>
                    <td>
                        <input type="checkbox" name="cb" id="firstCb">
                    </td>
                    <td>ID</td>
                    <td>商品名称</td>
                    <td>商品规格</td>
                    <td>温度</td>
                    <td>价格</td>
                    <td>商品描述</td>
                    <td>操作一</td>
                    <td>操作二</td>
                </tr>
                <#list commodity as item>
                    <tr>
                        <td>
                            <input type="checkbox" name="cb">
                        </td>
                        <td>
                            ${(item.id)!''}
                        </td>
                        <td>
                            ${(item.name)!''}
                        </td>
                        <td>
                            ${(item.standard)!''}
                        </td>
                        <td>
                            ${(item.temperature)!''}
                        </td>
                        <td>
                            ${(item.price)!''}
                        </td>
                        <td>
                            ${(item.description)!''}
                        </td>
                        <td>
                            <button type="button" class="btn btn-warning" onclick="edit(${(item.id)});">
                                修改
                            </button>
                        </td>
                        <td>
                            <button type="button" class="btn btn-danger" onclick="deleteById(${(item.id)!})">
                                删除
                            </button>
                        </td>
                    </tr>
                </#list>
            </table>
            <tfoot>
            <ul class="pagination pagination-lg">
                <!-- 动态长度  -->
                <#if sum%5 == 0>
                    <#assign num = (sum/5)?int>
                <#else>
                    <#assign num = (sum/5)?int + 1>
                </#if>
                <li><a style="user-select: none" onclick="selectByPageNum(
                    <#if pageNum == 1>
                    ${pageNum}
                    <#else>
                            ${pageNum}-1
                    </#if>)">上一页</a></li>
                <#list 1..num as i>
                    <li><a style="user-select: none" onclick="selectByPageNum(${i})">${i}</a></li>
                </#list>
                <li><a style="user-select: none" onclick="selectByPageNum(
                    <#if pageNum == num>
                    ${pageNum}
                    <#else>
                            ${pageNum}+1
                    </#if>)">下一页</a></li>
            </ul>
            </tfoot>
        </div>
    </section>
    <section id="contact" class="content-section">
        <div class="section-heading">
            <h1>中间<br><em>列表</em></h1>
        </div>
        <div style="height: 1000px;width: 200px;background-color: #2b542c">
            <h1 style="font-size: larger;color: white">占位</h1>
        </div>
    </section>
    <section id="contact2" class="content-section">
        <div class="section-heading">
            <h1>底部<br><em>列表</em></h1>
        </div>
        <div style="height: 1000px;width: 200px;background-color: #2b542c">
            <h1 style="font-size: larger;color: white">占位</h1>
        </div>
    </section>
</div>
<script>
    var didScroll;
    var lastScrollTop = 0;
    var delta = 5;
    var navbarHeight = $('header').outerHeight();

    $(window).scroll(function (event) {
        didScroll = true;
    });

    setInterval(function () {
        if (didScroll) {
            hasScrolled();
            didScroll = false;
        }
    }, 250);

    function hasScrolled() {
        var st = $(this).scrollTop();

        if (Math.abs(lastScrollTop - st) <= delta)
            return;

        if (st > lastScrollTop && st > navbarHeight) {
            $('header').removeClass('nav-down').addClass('nav-up');
        } else {
            if (st + $(window).height() < $(document).height()) {
                $('header').removeClass('nav-up').addClass('nav-down');
            }
        }
        lastScrollTop = st;
    }
</script>
<script>

    function edit(id) {
        var title = "新增商品";
        if (id != 0) {
            title = "编辑商品";
        }
        var ob = {
            title: "<label>" + title + "</label>",
            width: "800",
            height: "400",
            url: "findById?id=" + id
        };
        my.open(ob);
    }

    function reload() {
        window.location.reload();
    }

    function selectByPageNum(num) {
        var currentUrl = window.location.href;
        var urlArr = currentUrl.split('?');

        var newUrl = urlArr[0] + "?pageNum=" + String(num);
        window.location.href = newUrl;
    }

    function deleteById(id) {
        my.confirm('提示信息', '是否确认删除?', function () {
            $.get("/commodity/deleteById", {id: id}, function (data) {
                console.log(data);
                if (data.success) {
                    layer.msg("删除成功!",
                        {icon: 6, time: 1000},
                        function () {
                            reload();
                        })
                } else {
                    my.alert('删除失败!');
                }
            });
        })
    }
</script>
<script>
    function deleteByChoose() {
        var table = document.getElementById('commodity_table');//获取table的id标识
        var rowsLength = table.rows.length;//表格总共有多少行
        var idArray = new Array()
        for (var i = 1; i < rowsLength; i++) {//按表的行数进行循环，本例第一行是标题，所以i=1，从第二行开始筛选（从0数起）
            var searchCheckBox = table.rows[i].cells[0].children[0];//取得table行，列的值
            if (searchCheckBox.checked == true) {
                // 如果被选中了
                idArray.push(parseInt(table.rows[i].cells[1].innerHTML));

            }
        }
        console.log(idArray)
        if (idArray.length == 0) {
            my.alert('提示信息', '你没有选中任何数据')
        } else {
            my.confirm('提示信息', '是否确认删除选中的内容?', function () {
                $.ajax({
                    url: "/commodity/deleteByIdArray",
                    type: 'POST',
                    dataType: 'json',
                    // traditional: true,
                    // contentType: "application/json",
                    // async: false,
                    data: {
                        idArray: idArray
                    },
                    success: function (data) {
                        if (data.success) {
                            layer.msg("删除成功!",
                                {icon: 6, time: 1000},
                                function () {
                                    reload();
                                })
                        } else {
                            my.alert('删除失败!');
                        }
                    }
                });
            })
        }
    }

    function searchFor() {
        var input_value = document.getElementById("inputForSearch").value;
        var table = document.getElementById('commodity_table');//获取table的id标识
        var rowsLength = table.rows.length;//表格总共有多少行
        var searchCol = 2;//要搜索的哪一列，这里是第一列，从0开始数起
        if (input_value == "") {
            for (var i = 1; i < rowsLength; i++) {//按表的行数进行循环，本例第一行是标题，所以i=1，从第二行开始筛选（从0数起）
                table.rows[i].style.display = '';//显示行操作，
            }
        } else {
            for (var i = 1; i < rowsLength; i++) {//按表的行数进行循环，本例第一行是标题，所以i=1，从第二行开始筛选（从0数起）
                var searchText = table.rows[i].cells[searchCol].innerHTML;//取得table行，列的值
                //alert(searchText);
                if (searchText.match(input_value) || searchText.toUpperCase().match(input_value.toUpperCase())) {//用match函数进行筛选，如果input的值，即变量 key的值为空，返回的是ture，
                    table.rows[i].style.display = '';//显示行操作，
                } else {
                    table.rows[i].style.display = 'none';//隐藏行操作
                }
            }
        }
    }

    window.onload = function () {
        document.getElementById("selectAll").onclick = function () {
            var checkBoxObject = document.getElementsByName("cb");
            for (var i = 0; i < checkBoxObject.length; i++) {
                checkBoxObject[i].checked = true;
            }
        };

        document.getElementById("unSelectAll").onclick = function () {
            var checkBoxObject = document.getElementsByName("cb");
            for (var i = 0; i < checkBoxObject.length; i++) {
                checkBoxObject[i].checked = false;
            }
        };

        document.getElementById("selectReverse").onclick = function () {
            var checkBoxObject = document.getElementsByName("cb");
            for (var i = 0; i < checkBoxObject.length; i++) {
                if (checkBoxObject[i].id != "firstCb") {
                    checkBoxObject[i].checked = !checkBoxObject[i].checked;
                } else {
                    checkBoxObject[i].checked = false;
                }
            }
        };

        document.getElementById("firstCb").onclick = function () {
            var checkBoxObject = document.getElementsByName("cb");
            for (var i = 0; i < checkBoxObject.length; i++) {
                checkBoxObject[i].checked = this.checked;
            }
        };

        var trs = document.getElementsByTagName("tr");
        for (var i = 0; i < trs.length; i++) {
            trs[i].onmouseover = function () {
                this.className = "over";
            };
            trs[i].onmouseout = function () {
                this.className = "out";
            };
        }
    }

</script>
</body>
</html>