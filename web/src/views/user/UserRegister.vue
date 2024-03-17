<template>
  <div class="main user-layout-register">
    <h3><span>注册</span></h3>
    <a-form
        ref="formRef"
        :model="formState"
        name="user-register"
        class="user-layout-register"
        :label-col="{ span: 8 }"
        :wrapper-col="{ span: 16 }"
        autocomplete="off"
        :rules="rules"
        @finish="handleFinish"
        @finishFailed="handleFinishFailed"
        @validate="handleValidate"
    >
      <a-form-item
          label="邮箱"
          name="email"
          has-feedback
      >
        <a-input placeholder="请输入邮箱" v-model:value="formState.email"/>
      </a-form-item>
      <a-form-item
          label="用户名"
          name="userName"
          has-feedback
      >
        <a-input placeholder="请输入用户名" v-model:value="formState.userName"/>
      </a-form-item>

      <a-form-item
          label="密码"
          name="password"
          has-feedback
      >
        <a-input-password placeholder="请输入密码" v-model:value="formState.password"/>
      </a-form-item>

      <a-form-item
          label="确认密码"
          name="checkPass"
          has-feedback
      >
        <a-input-password placeholder="请再次输入密码" v-model:value="formState.checkPass"/>
      </a-form-item>

      <a-form-item :wrapper-col="{ offset: 8, span: 16 }">
        <a-button type="primary" class="register-button" html-type="submit">
          注册
        </a-button>
        <a-button style="margin-left: 10px" @click="resetForm">重置</a-button>
      </a-form-item>
    </a-form>
  </div>

</template>

<script lang="ts" setup>
import {defineComponent, reactive, ref} from "vue";
import {FormInstance} from "ant-design-vue";
import {Rule} from "ant-design-vue/es/form";

interface FormState {
  userName: string
  password: string;
  checkPass: string;
  email: string;
}

const formRef = ref<FormInstance>();
const formState = reactive<FormState>({
  userName: '',
  password: '',
  checkPass: '',
  email: ''
});
const validatePass = async (_rule: Rule, value: string) => {
  if (value === '') {
    return Promise.reject('密码不能为空');
  } else {
    if (formState.checkPass !== '') {
      formRef.value!.validateFields('checkPass');
    }
    return Promise.resolve();
  }
};
const validatePass2 = async (_rule: Rule, value: string) => {
  if (value === '') {
    return Promise.reject('请再次输入密码');
  } else if (value !== formState.password) {
    return Promise.reject("两次密码不一致");
  } else {
    return Promise.resolve();
  }
};


const rules: Record<string, Rule[]> = {
  email: [{required: true, trigger: 'change',message: '用户名不能为空'},{type: 'email', message: '请输入正确的邮箱格式'}],
  userName: [{required: true, trigger: 'change',message: '用户名不能为空'}],
  password: [{required: true, validator: validatePass, trigger: 'change'}],
  checkPass: [{validator: validatePass2, trigger: 'change'}],
};

const handleFinish = (values: FormState) => {
  console.log('handleFinish=>',values, formState, formRef);
};
const handleFinishFailed = errors => {
  console.log('handleFinishFailed =>',errors);
};
const resetForm = () => {
  formRef.value.resetFields();
};
const handleValidate = (...args) => {
  console.log('handleValidate=>',args);
};


</script>

<style lang="less" scoped>
.user-layout-register {
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

  button.register-button {
    padding: 0 15px;
    font-size: 16px;
    height: 40px;
    width: 100%;
  }

  .user-register-other {
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
