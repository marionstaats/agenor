<template>
    <v-container>
    <v-row justify="center">
        <v-col cols="11" md="6">
            <v-row>
                <!-- Images -->
                <v-col cols="12" md="3" align="center">
                    <span class="text-caption">Main image</span>
                    <v-img v-if="hasImage(currentItem.mainImage)" :src="require('../../../uploaded-files/' + currentItem.mainImage)" max-width="100"></v-img>
                </v-col>
                <v-col cols="12" md="3" align="center">
                    <span class="text-caption">Image 1</span>
                    <v-img v-if="hasImage(currentItem.smallImage1)" :src="require('../../../uploaded-files/' + currentItem.smallImage1)" max-width="100"></v-img>
                </v-col>
                <v-col cols="12" md="3" align="center">
                    <span class="text-caption">Image 2</span>
                    <v-img v-if="hasImage(currentItem.smallImage2)" :src="require('../../../uploaded-files/' + currentItem.smallImage2)" max-width="100"></v-img>
                </v-col>
                <v-col cols="12" md="3" align="center">
                    <span class="text-caption">Image 3</span>
                    <v-img v-if="hasImage(currentItem.smallImage3)" :src="require('../../../uploaded-files/' + currentItem.smallImage3)" max-width="100"></v-img>
                </v-col>

            </v-row>
        </v-col>

        <!-- Info -->
        <v-col cols="12" md="6" align="center">
            <div v-if="currentItem" class="edit-form ma-3">
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

                    <v-radio-group
                        v-model="currentItem.inStock"
                        row
                        required
                    >
                        <v-radio
                            label="In stock"
                            :value="true"
                            color="secondary"
                        ></v-radio>
                        <v-radio
                            label="Not in stock"
                            :value="false"
                            color="secondary"
                        ></v-radio>
                    </v-radio-group>

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
    </v-container>
</template>

<script>
import itemDataService from "../services/itemDataService";

export default {
    name: "item",
    data() {
        return {
        currentItem: {},
        message: "",
        types: ['bagues','broches', 'colliers', 'penditifs', 'outilsdecouture', 'outilsdescribe', 'viequotidienne', 'boutons', 'epingles', 'moderne'],
        };
    },
    methods: {
        hasImage(image) {
            if (image) {
                try {
                    require('../../../uploaded-files/' + image )
                    return true
                }
                catch (e) {
                    console.log('Error getting image: ' + e)
                    return false
                }
            }
            return false
        },

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
                type: this.currentItem.type,
                inStock: this.currentItem.inStock,
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
                .then(() => {
                    this.message = "The item was updated successfully!";
                })
                .catch((e) => {
                    console.log(e);
                });
        },
    },

    mounted() {
        this.getItem(this.$route.params.id);
    },
}
</script>

<style>
</style>