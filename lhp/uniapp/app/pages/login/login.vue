<template>
	<view>
		
		<view style="width: 90%; margin: 200rpx auto;">
			<view style="margin-bottom: 70rpx; font-size: 60rpx; color: royalblue; text-align: center;">登录</view>
			<view style="margin: 40rpx 0;">
				<uni-easyinput  v-model="form.username"  prefixIcon="person" placeholder="请输入登录用户名"></uni-easyinput>
			</view>
			<view style="margin: 40rpx 0;">
				<uni-easyinput  type="password" v-model="form.password"  prefixIcon="locked" placeholder="请输入登录密码"></uni-easyinput>
			</view>
			<view style="margin: 40rpx 0;">
				<button type="primary" @click="login"> 登 录</button>
			</view>
			<navigator style="margin: 40rpx 0; color: bisque;" url="/pages/register/register" >无账号?前往注册</navigator>
		</view>
	</view>
</template>

<script>
	
	export default {
		data() {
			return {
				form: {
					username: new String(),
					password: new String()
				}
				
			}
		},
		methods: {
			login(){
				console.log(this.form);
				const baseUrl = "http://localhost:8080"
				uni.request({
					url: baseUrl+'/user/login',
					method:"POST",
					data:{username:this.form.username,
					password:this.form.password},
					header:{
                        'content-type': 'application/x-www-form-urlencoded ',
						//"content-type": "application/json"
						//"multipart/form-data"
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
								title: '登录成功'
							})
							uni.setStorageSync('user',data.data);
							//跳转页面
							uni.navigateTo({
								url: '/pages/setting/setting'
							})
							//跳转tatbar页面
							/*uni.switchTab({
								url: '/pages/index/index'
							})*/
						}
					}
					
				})
			}
		}
	}
</script>

<style>

</style>
