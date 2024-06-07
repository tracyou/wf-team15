<template>
  <Header></Header>
  <NavBar></NavBar>
  <router-view></router-view>
</template>

<script>
import Header from "@/components/Header";
import NavBar from "@/components/NavBar";
import {CabinsAdaptor} from "@/services/CabinsAdaptor";
import CONFIG from '@/app-config.js'

export default {
  name: "App37",
  components: {
    Header,
    NavBar,
  },
  provide: {
    cabinsService: new CabinsAdaptor(CONFIG.BACKEND_URL + "/cabins")
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