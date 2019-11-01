<head>
   
</head>
<body>
<form action="/utils/file/import" id="fileForm" method="post" enctype="multipart/form-data">
	<input type="text" id="filename" /><!-- 因为type=file的样式难改  所以用此来显示样式   -->
	<input type="file" id="file" name="file" style="display:none" onchange="filename.value=this.files[0]"/> 
	<button type="button" onclick="file.click()">选择文件</button>
	<button type="button" onclick="">预览</button>
	<button type="button" onclick="if(!filename.value){alert('请先上传图片')}else{fileForm.submit()}">上传</button>
</form>

</body>
</html>