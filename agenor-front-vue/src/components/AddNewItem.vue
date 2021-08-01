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
                            <!-- Main image -->
                            <v-col cols="12" md="5">
                                <v-alert v-if="messageMainImage" color="red" dark dense>
                                    {{ messageMainImage }}
                                </v-alert>
                                <v-row>
                                    <v-col cols="9">
                                        <v-file-input
                                            :rules="[(v) => !v || v.size < 20000000 || 'Image should be less than 20 MB']"
                                            prepend-icon="mdi-camera"
                                            label="Main image"
                                            accept="image/*"
                                            show-size
                                            required
                                            @change="selectFile"
                                        ></v-file-input>
                                    </v-col>
                                    <v-col cols="3" align-self="center">
                                        <v-btn color="success" dark small @click="upload">
                                            Upload
                                        </v-btn>
                                    </v-col>
                                </v-row>
                                
                                <div v-if="currentItem.mainImage">
                                    Main image: {{ mainImage.name }}
                                </div>

                                <!-- Small image 1 -->
                                <v-alert v-if="messageSmallImage1" color="red" dark dense>
                                    {{ messageSmallImage1 }}
                                </v-alert>
                                <v-row>
                                    <v-col cols="9">
                                        <v-file-input
                                            :rules="[(v) => !v || v.size < 20000000 || 'Image should be less than 20 MB']"
                                            prepend-icon="mdi-camera"
                                            label="Small image 1"
                                            accept="image/*"
                                            show-size
                                            required
                                            @change="selectFile1"
                                        ></v-file-input>
                                    </v-col>
                                    <v-col cols="3" align-self="center">
                                        <v-btn color="success" dark small @click="upload1">
                                            Upload
                                        </v-btn>
                                    </v-col>
                                </v-row>
                                
                                <div v-if="currentItem.smallImage1">
                                    Small image 1: {{ smallImage1.name }}
                                </div>

                                <!-- Small image 2 -->
                                <v-alert v-if="messageSmallImage2" color="red" dark dense>
                                    {{ messageSmallImage2 }}
                                </v-alert>
                                <v-row>
                                    <v-col cols="9">
                                        <v-file-input
                                            :rules="[(v) => !v || v.size < 20000000 || 'Image should be less than 20 MB']"
                                            prepend-icon="mdi-camera"
                                            label="Small image 2"
                                            accept="image/*"
                                            show-size
                                            required
                                            @change="selectFile2"
                                        ></v-file-input>
                                    </v-col>
                                    <v-col cols="3" align-self="center">
                                        <v-btn color="success" dark small @click="upload2">
                                            Upload
                                        </v-btn>
                                    </v-col>
                                </v-row>
                                
                                <div v-if="currentItem.smallImage2">
                                    Small image 2: {{ smallImage2.name }}
                                </div>

                                <!-- Small image 3 -->
                                <v-alert v-if="messageSmallImage3" color="red" dark dense>
                                    {{ messageSmallImage3 }}
                                </v-alert>
                                <v-row>
                                    <v-col cols="9">
                                        <v-file-input
                                            :rules="[(v) => !v || v.size < 20000000 || 'Image should be less than 20 MB']"
                                            prepend-icon="mdi-camera"
                                            label="Small image 3"
                                            accept="image/*"
                                            show-size
                                            required
                                            @change="selectFile3"
                                        ></v-file-input>
                                    </v-col>
                                    <v-col cols="3" align-self="center">
                                        <v-btn color="success" dark small @click="upload3">
                                            Upload
                                        </v-btn>
                                    </v-col>
                                </v-row>
                                
                                <div v-if="currentItem.smallImage3">
                                    Small image 3: {{ smallImage3.name }}
                                </div>

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
                    :disabled="!isValid"
                    @click="save"
                >
                    Save
                </v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>

</template>

<script>
import imageDataService from "@/services/imageDataService";
import itemDataService from "@/services/itemDataService";

