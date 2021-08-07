<template>
        <v-dialog
            v-model="dialog"
            max-width="900px"
            >
            <template v-slot:activator="{ on, attrs }">
                <v-btn class="myaddbutton mx-5 gold" v-bind="attrs" v-on="on">
                    <v-icon>mdi-briefcase-plus</v-icon>
                    <span>Add new item</span>
                </v-btn>
            </template>
            <v-card>
                <v-card-title>
                <span class="headline">Add new item</span>
                </v-card-title>

                <v-card-text>
                    <v-form ref="form" v-model="isValid">

                        <v-row>
                            <v-col cols="12" md="7">
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
                                        :rules="[(v) => !!v || 'Type is required']"
                                        color="secondary"
                                        required
                                        ></v-select>
                                    </v-col>
                                </v-row>

                                <v-radio-group
                                    v-model="currentItem.published"
                                    row
                                    mandatory
                                >
                                    <v-radio
                                        label="Publish"
                                        value="true"
                                        color="secondary"
                                    ></v-radio>
                                    <v-radio
                                        label="Do not publish (yet)"
                                        value="false"
                                        color="secondary"
                                    ></v-radio>
                                </v-radio-group>

                                <v-radio-group
                                    v-model="currentItem.inStock"
                                    row
                                    mandatory
                                >
                                    <v-radio
                                        label="In stock"
                                        value="true"
                                        color="secondary"
                                    ></v-radio>
                                    <v-radio
                                        label="Not in stock"
                                        value="false"
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

                            </v-col>

                            <!-- Images -->
                            <v-col cols="12" md="5">
                                <!-- Main image -->
                                <v-row>
                                    <v-btn class="upload_widget" color="success" dark @click="openUploadModal('main')">Upload main image</v-btn>
                                    <p v-if="successfullUploadMainImage">Main image successfully added!</p>
                                </v-row>

                                <!-- Small image 1 -->
                                <v-row>
                                    <v-btn class="upload_widget" color="success" dark @click="openUploadModal(1)">Upload small image 1</v-btn>
                                    <p v-if="successfullUploadSmall1">Small image 1 successfully added!</p>
                                </v-row>

                                <!-- Small image 2 -->
                                <v-row>
                                    <v-btn class="upload_widget" color="success" dark @click="openUploadModal(2)">Upload small image 2</v-btn>
                                    <p v-if="successfullUploadSmall2">Small image 2 successfully added!</p>
                                </v-row>

                                <!-- Small image 3 -->
                                <v-row>
                                    <v-btn class="upload_widget" color="success" dark @click="openUploadModal(3)">Upload small image 3</v-btn>
                                    <p v-if="successfullUploadSmall3">Small image 3 successfully added!</p>
                                </v-row>

                            </v-col>
                        </v-row>

                    </v-form>

                </v-card-text>

                <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn
                    color="blue darken-1"
                    text
                    @click="close"
                >
                    Cancel
                </v-btn>
                <v-btn
                    color="blue darken-1"
                    text
                    :disabled="!isValid || !successfullUploadMainImage"
                    @click="save"
                >
                    Save
                </v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>

</template>

<script>
import itemDataService from "@/services/itemDataService";

export default {
    data: () => {
        return {
            dialog: false,
            isValid: true,

            successfullUploadMainImage: false,
            successfullUploadSmall1: false,
            successfullUploadSmall2: false,
            successfullUploadSmall3: false,

            currentItem: {
                title: "",
                price: "",
                description: "",
                published: "",
                inStock: "",
                mainImage: "",
                smallImage1: "",
                smallImage2: "",
                smallImage3: ""
            },
            types: ['bagues','broches', 'colliers', 'penditifs', 'outilsdecouture', 'outilsdescribe', 'viequotidienne', 'boutons', 'epingles', 'moderne']
        }
    },
    methods: {
        openUploadModal(index) {
            window.cloudinary.openUploadWidget(
                { 
                    cloud_name: 'agenor',
                    upload_preset: 'io0se5gr',
                    folder: this.currentItem.title === "" ? new Date() : this.currentItem.title
                },
                (error, result) => {
                if (!error && result && result.event === "success") {
                    if (index === 1) {
                        this.currentItem.smallImage1 = result.info.url;
                        this.successfullUploadSmall1 = true;
                    } else if (index === 2) {
                        this.currentItem.smallImage2 = result.info.url;
                        this.successfullUploadSmall2 = true;
                    } else if (index === 3) {
                        this.currentItem.smallImage3 = result.info.url;
                        this.successfullUploadSmall3 = true;
                    } else if (index === 'main') {
                        this.currentItem.mainImage = result.info.url;
                        this.successfullUploadMainImage = true;
                    }
                }}).open();
        },

        reset() {
            this.$refs.form.reset();
            this.currentItem = {};
            this.successfullUploadMainImage = false;
            this.successfullUploadSmall1 = false;
            this.successfullUploadSmall2 = false;
            this.successfullUploadSmall3 = false;
        },

        close () {
            this.reset();
            this.dialog = false;
        },

        save () {
            itemDataService.create(this.currentItem)
                .then( () => {
                    this.reset();
                    this.$emit('refreshItems');
                    this.dialog = false;
                })
                .catch( e => console.log(e) )       
        }
    }
}
</script>

<style scoped>
    .myaddbutton:focus::before {
        opacity: 0 !important;
    }

    .upload_widget {
        margin: 10px 0;
    }
</style>