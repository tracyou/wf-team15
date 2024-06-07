<template>
  <table class="table">
    <thead class="thead-dark">
    <th class="top-row">Cabin details (id={{ selectedCabin.id }})</th>
    </thead>
    <tbody>
    <tr>
      <th class="col">Type:</th>
      <select v-model="selectedCabin.type">
        <option v-for="cabinType in cabinType" :key="cabinType" :value="cabinType">{{ cabinType }}</option>
      </select>
    </tr>
    <tr>
      <th class="col">Location:</th>
      <select v-model="selectedCabin.location">
        <option v-for="location in location" :key="location" :value="location">{{ location }}</option>
      </select>
    </tr>
    <tr>
      <th class="col">Description:</th>
      <input v-model="selectedCabin.description">
    </tr>
    <tr>
      <th class="col">Image:</th>
      <select v-model="selectedCabin.image">
        <option v-for="image in images" :key="image" :value=" image">{{ images }}</option>
      </select>
    </tr>
    <tr>
      <th class="col">Price per week:</th>
      <input v-model="selectedCabin.pricePerWeek">
    </tr>
    <tr>
      <th class="col">Total availability:</th>
      <input v-model="selectedCabin.numAvailable">
    </tr>
    </tbody>
  </table>
  <button @click="$emit('delete-cabin', selectedCabin)" type="button" class="btn">Delete</button>
</template>

<script>

import {Cabin} from "@/models/cabin";

export default {
  name: "CabinsDetail32",
  props: ['selectedCabin'],
  emits: ['delete-cabin'],
  data() {
    return {
      cabinType: Cabin.CabinType,
      images: Cabin.Images,
      location: Cabin.Location,
    }
  }
}
</script>

<style scoped>
.top-row {
  width: 50%;
  text-align: center;
}

.col {
  border-color: #B27791;
  text-align: right
}

table {
  width: 50%;
  margin-left: auto;
  margin-right: auto;
}

select, input {
  width: 90%;
  border: none;
  padding: 12px 20px;
  margin-top: 5px;
  border-bottom: 1px solid #B27791;
}

select:focus, input:focus {
  width: 90%;
  outline: none;
  padding: 12px 20px;
  border-bottom: 2px solid #B27791;
}

.btn {
  background-color: #D3D3D3;
  margin: 5px 5px 10px;
}
</style>
