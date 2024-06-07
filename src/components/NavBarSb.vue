<template>
  <nav class="navbar navbar-expand-lg">
    <div class="container-fluid">
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
              aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <router-link to="/home" active-class="active" class="nav-link">Home</router-link>
          </li>
          <li class="nav-item dropdown">

            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
              Cabins
            </a>
            <ul class="dropdown-menu">
              <li>
                <router-link to="/cabins/overview31" active-class="highlight" class="dropdown-item" href="#">All cabins
                  overview
                </router-link>
              </li>
              <li>
                <router-link to="/cabins/overview32" active-class="highlight" class="dropdown-item" href="#">Cabins edit
                  (components)
                </router-link>
              </li>
              <li>
                <router-link to="/cabins/overview33" active-class="highlight" class="dropdown-item" href="#">Cabins edit
                  (guarded)
                </router-link>
              </li>
              <li>
                <router-link to="/cabins/overview34" class="dropdown-item" active-class="highlight" href="#">Cabins edit
                  (routed)
                </router-link>
              </li>
              <li>
                <router-link to="/cabins/overview37" class="dropdown-item" active-class="highlight" href="#">Cabins edit
                  (REST)
                </router-link>
              </li>
              <li><a class="dropdown-item" href="#">Cabins edit (managed)</a></li>
            </ul>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Rentals</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">My Account</a>
          </li>
        </ul>
      </div>

      <ul class="navbar-nav navbar-right">
        <li class="nav-item">
          <router-link active-class="highlight" to="sign-up" class="nav-link" href="#">Sign Up</router-link>
        </li>
        <li class="nav-item">
          <div v-if="loggedIn">
            <router-link active-class="highlight" to="/sign-in" class="nav-link" href="#" @click="logOut">Log out</router-link>
          </div>
          <div v-else>
            <router-link active-class="highlight" to="/sign-in" class="nav-link" href="#">Log in</router-link>
          </div>
        </li>
      </ul>
    </div>
  </nav>
</template>

<script>
export default {
  name: "NavBar",
  inject: ['sessionService'],
  data() {
    return {
      logText: "Login",
      token: '',
    }
  },
  computed: {
    loggedIn() {
      return this.sessionService.isAuthenticated();
    }
  },
  methods: {
    logOut() {
      this.sessionService.signOut();
    },
  }
}
</script>

<style scoped>
.navbar {
  background-color: #A47A8C;
}

.nav-link {
  font-size: 16px;
  color: white;
}

.navbar a:hover, .dropdown:hover {
  background-color: #B27791;
}

.dropdown-content a:hover {
  background-color: #ddd;
}

</style>
