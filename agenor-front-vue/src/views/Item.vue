<template>
    <v-row justify="center">
        <!-- Images -->
        <v-col cols="10" md="4" class="ma-md-3" >
            <v-img :src="currentItem.mainImage" contain></v-img>
            <v-btn id="upload_widget" color="accent" class="mt-5">Change main image</v-btn>
        </v-col>

        <v-col cols="10" md="2" class="ma-md-3" align="center">
            
            <v-row>
                <v-col cols="12" >
                    <v-img :src="currentItem.smallImage1" max-height="200" contain>
                    </v-img>
                    <v-btn id="upload_widget" color="accent" class="mt-3" small>Change small image 1</v-btn>
                </v-col>
            </v-row>
            <v-row>
                <v-col cols="12">
                    <v-img :src="currentItem.smallImage2" max-height="200" contain>
                    </v-img>
                    <v-btn id="upload_widget" color="accent" class="mt-3" small>Change small image 2</v-btn>
                </v-col>
            </v-row>
            <v-row>
                <v-col cols="12">
                    <v-img :src="currentItem.smallImage3" max-height="200" contain>
                    </v-img>
                    <v-btn id="upload_widget" color="accent" class="mt-3" small>Change small image 3</v-btn>
                </v-col>
            </v-row>
            
        </v-col>

        <!-- Info -->
        <v-col cols="12" md="4" align="center">
            <div v-if="currentItem" class="edit-form pa-5 ma-3">
                <p class="headline">Edit your item</p>

                <v-form ref="form" lazy-validation>
                    <v-text-field
                        v-model="currentItem.title"
                        :rules="[(v) => !!v || 'Title is required']"
                        label="Title"
                        required
                        color="secondary"
                    ></v-text-field>

                    <v-row>
                        <v-col cols="12" sm="6">
                            <v-text-field
                            label="Price"
                            v-model="currentItem.price"
                            :rules="[(v) => !!v || 'Price is required']"
                            prefix="€"
                            color="secondary"
                            required
                            ></v-text-field>
                        </v-col>
                        <v-col cols="12" sm="6">
                            <v-select
                            :items="types"
                            label="Type"
                            v-model="currentItem.type"
                            color="secondary"
                            ></v-select>
                        </v-col>
                    </v-row>
                    <v-textarea
                        v-model="currentItem.description"
                        :rules="[(v) => !!v || 'Description is required']"
                        label="Description"
                        maxlength="255"
                        required
                        counter
                        color="secondary"
                    ></v-textarea>

                    <label><strong>Status:</strong></label>
                    {{ currentItem.published ? "Published" : "Not published" }}

                    <v-divider class="my-5"></v-divider>

                    <v-btn v-if="currentItem.published"
                        @click="updatePublished(false)"
                        color="purple" small class="mr-2 white--text"
                    >
                        Unpublish
                    </v-btn>

                    <v-btn v-else
                        @click="updatePublished(true)"
                        color="secondary" small class="mr-2"
                    >
                        Publish
                    </v-btn>

                    <v-btn color="error" small class="mr-2" @click="deleteItem">
                        Delete
                    </v-btn>

                    <v-btn color="success" small @click="updateItem">
                        Update
                    </v-btn>
                </v-form>

                <p class="mt-3">{{ message }}</p>
            </div>

            <div v-else>
                <p>Please click on an item...</p>
            </div>


        </v-col>
    </v-row>

</template>

<script>
import itemDataService from "../services/itemDataService";

export default {
    name: "item",
    data() {
        return {
        currentItem: {},
        message: "",
        types: ['broche', 'necklace', 'bague']
        };
    },
    methods: {
        getItem(id) {
            itemDataService.get(id)
                .then((response) => {
                this.currentItem = response.data;
                })
                .catch((e) => {
                console.log(e);
                });
        },

        updatePublished(status) {
            var data = {
                id: this.currentItem.id,
                title: this.currentItem.title,
                price: this.currentItem.price,
                description: this.currentItem.description,
                published: status,
                mainImage: this.currentItem.mainImage,
                smallImage1: this.currentItem.smallImage1,
                smallImage2: this.currentItem.smallImage2,
                smallImage3: this.currentItem.smallImage3
            };

            itemDataService.update(this.currentItem.id, data)
                .then(() => {
                this.currentItem.published = status;
                })
                .catch((e) => {
                console.log(e);
                });
        },

        updateItem() {
            itemDataService.update(this.currentItem.id, this.currentItem)
                .then((response) => {
                    console.log(response.data);
                    this.message = "The item was updated successfully!";
                })
                .catch((e) => {
                    console.log(e);
                });
        },

        deleteItem() {
            itemDataService.delete(this.currentItem.id)
                .then(() => {
                this.$router.push({ name: "admin" });
                })
                .catch((e) => {
                console.log(e);
                });
        },
        updateImage() {
            var data = {
                id: this.currentItem.id,
                title: this.currentItem.title,
                price: this.currentItem.price,
                description: this.currentItem.description,
                published: this.currentItem.status,
                mainImage: this.currentItem.mainImage,
                smallImage1: this.currentItem.smallImage1,
                smallImage2: this.currentItem.smallImage2,
                smallImage3: this.currentItem.smallImage3
            };

            itemDataService.update(this.currentItem.id, data)
                .then((res) => {
                console.log(res);
                })
                .catch((e) => {
                console.log(e);
                });
        }
    },
    mounted() {
        this.message = "";
        this.getItem(this.$route.params.id);
    },
}
</script>

<style>
</style>