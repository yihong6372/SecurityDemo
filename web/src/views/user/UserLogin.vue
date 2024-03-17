<template>
  <div class="main">
    <a-form
        :model="formState"
        name="user-login"
        class="user-layout-login"
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
        <a-input-password v-model:value="formState.password"/>
      </a-form-item>

      <a-form-item name="remember" :wrapper-col="{ offset: 8, span: 16 }">
        <a-checkbox v-model:checked="formState.remember">Remember me</a-checkbox>
      </a-form-item>

      <a-form-item :wrapper-col="{ offset: 8, span: 16 }">
        <a-button
            type="primary"
            class="login-button"
            html-type="submit">Submit
        </a-button>
      </a-form-item>
    </a-form>
  </div>
</template>

<script lang="ts" setup>
import {defineComponent, reactive} from "vue";
import {message} from "ant-design-vue";
import axios from "axios";

console.log('UserView setup');

interface FormState {
  userName: string;
  password: string;
  remember: boolean;
}

const formState = reactive<FormState>({
  userName: '',
  password: '',
  remember: true,
});
const onFinish = (values: any) => {
  console.log('FormState->', formState);
  login();
  console.log('Success:', values);
};

const onFinishFailed = (errorInfo: any) => {
  console.log('Failed:', errorInfo);
};

const login = () => {
  axios.post('http://localhost:8080/user/login', formState).then((response) => {
    let respData = response.data;
    console.log(respData);
    if (respData.code == 200) {
      localStorage.setItem('token', respData.data.token);
      message.success('登陆成功');
      console.log(localStorage.getItem('token'));
    } else {
      message.error(respData.msg);
    }
  })
};

</script>

<style lang="less" scoped>
.user-layout-login {
  label {
    font-size: 14px;
  }

  .getCaptcha {
    display: block;
    width: 100%;
    height: 40px;
  }

  .forge-password {
    font-size: 14px;
  }

  button.login-button {
    padding: 0 15px;
    font-size: 16px;
    height: 40px;
    width: 100%;
  }

  .user-login-other {
    text-align: left;
    margin-top: 24px;
    line-height: 22px;

    .item-icon {
      font-size: 24px;
      color: rgba(0, 0, 0, 0.2);
      margin-left: 16px;
      vertical-align: middle;
      cursor: pointer;
      transition: color 0.3s;

      &:hover {
        color: #1890ff;
      }
    }

    .register {
      float: right;
    }
  }
}
</style>