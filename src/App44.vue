<template>
  <HeaderSb></HeaderSb>
  <NavBarSb></NavBarSb>
  <router-view></router-view>
</template>

<script>
import {SessionSbService} from "@/services/SessionSbService";
import {reactive, shallowReactive} from "vue";
import {CabinsAdaptor} from "@/services/CabinsAdaptor";
import CONFIG from '@/app-config.js'
import NavBarSb from "@/components/NavBarSb.vue";
import HeaderSb from "@/components/HeaderSb.vue";
import {FetchInterceptor} from "@/services/FetchInterceptor";

export default {
  name: "App44",
  components: {
    HeaderSb,
    NavBarSb,
  },
  // provide: {
  //   cabinsService: new CabinsAdaptor(CONFIG.BACKEND_URL + "/cabins"),
  //
  //   sessionService: shallowReactive(
  //       new SessionSbService(CONFIG.BACKEND_URL + "/authentication", CONFIG.JWT_STORAGE_ITEM)),
  //
  // }
  provide() {
    this.sessionService = shallowReactive(
        new SessionSbService(CONFIG.BACKEND_URL + "/authentication", CONFIG.JWT_STORAGE_ITEM));
    this.theFetchInterceptor = new FetchInterceptor(this.sessionService, this.$router);

    return {
      cabinsService: new CabinsAdaptor(CONFIG.BACKEND_URL + "/cabins"),
      sessionService: this.sessionService,
      theFetchInterceptor: this.theFetchInterceptor
    }
  },
  unmounted() {
    console.log("App.unmounted() has been called");
    this.theFetchInterceptor.unregister();
  }
}
</script>

<style>
#app {
  font-family: Avenir, Arial, Roboto Slab, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin: 0;
  padding: 0;


  background-color: #ffffff;
  font-size: 18px;
  box-sizing: border-box;
  text-decoration-color: white;
}

</style>
