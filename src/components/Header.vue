<template>
  <body>
  <header>
    <img height="80" src="../assets/logo_left.jpg" alt="logo_left" class="logo-left">
    <img height="80" src="../assets/logo_right.png" alt="logo_right" class="logo-right">
    <div class="title">
      <h2>Play & Stay Aan Zee</h2>
      <h6>{{currentDate}}</h6>
    <h5>Chill out and feel good!</h5>
    </div>
  </header>
  </body>
</template>

<script>

export default {
  name: "HeaderComponent",
  data() {
    return {
      currentDate: "Today is " + new Date().toLocaleString("en-NL",
          {year: "numeric", month: "long", day: "numeric"})
    }
  },
  methods: {

  }
}
</script>

<style>

header {
  background-image: linear-gradient(rgba(0, 0, 0, 0.2), rgba(0, 0, 0, 0.2)), url("../assets/background_img_sea2.jpg");
  height: 70vh;
  background-size: cover;
  background-position: center;
}

.title h2 {
  display: flex;
  align-items: flex-end;
  justify-content: center;
  width: 100%;
  font-size: 50px;
  color: #fff;
}

.title h5 {
  display: flex;
  align-items: center;
  justify-content: flex-end;
  margin-top: -42px;
  margin-left: -20px;
  padding: 5px 5px;
  width: 100%;
  font-size: 20px;
  color: #fff;
}

.title h6 {
  display: flex;
  align-items: center;
  justify-content: flex-start;
  margin-top: 190px;
  margin-left: 20px;
  padding: 5px 5px;
  width: 98%;
  font-size: 20px;
  color: #fff;
}

header img {
  height: auto;
  margin: 10px 10px 110px;
}

.logo-left {
  display: inline-block;
  width: 220px;
  float: left;

}

.logo-right {
  display: inline-block;
  width: 80px;
  float: right;
}

</style>