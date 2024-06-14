<template>
  <div class="container">
    <div id="cabinDetails" class="container horizontal-scrollable"><h3>All Cabins Detail (managed by component):</h3>
      <table id="cabinCards">
        <div class="card-block card-1" :class="{'selectedCabinColor': selectCabin === cabin}" v-for="cabin in cabins"
             :key="cabin" @click="pushRoutedCabin(cabin) ">
          <div class="cabin-div" v-bind:id="cabin.id">
            <div><img alt="cabin-image" :src="cabin.image"></div>
            <div>{{ cabin.type }}</div>
            <div>{{ cabin.location }}</div>
          </div>
        </div>
      </table>
      <div>
        <button @click="onNewCabin" type="button" class="btn">New Cabin</button>
      </div>
      <br>
      <div v-if="selectCabin === null">
        <p class="selectCabinText">Select a cabin from the list above</p>
      </div>
      <div v-else>
        <router-view @unselect-cabin="pushRoutedCabin"></router-view>
      </div>
    </div>
  </div>
</template>

<script>
import {Cabin} from "@/models/cabin.js";


export default {
  name: "Overview37Component",
  inject: ['cabinsService'],
  async created() {
    this.cabins = await this.cabinsService.asyncFindAll();
    this.selectCabin = await this.findSelectedFromRouteParam();
  },
  async updated() {
    this.cabins = await this.cabinsService.asyncFindAll();
  },
  data() {
    return {
      cabins: [],
      selectCabin: null,
    }
  },
  watch: {
    async '$route'() {
      await this.findSelectedFromRouteParam();
    }
  },
  methods: {
    async onNewCabin() {
      let newCabin = Cabin.createSampleCabin(0)
      newCabin = await this.cabinsService.asyncSave(newCabin)
      this.cabins = await this.cabinsService.asyncFindAll();
      this.pushRoutedCabin(newCabin);
    },
    async deleteCabin(cabin) {
      await this.cabinsService.asyncDeleteById(cabin);
      this.cabins = await this.cabinsService.asyncFindAll();
      this.pushRoutedCabin(null);
    },
    async updateCabin(cabin) {
      await this.cabinsService.asyncSave(cabin)
      this.cabins = await this.cabinsService.asyncFindAll();
    },
    pushRoutedCabin(cabin) {
      if (cabin != null && cabin !== this.selectCabin) {
        this.$router.push(this.$route.matched[0].path + "/" + cabin.id)
      } else if (this.selectCabin != null) {
        this.selectCabin = null;
        this.$router.push('/cabins/overview37');
      }
    },
    async findSelectedFromRouteParam(){
      console.log(this.$route)
      if (this.$route.params.id == null) {
        this.$router.push('/cabins/overview37');
      } else {
        this.selectCabin = await this.cabinsService.asyncFindById(this.$route.params.id);
      }
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

