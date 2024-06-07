<template>
  <Header></Header>
  <NavBar></NavBar>
  <Overview31></Overview31>
  <Overview32></Overview32>
</template>

<script>

import Header from "@/components/Header";
import NavBar from "@/components/NavBar";
import Overview31 from "@/cabins/Overview31";
import Overview32 from "@/cabins/Overview32";

export default {
  name: 'App31Component',
  components: {
    Header,
    NavBar,
    Overview31,
    Overview32
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
