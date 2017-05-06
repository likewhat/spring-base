<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<%@include file="../includes/header.jsp" %>


<div class="ui text container">
    <h1>About</h1>

    <div id="editor-about" contenteditable="false">
        <h3 style="color:#aaa; font-style:italic">hhhhhh</h3>

        <p>&nbsp;</p>

        <p>&nbsp;</p>

        <p>&nbsp;</p>

        <p><em>hhhh</em></p>

        <p>&nbsp;</p>

    </div>


    <script>
        var editorIDName = "editor-about";
        $(document).ready(function () {

            $(".start-edit").click(function () {
                CKEDITOR.disableAutoInline = true;
                var editor = CKEDITOR.inline(editorIDName, {
                    startupFocus: true,
                    autoGrow_onStartup: true
                });
                $(".start-edit").hide();
                $(".end-edit").show();
                $("#" + editorIDName).attr("contenteditable", "true");
            });

            $(".end-edit").click(function () {
                if (CKEDITOR.instances[editorIDName]) {
                    var json_data = {
                        csrf_token: "1485685574##1ddea41cefb6407a42494b921b8f9f7dc75034d0",
                        editor_name: "about",
                        edit_data: CKEDITOR.instances[editorIDName].getData(),
                    };
                    $.post("/admin/_update_editor_contents", json_data);
                    CKEDITOR.instances[editorIDName].destroy();
                }
                $(".end-edit").hide();
                $(".start-edit").show();
                $("#" + editorIDName).attr("contenteditable", "false");
            });
        });
    </script>

</div>

<script src="/static/ckeditor/ckeditor.js"></script>


</body>
</html>