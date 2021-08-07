<template>
    <div class="vieux">
        <h3 class="ma-4 black--text">{{title}}</h3>
        <v-container>
            <div v-if="items.length > 0">
            <v-row>
                <v-col cols="12" sm="6" xl="4" v-for="(item, index) in items" :key="index">
                    <v-card class="elevation-3" :min-width="$vuetify.breakpoint.smAndDown ? 350 : 570" min-height="357">
                        <v-row no-gutters >
                            <v-col cols="12" md="6" align-self="center" >
                                <v-img v-bind:src="item.mainImage" class="img-pointer ml-sm-5" max-height="300" @click="dialog=true"></v-img>
                            </v-col>
                            <v-col cols="12" md="6">
                                <v-card-title
                                    class="headline"
                                    v-text="`${item.title}`"
                                ></v-card-title>
                                <v-card-subtitle class="text-justify" :class="$vuetify.breakpoint.smAndDown ? '' : 'cardtext'">{{item.description}}</v-card-subtitle>
                                <h3 class="rose--text font-weight-bold text-right mr-5">€ {{item.price}}</h3>

                                <v-row class="ma-3">
                                    <v-col cols="4">
                                        <v-img v-bind:src="item.smallImage1" class="img-pointer" @click="dialog=true"></v-img>
                                    </v-col>
                                    <v-col cols="4">
                                        <v-img v-bind:src="item.smallImage2" class="img-pointer" @click="dialog=true"></v-img>
                                    </v-col>
                                    <v-col cols="4">
                                        <v-img v-bind:src="item.smallImage3" class="img-pointer" @click="dialog=true"></v-img>
                                    </v-col>
                                </v-row>
                            </v-col>
                        </v-row>          
                    </v-card>

                    <v-dialog v-model="dialog" width="600">
                        <v-carousel>
                            <v-carousel-item :src="items[index].mainImage"></v-carousel-item>
                            <v-carousel-item :src="items[index].smallImage1"></v-carousel-item>
                            <v-carousel-item :src="items[index].smallImage2"></v-carousel-item>
                            <v-carousel-item :src="items[index].smallImage3"></v-carousel-item>
                        </v-carousel>
                    </v-dialog>

                </v-col>
            </v-row>
            </div>
            <div v-else>
                <h3>Il n'y a aucun élément à afficher</h3>
            </div>    
        </v-container>
    </div>
</template>

<script>
import itemDataService from "../services/itemDataService";

export default {
    data: () => {
        return {
            items: [],
            dialog: false,
        }
    },
    computed: {
        title() {
            if (this.$route.params.type == 'outilsdecouture') {
                return 'Outils de couture'
            } else if (this.$route.params.type == 'outilsdescribe') {
                return 'Outils de scribe'
            } else if (this.$route.params.type == 'viequotidienne') {
                return 'Vie quotidienne'
            }
            return this.capitalize(this.$route.params.type);
        }
    },

    methods: {
        retrieveItems() {
            itemDataService.getAll()
                .then(response => {
                    this.items = response.data.filter(item => {
                        return item.published === true && item.type == this.$route.params.type;
                    });         
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

<style scoped>
    .img-pointer:hover {
        cursor: pointer;
    }
    .cardtext {
        min-height: 170px;
    }
</style>