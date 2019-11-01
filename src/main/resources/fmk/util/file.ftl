<!DOCTYPE html>
<html lang="en">
<head>
    <title></title>
    <script type="text/javascript">        
		function afterChooseFile(btn){
			var file=btn.files[0];
			document.getElementById("filename").value=file.name;
		}
    </script>
</head>
<body>
<form action="/utils/file/import" id="fileForm" method="post">
	<input type="text" id="filename" /><!-- 因为type=file的样式难改  所以用此来显示样式   -->
	<input type="file" id="file" name="file" style="display:none" onchange="afterChooseFile(this)"/> 
	<button type="button" onclick="file.click()">选择文件</button>
	<button type="button" onclick="">预览</button>
	<button type="button" onclick="if(!filename.value){alert('请先上传图片')}else{fileForm.submit()}">上传</button>
</form>


<form action="/utils/file/import" method="post">
	<input type="file" id="file" name="file" onchange="afterChooseFile(this)"/> 
	<input type="submit" value="123445">
</form>
</body>
</html>