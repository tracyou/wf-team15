<template>
  <table class="table">

    <thead class="thead-dark">
    <th class="top-row">Cabin details (id={{ copy.id }})</th>
    </thead>
    <tbody>
    <tr>
      <th class="col">Type:</th>
      <select v-model="copy.type">
        <option v-for="cabinType in cabinType" :key="cabinType" :value="cabinType">{{ cabinType }}</option>
      </select>
    </tr>
    <tr>
      <th class="col">Location:</th>
      <select v-model="copy.location">
        <option v-for="location in location" :key="location" :value="location" selected>{{ location }}</option>
      </select>
    </tr>
    <tr>
      <th class="col">Description:</th>
      <input v-model="copy.description">
    </tr>
    <tr>
      <th class="col">Image:</th>
      <select v-model="copy.image">
        <option v-for="image in images" :key="image" :value=" image">{{ images }}</option>
      </select>
    </tr>
    <tr>
      <th class="col">Price per week:</th>
      <input v-model="copy.pricePerWeek">
    </tr>
    <tr>
      <th class="col">Total availability:</th>
      <input v-model="copy.numAvailable">
    </tr>
    </tbody>
  </table>
  <div>
    <button @click="saveCabin" :disabled="this.isDisabled">Save</button>
    <button @click="cancelEditCabin">Cancel</button>
    <button @click="resetCabin" :disabled="this.isDisabled" >Reset</button>
    <button @click="clearCabin">Clear</button>
    <button @click="deleteCabin(copy.id)">Delete</button>
  </div>
</template>

<script>

import {Cabin} from "@/models/cabin";

export default {
  name: "CabinsDetail34",
  props: ['selectedCabin'],
  emits: ['delete-cabin', 'unselect-cabin'],
  data() {
    return {
      cabinType: Cabin.CabinType,
      images: Cabin.Images,
      location: Cabin.Location,
      copy: null,

    }
  },
  computed: {
    isDisabled() {
      if (this.copy.type === null || this.copy.location === null || this.copy.image === null) {
        return true
      } else {
        return JSON.stringify(this.selectedCabin) === JSON.stringify(this.copy)
      }
    },
  },
  created() {
    this.createCopy();
  },
  methods: {
    createCopy() {
      this.copy = Cabin.copyConstructor(this.selectedCabin);
    },
    saveCabin() {
      Object.assign(this.selectedCabin, this.copy);
      this.$emit('unselect-cabin', this.selectedCabin);
    },
    resetCabin() {
      this.createCopy();
    },
    clearCabin() {
      this.copy = new Cabin();
    },
    cancelEditCabin() {
      this.$emit('unselect-cabin', this.selectedCabin);
    },
    deleteCabin() {
      this.$emit('delete-cabin', this.selectedCabin);
    }
  },
  watch: {
    selectedCabin() {
      this.createCopy();
    },
  },
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