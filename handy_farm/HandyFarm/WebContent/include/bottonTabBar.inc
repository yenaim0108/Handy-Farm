<script>
	$(document).ready(function(){
	    $("#bottomTabBar li").click(function()
	     {
	    	var id = main;
	    	var img = document.getElementByName(id);
	        	var change_img = "../icon/green_" + id + ".png";
	        
	       	img.src = change_img;
	        
	     });
	});
</script>		
	
<footer>
	<ul id="bottomTabBar">
		<li id="main">
			<a href="main.do">
				<img class="d-ib m-x-md" src="../icon/gray_main.png" alt="main" name="main">
			</a>
		</li>
		<li id="calendar">
			<a href="calendar.do">
				<img class="d-ib m-x-md" src="../icon/gray_calendar.png" alt="calendar" name="calendar">
			</a>
		</li>
		<li id="tip">
			<a href="tip.do">
				<img class="d-ib m-x-md" src="../icon/gray_tip.png" alt="Tip" name="tip">
			</a>
		</li>
		<li id="setting">
			<a href="setting.do">
				<img class="d-ib m-x-md" src="../icon/gray_setting.png" alt="setting" name="setting">
			</a>
		</li>
	</ul>
</footer>