export default {
    data: () => {
        return {
            dialog: false,
            isValid: true,
            mainImage: undefined,
            messageMainImage: "",
            smallImage1: undefined,
            messageSmallImage1: "",
            smallImage2: undefined,
            messageSmallImage2: "",
            smallImage3: undefined,
            messageSmallImage3: "",

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
            types: ['bagues','broches', 'colliers', 'penditifs', 'outilsdecouture', 'outilsdescribe', 'viequotidienne']
        }
    },
    methods: {

        selectFile(file) {
            this.messageMainImage = "";
            this.mainImage = file;
            this.currentItem.mainImage = file?.name;
        },
        selectFile1(file) {
            this.messageSmallImage1 = "";
            this.smallImage1 = file;
            this.currentItem.smallImage1 = file?.name;
        },
        selectFile2(file) {
            this.messageSmallImage2 = "";
            this.smallImage2 = file;
            this.currentItem.smallImage2 = file?.name;
        },
        selectFile3(file) {
            this.messageSmallImage3 = "";
            this.smallImage3 = file;
            this.currentItem.smallImage3 = file?.name;
        },

        upload() {
            if (!this.mainImage) {
                this.messageMainImage = "Please select a file!";
                return;
            }

            this.messageMainImage = "";

            imageDataService.upload(this.mainImage)
                .then(() => {
                    this.messageMainImage = "Image uploaded successfully!";
                    setTimeout(this.hideMessageMainImage, 2000);
                })
                .catch((e) => {
                    this.messageMainImage = "Could not upload the file!";
                    setTimeout(this.hideMessageMainImage, 3000);

                    this.mainImage = undefined;
                    console.log(e);
                });
        },
        hideMessageMainImage() {this.messageMainImage = ""},

        upload1() {
            if (!this.smallImage1) {
                this.messageSmallImage1 = "Please select a file!";
                return;
            }

            this.messageSmallImage1 = "";

            imageDataService.upload(this.smallImage1)
                .then(() => {
                    this.messageSmallImage1 = "Image uploaded successfully!";
                    setTimeout(this.hideMessageSmallImage1, 2000);
                })
                .catch((e) => {
                    this.messageSmallImage1 = "Could not upload the file!";
                    setTimeout(this.hideMessageSmallImage1, 3000);

                    this.smallImage1 = undefined;
                    console.log('error:', e);
                });
        },
        hideMessageSmallImage1() {this.messageSmallImage1 = ""},

        upload2() {
            if (!this.smallImage2) {
                this.messageSmallImage2 = "Please select a file!";
                return;
            }

            this.messageSmallImage2 = "";

            imageDataService.upload(this.smallImage2)
                .then(() => {
                    this.messageSmallImage2 = "Image uploaded successfully!";
                    setTimeout(this.hideMessageSmallImage2, 2000);
                })
                .catch((e) => {
                    this.messageSmallImage2 = "Could not upload the file!";
                    setTimeout(this.hideMessageSmallImage2, 3000);

                    this.smallImage2 = undefined;
                    console.log(e);
                });
        },
        hideMessageSmallImage2() {this.messageSmallImage2 = ""},

        upload3() {
            if (!this.smallImage3) {
                this.messageSmallImage3 = "Please select a file!";
                return;
            }

            this.messageSmallImage3 = "";

            imageDataService.upload(this.smallImage3)
                .then(() => {
                    this.messageSmallImage3 = "Image uploaded successfully!";
                    setTimeout(this.hideMessageSmallImage3, 2000);
                })
                .catch((e) => {
                    this.messageSmallImage3 = "Could not upload the file!";
                    setTimeout(this.hideMessageSmallImage3, 3000);

                    this.smallImage3 = undefined;
                    console.log(e);
                });
        },
        hideMessageSmallImage3() {this.messageSmallImage3 = ""},

        deleteImages() {
            if(this.currentItem.mainImage) {imageDataService.delete(this.currentItem.mainImage)}
            if(this.currentItem.smallImage1) {imageDataService.delete(this.currentItem.smallImage1)}
            if(this.currentItem.smallImage2) {imageDataService.delete(this.currentItem.smallImage2)}
            if(this.currentItem.smallImage3) {imageDataService.delete(this.currentItem.smallImage3)}
        },
        reset() {
            this.$refs.form.reset();
            this.currentItem = {};
            this.mainImage = undefined;
            this.smallImage1 = undefined;
            this.smallImage2 = undefined;
            this.smallImage3 = undefined;
        },
        async close () {
            await this.deleteImages();
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
</style>