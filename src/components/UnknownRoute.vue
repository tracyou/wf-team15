<template>
  <div class="alert alert-dismissible alert-primary">
    <div class="container">
      <h2 class="title">Sorry. An error has occurred..</h2>
      <p>
      There is no component available at: "{{ $route.path }}"<br>
      Please check if you have your configuration correctly setup.
      </p>
    </div>
  </div>
</template>

<script>
export default {
  name: "UnknownRoute"
}
</script>

<style scoped>
.container {
  display: flex;
  flex-wrap: wrap;
}

.page-box {
  width: 60%;
  max-width: 600px;
  background-color: hsla(334, 28%, 58%, .4) !important;
  backdrop-filter: saturate(200%) blur(25px);  padding: 10px 30px 30px;
  box-shadow: 0 0 0 2px #7a7676;
  border-left: 5px #7a7676 solid;
  margin: 5% 5%;
  color: white;
}
.alert {
  background-color: hsla(334, 28%, 58%, .4) !important;
  backdrop-filter: saturate(200%) blur(25px);
  border-color: #B27791;
  text-align: center;
}

.container {
  color: white;
  display: flex;
  justify-content: space-around;
  flex-wrap: wrap;
  flex-direction: row;
  align-items: center;
}


.title {
  display: flex;
  justify-content: left;
  padding-top: 10px;
  padding-bottom: 10px;
}

</style>