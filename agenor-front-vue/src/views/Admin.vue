<template>
    <div class="admin">
        <v-container>
            <v-card class="elevation-2 mt-3">
                <v-card-title>
                    Aganor items
                    <v-spacer></v-spacer>
                    <v-text-field
                        v-model="search"
                        append-icon="mdi-magnify"
                        label="Search"
                        single-line
                        hide-details
                        class="mx-5"
                    ></v-text-field>

                    <AddNewItem />
                </v-card-title>

                <v-data-table
                :headers="headers"
                :items="items"
                :search="search"
                >

                    <template v-slot:[`item.published`]="{ item }">
                        <v-chip
                            :color="getColor(item.published)"
                            dark
                        >
                            <v-icon>{{getPublishIcon(item.published)}}</v-icon>
                        </v-chip>
                    </template>

                    <template v-slot:[`item.mainImage`]="{ item }">
                        <v-img :src="item.mainImage" contain max-height="120" class="ma-3"></v-img>
                    </template>

                    <template v-slot:[`item.smallImage1`]="{ item }">
                        <v-img :src="item.smallImage1" contain max-height="120" class="ma-3"></v-img>
                    </template>
                    <template v-slot:[`item.smallImage2`]="{ item }">
                        <v-img :src="item.smallImage2" contain max-height="120" class="ma-3"></v-img>
                    </template>
                    <template v-slot:[`item.smallImage3`]="{ item }">
                        <v-img :src="item.smallImage3" contain max-height="120" class="ma-3"></v-img>
                    </template>


                    <template v-slot:[`item.actions`]="{ item }">
                    <v-icon small class="mr-2" @click="editItem(item.id)">mdi-pencil</v-icon>
                    <v-icon small @click="deleteItem(item.id)">mdi-delete</v-icon>
                    </template>

                </v-data-table>
            </v-card>
        </v-container>
    </div>
</template>

<script>
import itemDataService from "../services/itemDataService";
import AddNewItem from "@/components/AddNewItem"

export default {
    components: {
        AddNewItem
    },
    data: () => {
        return {
            search: '',
            items: [],
            headers: [
                { text: "Title", align: "start", sortable: true, value: "title" },
                { text: "Price", value: "price", sortable: true},
                { text: "Type", value: "type", sortable: true},
                { text: "Description", value: "description", sortable: true },
                { text: "Main image", value: "mainImage", sortable: false, align: "center" },
                { text: "Small image 1", value: "smallImage1", sortable: false, align: "center" },
                { text: "Small image 2", value: "smallImage2", sortable: false, align: "center" },
                { text: "Small image 3", value: "smallImage3", sortable: false, align: "center" },
                { text: "Published", value: "published", sortable: true },
                { text: "Actions", value: "actions", sortable: false },
            ]
        }
    },
    methods: {
        retrieveItems() {
            itemDataService.getAll()
                .then(response => {
                    this.items = response.data.map(this.getDisplayItem);
                })
                .catch(e => {
                    console.log('Error getting all items: ', e);
                });
        },
        getDisplayItem(item) {
            return {
                id: item.id,
                title: item.title.length > 15 ? item.title.substr(0, 15) + "..." : item.title,
                price: item.price,
                type: item.type,
                description: item.description.length > 20 ? item.description.substr(0, 20) + "..." : item.description,
                published: item.published,
                mainImage: item.mainImage,
                smallImage1: item.smallImage1,
                smallImage2: item.smallImage2,
                smallImage3: item.smallImage3
            };
        },

        editItem(id) {
            this.$router.push({ name: "item", params: { id: id } });
        },
        getColor(published) {
            if (published) {
                return 'green';
            } else {
                return 'red';
            }
        },
        getPublishIcon(published) {
            if (published) {
                return 'mdi-checkbox-marked-circle'
            } else {
                return 'mdi-close-circle'
            }
        },
        deleteItem(id) {
            itemDataService.delete(id)
                .then(() => {
                this.retrieveItems();
                })
                .catch((e) => {
                console.log(e);
                });
        },
    },
    mounted() {
        this.retrieveItems();
    },

}
</script>
