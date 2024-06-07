<template>
  <div class="container-fluid">
    <div class="row">
      <div class="col-xs-12 col-md-3 col-lg-3">
        <h3>Pick a date:</h3>
        <input type="date">
        <h3 id="location">Pick a location:</h3>
        <div class="list-group">
          <a href="#" class="list-group-item list-group-item-action">Egmond aan Zee</a>
          <a href="#" class="list-group-item list-group-item-action">Katwijk</a>
          <a href="#" class="list-group-item list-group-item-action">Renesse</a>
          <a href="#" class="list-group-item list-group-item-action">Zoutelande</a>
          <a href="#" class="list-group-item list-group-item-action">De Panne</a>
        </div>
      </div>
      <div class="col">
        <h3>Photo Gallery:</h3>
        <img src="../assets/cabins/cabin4.jpg" alt="cabin" class="rounded img-fluid float-start" width="50%">
        <img src="../assets/cabins/cabin2.jpg" alt="cabin" class="rounded img-fluid float-end" width="50%">
        <img src="../assets/cabins/cabin1.jpg" alt="cabin" class="rounded img-fluid float-start" width="50%">
        <img src="../assets/cabins/cabin3.jpg" alt="cabin" class="rounded img-fluid float-end" width="50%">
        <img src="../assets/cabins/cabin.jpg" alt="cabin" class="rounded img-fluid float-end" width="50%">
        <img src="../assets/cabins/cabin5e.jpg" alt="cabin" class="rounded img-fluid float-start" width="50%">
      </div>
      <div class="col-xs-12 col-md-3 col-lg-3">
        <h3>Beach activities</h3>
        <div class="card text-center mb-3">
          <div class="card-body">
            <h5 class="card-title">Book now for the next month of june at Knokke-Heist, and get a free pass-par-toutes
              for the
              beach-volleyball tournament of the open belgium championships!</h5>

            <ul class="list-group list-group-flush">
              <li class="list-group-item"><strong>Period:</strong>6-19 june 2022</li>
              <li class="list-group-item"><strong>Location:</strong>Knokke beach</li>
              <li class="list-group-item"><strong>Size:</strong>Max 6 guests per cabin</li>
              <li class="list-group-item"><strong>Rent:</strong>1300 p/w</li>
              <li class="list-group-item"><strong>Only 4 cabins remaining!</strong></li>
            </ul>
          </div>
        </div>
      </div>
    </div>
    <br>
  </div>
</template>

<script>

export default {
  name: "WelcomeComponent",
  props: {}
}
</script>

<style scoped>
#location {
  padding-top: 10px;
}

h3 {
  color: black;
}

.container-fluid {
  padding-top: 20px;
}

input[type=date] {
  width: 100%;
  padding: 10px 20px;
  margin: 8px 0;
  box-sizing: border-box;
  border-radius: 4px;
  border-color: #A9A9A9;
}

ul {
  padding-left: 0;
}

.card {
  width: 100%;
}

.list-group-item {
  border-radius: 3px;
  padding-left: 0;
  width: 100%;
}

img {
  padding: 2px;
}

.list-group-item a:hover {
  color: #B27791;
}

.col, .col-xs-12 {
  padding-top: 20px;
}
</style>
