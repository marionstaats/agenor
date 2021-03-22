<template>
    <div class="vieux">
        <h3 class="ma-4 black--text">{{title}}</h3>
        <v-container>
            <v-row>
                <v-col cols="12" sm="6" xl="4" v-for="(item, index) in items" :key="index">
                    <v-card class="elevation-3">
                        <v-row no-gutters>
                            <v-col cols="12" md="6" class="align-self-center">
                                <v-img :src="item.mainImage" @click="dialog=true"></v-img>
                            </v-col>
                            <v-col cols="12" md="6">
                                <v-card-title
                                    class="headline"
                                    v-text="`${item.title}  -  € ${item.price}`"
                                ></v-card-title>
                                <v-card-subtitle height="200" class="text-justify">{{item.description}}</v-card-subtitle>

                                <v-row class="ma-3">
                                    <v-col cols="4">
                                        <v-img :src="item.smallImage1" @click="dialog=true"></v-img>
                                    </v-col>
                                    <v-col cols="4">
                                        <v-img :src="item.smallImage2"></v-img>
                                    </v-col>
                                    <v-col cols="4">
                                        <v-img :src="item.smallImage3"></v-img>
                                    </v-col>
                                </v-row>
                            </v-col>
                        </v-row>          
                    </v-card>

                    <v-dialog v-model="dialog" width="600">
                <v-carousel>
                    <v-carousel-item
                    :src="items[index].mainImage"
                    ></v-carousel-item>
                    <v-carousel-item
                    :src="items[index].smallImage1"
                    ></v-carousel-item>
                    <v-carousel-item
                    :src="items[index].smallImage2"
                    ></v-carousel-item>
                    <v-carousel-item
                    :src="items[index].smallImage3"
                    ></v-carousel-item>

                </v-carousel>
            </v-dialog>

                </v-col>
            </v-row>
        </v-container>
    </div>
</template>

<script>
import itemDataService from "../services/itemDataService";

export default {
    data: () => {
        return {
            title: "",
            items: [],
            dialog: false,
        }
    },
    methods: {
        retrieveItems() {
            this.title = this.capitalize(this.$route.params.type);
            itemDataService.findByType(this.capitalize(this.$route.params.type))
                .then(response => {
                    this.items = response.data;         
                })
                .catch(e => {
                    console.log('Error getting items: ', e);
                });
        },
        capitalize(string) {
            return string.charAt(0).toUpperCase() + string.slice(1);
        },
    },
    watch: {
        '$route.params.type': function() {
            this.retrieveItems();
        }
    },
    mounted() {
        this.retrieveItems();
    }
}
</script>

<style>

</style>