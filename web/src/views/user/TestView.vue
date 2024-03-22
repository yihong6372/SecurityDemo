<template>
  <div>
    <div>
      <span style="display: block;color: #acacac">请输入clientId然后点击按钮连接sse</span>

      <input v-model="clientId" style="width: 300px;margin: 0 15px 0 15px"/>
      <button @click="createSSE">连接sse</button>
      <div style="margin: 20px 15px">
        <input v-model="msg" style="width: 300px;margin: 0 15px 0 15px"/>
        <button @click="sendMsg">提交</button>

        <button @click="testButton">test</button>
      </div>
    </div>
    <div class="content">
      <div v-for="(item, index) in eventData" :key="index" :class="item.styleClass">
        <span style="display: block">{{item.content}}</span>
      </div>
    </div>
    <div>
    </div>
  </div>
</template>

<script setup>
import {ref, onBeforeUnmount} from 'vue';
import {EventSourcePolyfill} from 'event-source-polyfill';
import axios from "axios";

const url = 'http://localhost:8080';
const clientId = ref(null);
const sseEvent = ref(null);
const msg = ref('');
const eventData = ref([]);

const testButton = () => {
  axios.get(`${url}/test/hello`, {
    params: {
      clientId: clientId.value
    }
  }).then(res => console.log(res));
};

const createSSE = () => {
  if (window.EventSource) {
    sseEvent.value = new EventSourcePolyfill(`${url}/sse/stream/${clientId.value}`, {
      heartbeatTimeout: 60 * 60 * 1000,
      headers: {
        'token': localStorage.getItem('token'),
      },
    });

    sseEvent.value.onopen = function(event) {
      console.log("连接成功", event);
    };

    sseEvent.value.onmessage = function(event) {
      let popData = eventData.value[eventData.value.length - 1];
      popData.content += event.data;
      console.log("接收信息", event);
    };

    sseEvent.value.onerror = function(error) {
      console.log("错误", error);
    };
  } else {
    console.log("不支持sse");
  }
};

const sendMsg = () => {
  let userData = {
    styleClass: 'content-user',
    content: msg.value
  };
  eventData.value.push(userData);

  let assistantData = {
    styleClass: 'content-assistant',
    content: ""
  };
  eventData.value.push(assistantData);
  axios.get(`${url}/sse/send`, {
    params: {
      clientId: clientId.value,
      content: msg.value
    }
  });
};

onBeforeUnmount(() => {
  if (sseEvent.value) {
    sseEvent.value.close();
    axios.get(`${url}/sse/close`, {
      params: {
        clientId: clientId.value
      }
    }).then(res => console.log(res));
  }
});
</script>

<style scoped>
.content{
  width: 80%;
  height: auto;
  margin: 0 auto;
  background-color: #e8e8e8;
}

.content-user{
  width: 100%;
  height: auto;
  padding: 25px;
  border: 1px #0071ff solid;
}
.content-user span{
  float: right;
}
.content-assistant{
  width: 100%;
  height: auto;
  padding: 25px;
  border: 1px #00ff22 solid;
}
.content-assistant span{
  float: left;
}

.content-user::after, .content-assistant::after {
  content: "";
  display: table;
  clear: both;
}

</style>
