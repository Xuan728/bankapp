<template>
	<view class="content">
		<image class="logo" src="/static/logo.png"></image>
		<view class="text-area">
			<text class="title">{{title}}</text>
		</view>
		<view>
		    <input type="text" v-model="username" placeholder="请输入用户名" />
		    <input type="password" v-model="password" placeholder="请输入密码" />
		    <button @click="login">登录</button>
		  </view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				title: 'Hello',
				users: '',
				username: '',
				password: ''
			}
		},
		onLoad() {

		},
		methods: {
		 getUsers() {
			  uni.request({
				url: '/user/login',
				method: 'POST',
				data: {
				          username: this.username,
				          password: this.password
				},
				header: {
				          'content-type': 'application/json' // 设置请求头为JSON格式
				},
				success: (res) => {
				  // 请求成功，处理返回的数据
				  this.users = res.data;
				  console.log(res.data);
				},
				fail: (err) => {
				  // 请求失败，处理错误信息
				  console.error(err);
				}
			  });
			}
		}
	}
</script>

<style>
	.content {
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
	}

	.logo {
		height: 200rpx;
		width: 200rpx;
		margin-top: 200rpx;
		margin-left: auto;
		margin-right: auto;
		margin-bottom: 50rpx;
	}

	.text-area {
		display: flex;
		justify-content: center;
	}

	.title {
		font-size: 36rpx;
		color: #8f8f94;
	}
</style>
