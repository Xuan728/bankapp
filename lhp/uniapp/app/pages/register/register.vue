<template>
	<view>
		
		<view style="width: 90%; margin: 200rpx auto;">
			<view style="margin-bottom: 70rpx; font-size: 60rpx; color: orange; text-align: center;">注册</view>
			<view style="margin: 40rpx 0;">
				<uni-easyinput  v-model="form.username"  prefixIcon="person" placeholder="请输入注册用户名"></uni-easyinput>
			</view>
			<view style="margin: 40rpx 0;">
				<uni-easyinput  type="password" v-model="form.password"  prefixIcon="locked" placeholder="请输入注册密码"></uni-easyinput>
			</view>
			<view style="margin: 40rpx 0;">
				<button type="default" @click="register"> 注 册</button>
			</view>
			
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
			register(){
				console.log(this.form);
				const baseUrl = "http://localhost:8080"
				uni.request({
					url: baseUrl+'/user/register',
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
								title: '注册成功'
							})
							uni.setStorageSync('token',data.data);
							//跳转页面
							uni.navigateTo({
								url: '/pages/login/login'
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
