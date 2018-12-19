$(document).ready(function () {
    var url = "http://localhost:8080";
    $("div[name='questionDesception']").each(
        function () {
            var id = $(this).attr("id");
            var editor = editormd(id, {
                path: "../editor.md-master/lib/",
                width: "90%",
                autoHeight : true,
                watch: false,
                toolbar: false,
                codeFold: true,
                searchReplace: true,
                readOnly: true,
                lineNumbers: $("#isQuestionCodeRelated").val() === 'true' ? true : false,
                value: (localStorage.mode) ? $("#" + localStorage.mode.replace("text/", "") + "-code").val() : $("#html-code").val(),
                theme: (localStorage.theme) ? localStorage.theme : "default",
                mode: (localStorage.mode) ? localStorage.mode : "text/x-java"
            });
        }
    )

    $("div[name='code']").each(
        function () {
            var id = $(this).attr("id");
            var editor = editormd(id, {
                path: "../editor.md-master/lib/",
                autoFocus: false,
                width: "90%",
                autoHeight : true,
                height: 720,
                watch: true,
                toolbar: true,
                codeFold: false,
                searchReplace: true,
                readOnly: false,
                placeholder: "Enjoy coding!",
                value: (localStorage.mode) ? $("#" + localStorage.mode.replace("text/", "") + "-code").val() : $("#html-code").val(),
                theme: (localStorage.theme) ? localStorage.theme : "default",
                mode: (localStorage.mode) ? localStorage.mode : "text/x-java",
                toolbarIcons: function () {
                    // Or return editormd.toolbarModes[name]; // full, simple, mini
                    // Using "||" set icons align right.
                    return ["undo", "redo", "|", "testIcon"]
                },
                toolbarIconsClass: {
                    testIcon: "fa-play"  // 指定一个FontAawsome的图标类
                },
                toolbarHandlers: {
                    /**
                     * @param {Object}      cm         CodeMirror对象
                     * @param {Object}      icon       图标按钮jQuery元素对象
                     * @param {Object}      cursor     CodeMirror的光标对象，可获取光标所在行和位置
                     * @param {String}      selection  编辑器选中的文本
                     */
                    testIcon: function (cm, icon, cursor, selection) {
                        event.preventDefault();
                        ajaxGet(this, this.id+'-preview-container');
                    }
                }
            });
        }
    )

    // DO GET
    function ajaxGet(editor, id) {
        $.ajax({
            type: "POST",
            url: url + "/compile",
            data: {
                "content": editor.getValue(),
                "id": 10
            },
            dataType: "JSON",
            success: function (data) {
                // fill data to Modal Body
                fillData(data,id);
            },
            error: function (e) {
                fillData(null);
            }
        });
    }

    function fillData(data, id) {
        if (data != null) {
            $('#'+id).html(data.result);
        } else {
        }
    }

})

