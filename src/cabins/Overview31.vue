<template>
  <div class="container">
    <div class="table-container">
      <h3>All Cabins Overview:</h3>
      <table class="table table-striped table-hover" id="categoryTable">
        <thead>
        <tr>
          <th v-for="header in headerName" :key="header"> {{ header }}</th>
        </tr>
        </thead>
        <tbody id="tableBody">
        <tr v-if="cabins.length === 0">
          <td>No data</td>
        </tr>
        <tr v-else v-for="cabin in cabins" v-bind:key="cabin">
          <td>{{ cabin.id }}</td>
          <td>{{ cabin.type }}</td>
          <td>{{ cabin.location }}</td>
          <td>{{ cabin.description }}</td>
          <td>$ {{ cabin.pricePerWeek }}</td>
          <td>{{ cabin.numAvailable }}</td>
        </tr>
        </tbody>
      </table>
    </div>
    <div>
      <button v-on:click="onNewCabin()" type="button" class="btn">New Event</button>
    </div>
    <br>
  </div>
</template>

<script>
import {Cabin} from "@/models/cabin.js";

export default {
  name: "Overview31Component",
  created() {
    this.lastId = 10000;
    for (let i = 0; i < 8; i++) {
      this.onNewCabin();
    }
  },

  data() {
    return {
      headerName: ["ID", "Type", "Location", "Description", "Price p/wk", "Total available"],
      cabins: []
    }
  },
  methods: {

    nextId() {
      this.lastId += 3;
      return this.lastId;
    },

    onNewCabin() {
      this.cabins.push(Cabin.createSampleCabin(this.nextId()));
    }
  },

}
</script>

<style scoped>

.container {
  padding: 10px;
}

.btn {
  background-color: #D3D3D3;
}

h3 {
  text-align: left;
}
</style>
