<template>
  <div class="container">
    <div id="cabinDetails" class="container horizontal-scrollable"><h3>All Cabins Detail (managed by component):</h3>
      <table id="cabinCards">
        <div class="card-block card-1" :class="{'selectedCabinColor': selectCabin === cabin}" v-for="cabin in cabins"
             :key="cabin" @click="chosenCabin(cabin) ">
          <div class="cabin-div" v-bind:id="cabin.id">
            <div><img :src="cabin.image"></div>
            <div> {{ cabin.type }}</div>
            <div> {{ cabin.location }}</div>
          </div>
        </div>
      </table>
      <div>
        <button v-on:click="onNewCabin()" type="button" class="btn">New Cabin</button>
      </div>
      <div v-if="selectCabin === null">
        <p class="selectCabinText">Select a cabin from the list above</p>
      </div>
      <div v-else>
        <router-view :selectedCabin="cabins.find(value => value === selectCabin)"
                     @delete-cabin="deleteCabin"></router-view>
      </div>
    </div>
  </div>
</template>

<script>
import {Cabin} from "@/models/cabin.js";
import {slugify} from "@/utils/slugiify";


export default {
  name: "Overview33Component",
  watch: {
    '$route'() {
      if (this.findSelectedFromRouteParam(this.$route) === undefined) {
        this.$router.push('/cabins/overview33');
      } else {
        this.selectCabin = this.findSelectedFromRouteParam(this.$route);
      }
    }
  },
  created() {
    {
      this.lastId = 10000;
      for (let i = 0; i < 8; i++) {
        this.cabins.push(Cabin.createSampleCabin(this.nextId()))
      }
    }
  },
  data() {
    return {
      cabins: [],
      selectCabin: null,
    }
  },
  methods: {
    nextId() {
      this.originalNumber = this.lastId;
      this.lastId += 3;
      return this.originalNumber;
    },

    onNewCabin() {
      this.newCabin = Cabin.createSampleCabin(this.nextId())
      this.cabins.push(this.newCabin);
      this.chosenCabin(this.newCabin);
    },

    deleteCabin(cabin) {
      this.cabins.splice(this.cabins.indexOf(cabin), 1);
      this.chosenCabin(cabin);
    },

    chosenCabin(cabin) {
      if (cabin != null && cabin !== this.selectCabin) {
        this.$router.push(this.$route.matched[0].path + "/" + cabin.id)
      } else if (this.selectCabin != null) {
        this.selectCabin = null;
        const titleSlug = slugify(cabin?.title);
        this.$router.push("/cabins/overview33/ " + titleSlug)
      }

      return this.selectCabin;
    },


    findSelectedFromRouteParam(route) {
      return this.cabins.find(value => value.id === parseInt(route.params.id));
    }
  }

}

</script>

<style scoped>
img {
  height: 100px;
  width: 100px;
}

#cabinCards {
  display: flex;
  width: 100%;
  height: 100%;
  overflow: auto;
}

.card-block {
  padding: 10px;
  width: 250px;
}

.cabin-div {
  align-content: center;
  border: 1px solid #D3D3D3;
  border-radius: 6px;
  padding: 10px;
  width: 100%;
  height: 100%;
}

.cabin-div:hover {
  background-color: #D3D3D3;
}

.selectedCabinColor {
  background: #ededed;
  border: 1px solid #ccc;
  border-radius: 3px;
  cursor: pointer;
}

.selectedCabinColor:focus {
  background: #e5e5e5;
  outline: none;
  cursor: pointer;
  border-radius: 3px;
}


#cabinDetails {
  padding-top: 10px;
}

.btn {
  background-color: #D3D3D3;
  margin: 5px 5px 10px;
}
</style>
