<%--
	* @author 임예나
	* email : yenaim0108@gmail.com
 --%>

<script>
	$(document).ready(function(){
		i = sessionStorage.getItem("current");
		
		if(i != null) {
			document.getElementsByName("page")[0].setAttribute("src", "../icon/gray_main.png");
			
			var imgTag = document.getElementsByName("page")[i];	
			var current = imgTag.getAttribute("value");
			
			imgTag.setAttribute("src", "../icon/green_" + current + ".png");
		}
	});
	
	function sendPage(i) {
		var current = document.getElementsByName("page")[i].getAttribute("value");
		
		location.href = current + ".do?" + i;
		sessionStorage.setItem("current", i)
	}
</script>		
	
<footer id="bottomTabBar">
	<ul>
		<li class="d-ib">
			<a onclick="sendPage(0);">
				<img class="d-ib m-x-md" src="../icon/green_main.png" alt="main" name="page" value="main">
			</a>
		</li>
		<li class="d-ib">
			<a onclick="sendPage(1);">
				<img class="d-ib m-x-md" src="../icon/gray_calendar.png" alt="calendar" name="page" value="calendar">
			</a>
		</li>
		<li class="d-ib">
			<a onclick="sendPage(2);">
				<img class="d-ib m-x-md" src="../icon/gray_tip.png" alt="Tip" name="page" value="tip">
			</a>
		</li>
		<li class="d-ib">
			<a onclick="sendPage(3);">
				<img class="d-ib m-x-md" src="../icon/gray_setting.png" alt="setting" name="page" value="setting">
			</a>
		</li>
	</ul>
</footer>