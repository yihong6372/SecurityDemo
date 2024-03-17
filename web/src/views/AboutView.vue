<template>
  <div class="about">
    <h1>This is an about page</h1>
    <a-button type="primary" @click="myButton">Primary Button</a-button>
    <a-form
        :model="formState"
        name="basic"
        :label-col="{ span: 8 }"
        :wrapper-col="{ span: 16 }"
        autocomplete="off"
        @finish="onFinish"
        @finishFailed="onFinishFailed"
    >
      <a-form-item
          label="Username"
          name="userName"
          :rules="[{ required: true, message: 'Please input your username!' }]"
      >
        <a-input v-model:value="formState.userName"/>
      </a-form-item>

      <a-form-item
          label="Password"
          name="password"
          :rules="[{ required: true, message: 'Please input your password!' }]"
      >
        <a-input v-model:value="formState.password" />
      </a-form-item>

      <a-form-item name="remember" :wrapper-col="{ offset: 8, span: 16 }">
        <a-checkbox :checked="'0'">Remember me</a-checkbox>
      </a-form-item>

      <a-form-item :wrapper-col="{ offset: 8, span: 16 }">
        <a-button type="primary" html-type="fromSubmit" v-on:click="login">Submit</a-button>
      </a-form-item>
    </a-form>
  </div>
</template>

<script lang="ts">
import {defineComponent, onMounted, ref} from "vue";
import {message} from "ant-design-vue";
import axios from "axios";

export default defineComponent(
    {
      name: "AboutView",
      setup() {

        const formState = ref({
          userName: '',
          password: ''
        });

        const myButton = () => {
          message.info("button");
          console.log(formState.value);
          axios.get('http://localhost:8080/test/aa',{
            headers: {
             token: localStorage.getItem('token')
            }
          }).then((response) => {
            console.log(response.data);
          })
        };

        const login = () => {
          message.info("login");
          axios.post('http://localhost:8080/user/login',formState.value).then((response) => {
            console.log(response.data);
            localStorage.setItem('token', response.data.data.token);
            console.log(localStorage.getItem('token'));
          })
        };
        onMounted(() => {
          console.log("onMounted");
          console.log(formState.value);
        });
        return {
          formState,
          myButton,
          login
        }
      }
    });
</script>
