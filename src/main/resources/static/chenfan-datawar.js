(function() {
	Date.prototype.Format = function (fmt) {  
	    var o = {  
	        "M+": this.getMonth() + 1, //月份   
	        "d+": this.getDate(), //日   
	        "H+": this.getHours(), //小时   
	        "m+": this.getMinutes(), //分   
	        "s+": this.getSeconds(), //秒   
	        "q+": Math.floor((this.getMonth() + 3) / 3), //季度   
	        "S": this.getMilliseconds() //毫秒   
	    };  
	    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));  
	    for (var k in o)  
	    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));  
	    return fmt;  
	}
	
//	var totalValue = 0;
//	var chinValue = 0;
//	var otherValue = 0;
//	var total = document.querySelector('.total');
//	var chin = document.querySelector('.chin');
//	var other = document.querySelector('.other');
//    ot = new Odometer({
//        el: total,
//        duration: 1000,
//        format: '(,ddd)',
//        theme: 'default',
//        animation: 'count'
//    });
//    oc = new Odometer({
//        el: chin,
//        duration: 1000,
//        format: '(,ddd)',
//        theme: 'default',
//        animation: 'count'
//    });
//    oo = new Odometer({
//        el: other,
//        duration: 1000,
//        format: '(,ddd)',
//        theme: 'default',
//        animation: 'count'
//    });
    // date : '12/07/2018 23:59:58',
	$('.countdown').downCount({
		date : '12/11/2018 11:59:58',
		offset: +8
	}, function() {
		$(".modal.fade.in").addClass('animated fadeOut').remove();
		setTimeout(() => {
			$(".modal-backdrop.fade.in").addClass('animated fadeOut');
//	    	$.getJSON("/getData", function(data){
//	    		if(data.code != 0){
//	    			return;
//	    		}
//	            ot.update(data.payAmt);
//	            oc.update(data.chin);
//	            oo.update(data.other);
//	    	});
//		    var auto = setInterval(function(){
//		    	$.getJSON("/getData", function(data){
//		    		if(data.code != 0){
//		    			return;
//		    		}
//		            ot.update(data.payAmt);
//		            oc.update(data.chin);
//		            oo.update(data.other);
//		    	});
//		    }, 5000);
		}, 12000);
	});
    
	setInterval(function(){
		var date = new Date().Format('yyyy-MM-dd HH:mm:ss');
		$(".time").text(date)
	},1000);
})();