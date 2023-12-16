<template>
	<view>
		<view>
			<view style="width: 90%; margin-top: 0rpx auto; ">
				<view style="margin: 0rpx; font-size: 60rpx; color: black; text-align: center;">安全信息</view>
				<uni-icons type="fire-filled" size="26" />
				<view style="margin: 40rpx 0;">
					<uni-easyinput  type="password" v-model="form.used_word"  prefixIcon="person" placeholder="请输入原来密码"></uni-easyinput>
				</view>
				<view style="margin: 40rpx 0;">
					<uni-easyinput  type="password" v-model="form.new_password"  prefixIcon="locked" placeholder="请输入新密码"></uni-easyinput>
				</view>
				<view style="margin: 40rpx 0;">
					<uni-easyinput  type="password" v-model="form.re_new_password"  prefixIcon="locked" placeholder="请再次输入新密码"></uni-easyinput>
				</view>
				<view style="margin: 40rpx 0;">
					<button type="primary" @click="update"> 确认 修改</button>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				form: {
					used_word: new String(),
					new_password: new String(),
					re_new_password:new String()
				}
			}
		},
		methods: {
			update(){
				console.log(this.form);
				const baseUrl = "http://localhost:8080"
				uni.request({
					url: baseUrl+'/user/updatePwd',
					method:"PATCH",
					data:JSON.stringify({old_pwd:this.form.used_word,
					new_pwd:this.form.new_password,
					re_pwd:this.form.re_new_password,
					 "account_type": 1 
					}),
					header:{
			            'content-type': 'application/json ',
						//"content-type": "application/json"
						//'content-type': 'application/x-www-form-urlencoded ',
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
								title: '修改成功'
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
