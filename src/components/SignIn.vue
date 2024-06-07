<template>
  <h3>Please provide your login credentials:</h3>
  <form method="post" v-on:submit.prevent="onSignIn()">
    <div class="container">
      <div>
        <label>E-mail address: </label>
      </div>
      <div>
        <input type="email" name="email" v-model.trim="accountEmail" required
               v-on:keydown.enter="$event.preventDefault()">
      </div>
      <div>
        <label>Password: </label>
      </div>
      <div>
        <input type="password" name="password" v-model="accountPassword" required minlength="3">
      </div>
      <h5>{{ errorMessage }}</h5>
      <button type="submit" class="btn">Login</button>
      <div class="container">
        <h3>Current token:</h3>
        <h5 class="token">{{ token }}</h5>
      </div>
    </div>
  </form>
</template>

<script>
import router from "@/router";

export default {
  name: "SignIn",
  inject: ['sessionService'],

  data() {
    return {
      accountEmail: null,
      accountPassword: null,
      errorMessage: null,
      token: null,
    }
  },
  methods: {
     async onSignIn() {
      this.errorMessage = null;
      let account = await this.sessionService.asyncSignIn(this.accountEmail, this.accountPassword);
      this.token = this.sessionService.getTokenFromBrowserStorage();

      if (this.sessionService.isAuthenticated()) {
         await router.push("/sign-in");
        return
      }

      if (account == null) {
        this.errorMessage = "Could not authenticate with provided credentials";
        this.token = null;
      }
    }
  }
}
</script>

<style scoped>
.btn {
  background-color: #D3D3D3;
}

input[type=email], input[type=password] {
  width: 40%;
  display: inline-block;
  box-sizing: border-box;
  border: none;
  padding: 12px 20px;
  margin-top: 5px;
  border-bottom: 1px solid #B27791;
}

.container {
  margin: auto;
  width: 80%;
  padding: 10px;
}

.token {
  font-size: smaller;
  overflow-wrap: anywhere;
}
</style>

