<template>
	<view style="width: 90%; margin: 200rpx auto;">
		<view style="margin-bottom: 70rpx; font-size: 60rpx; color: royalblue; text-align: center;">内容</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				
			}
		},
		onLoad() {
				const baseUrl = "http://localhost:8080"
				uni.request({
					url: baseUrl+'/user/userInfo',
					method:"GET",
					/*data:{username:this.form.username,
					password:this.form.password},*/
					header:{
			            //'content-type': 'application/x-www-form-urlencoded ',
						//"content-type": "application/json"
						//"multipart/form-data"
						Authorization:uni.getStorageSync("user")
			        },
					success: (res)=>{
						console.log(res)
						const data = res.data
						if(data.code !== 0){
							uni.showToast({
								icon:"none",
								title: data.message
							})
						}else{
							uni.showToast({
								title: '访问成功'
							})	
						}
					}
					
				})
			},
		methods: {
			
		}
	}
</script>

<style>

</style>
