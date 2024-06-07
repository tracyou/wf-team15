<template>
  <body>
  <header>
    <img height="80" src="../assets/logo_left.jpg" alt="logo_left" class="logo-left">
    <img height="80" src="../assets/logo_right.png" alt="logo_right" class="logo-right">
    <div class="title">
      <h2>Play & Stay Aan Zee</h2>
      <h6>{{ currentDate }}</h6>
      <h5>Chill out and feel good!</h5>

    </div>
    <div v-if="HasName">
      <h5 id="text">Welcome, {{ username }}!</h5>
    </div>
    <div v-else>
      <h5 id="text">Welcome, Visitor!</h5>
    </div>
  </header>
  </body>
</template>

<script>

export default {
  name: "HeaderComponent",
  inject: ['sessionService'],
  data() {
    return {
      currentDate: "Today is " + new Date().toLocaleString("en-NL",
          {year: "numeric", month: "long", day: "numeric"}),
      username: "Visitor",
      token: '',
    }
  },
  async created() {
    await this.getUserName()
  },
  computed: {
    HasName() {
      this.getUserName();
      return this.sessionService.isAuthenticated();
    }
  },
  methods: {
    getUserName() {
      this.token = this.sessionService.getTokenFromBrowserStorage();
      if (this.token !== null) {
        this.username = this.sessionService._currentAccount.name;
      }
    }
  },
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
  margin-top: 160px;
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

#text {
  align-self: end;
  display: flex;
  align-items: center;
  justify-content: flex-end;
  margin-bottom: 50px;
  margin-left: -20px;
  padding: 5px 5px;
  width: 100%;
  font-size: 20px;
  color: #fff;
}

</style>
