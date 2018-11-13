<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="http://getbootstrap.com/favicon.ico">

    <title>Signin Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/css/demo.css" rel="stylesheet">

    <script src="/js/jquery.min.js"></script>
    <script src="/js/jquery.form.js"></script>
</head>
<body class="text-center">
<form name="uploadForm" id="uploadForm" method="post">
    <!-- COMPONENT START -->
    <div class="form-group">
        <div class="input-group input-file" name="file">
            <input type="text" class="form-control" placeholder='Choose a file...'/>
            <span class="input-group-btn">
        		<button class="btn btn-default btn-choose" type="button">Choose</button>
    		</span>
        </div>
        <div class="input-group input-file" name="file2">
            <input type="text" class="form-control" placeholder='Choose a file...2'/>
            <span class="input-group-btn">
        		<button class="btn btn-default btn-choose" type="button">Choose</button>
    		</span>
        </div>
        <div class="input-group input-file" name="file3">
            <input type="text" class="form-control" placeholder='Choose a file...3'/>
            <span class="input-group-btn">
        		<button class="btn btn-default btn-choose" type="button">Choose</button>
    		</span>
        </div>
    </div>
    <!-- COMPONENT END -->
    <div class="form-group">
        <button type="button" id="submitBtn" class="btn btn-primary pull-right">Submit</button>
        <button type="reset" class="btn btn-danger">Reset</button>
    </div>
</form>
</body>

<script>
    function bs_input_file() {
        $(".input-file").before(
                function () {
                    if (!$(this).prev().hasClass('input-ghost')) {
                        var element = $("<input type='file' class='input-ghost' style='visibility:hidden; height:0'>");
                        element.attr("name", $(this).attr("name"));
                        element.change(function () {
                            element.next(element).find('input').val((element.val()).split('\\').pop());
                        });
                        $(this).find("button.btn-choose").click(function () {
                            element.click();
                        });
                        $(this).find("button.btn-reset").click(function () {
                            element.val(null);
                            $(this).parents(".input-file").find('input').val('');
                        });
                        $(this).find('input').css("cursor", "pointer");
                        $(this).find('input').mousedown(function () {
                            $(this).parents('.input-file').prev().click();
                            return false;
                        });
                        return element;
                    }
                }
        );
    }

    $(function () {
        bs_input_file();
        $('#submitBtn').click(function () {
            $('#uploadForm').ajaxForm({
                url: '/functional/upload',
                type: 'POST',
                dataType: 'text',
                enctype: 'multipart/form-data',
                contentType: 'multipart/form-data; charset=UTF-8',
                success: function (data) {
                    alert(data);
                },
                error: function (request, status, error) {
                    alert(error);
                }
            }).submit();
        });
    });
</script>

</html>
