<template>
	<view class="content">
		<image class="logo" src="/static/logo.png"></image>
		<view class="text-area">
<<<<<<< HEAD
			<text style="font-size: 40rpx;">{{title}}</text>
		</view>
		<view class="uni-form-item" style="margin-top: 200rpx;">
			<view class="title">手机号</view>
			<input class="uni-input" @input="inputmobile" placeholder="请输入手机号" />
		</view>
		<view class="uni-form-item">
			<view class="title">密码</view>
			<input class="uni-input" @input="inputpassword" password type="text" placeholder="请输入密码" />
			<view class="uni-button"></view>
=======
			<text class="title">{{title}}</text>
>>>>>>> 10a6fe85fd93c517e8b3790412e5ff4666c9e9b7
		</view>
	</view>
</template>

<script>
<<<<<<< HEAD
	import axios from "../../node_modules/axios"
	export default {
		data() {
			return {
				title: '用户您好!',
				mobile:"",
				password:"",
=======
	export default {
		data() {
			return {
				title: 'Hello'
>>>>>>> 10a6fe85fd93c517e8b3790412e5ff4666c9e9b7
			}
		},
		onLoad() {

		},
		methods: {
<<<<<<< HEAD
			 inputmobile: function(event) {
			            this.mobile = event.target.value
			        },
			inputpassword: function(event) {
			           this.password = event.target.value
			       },		
					
		}
	}
	axios.post("http://localhost:8081/user/login",mobile,password).then((res) => {
	        console.log(res);
	})
=======

		}
	}
>>>>>>> 10a6fe85fd93c517e8b3790412e5ff4666c9e9b7
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
<<<<<<< HEAD
		width: 20%;
	}
		
	.uni-form-item {
		display: flex;
		flex-direction: row;
		margin:10rpx 10rpx;
	}
	
	.uni-input{
		margin: 0 0 0 60rpx;
		background-color: aliceblue;
		padding: 3rpx 30rpx;
=======
>>>>>>> 10a6fe85fd93c517e8b3790412e5ff4666c9e9b7
	}
</style>
