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
                    <v-form ref="form" lazy-validation>

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
                                        color="secondary"
                                        required
                                        ></v-select>
                                    </v-col>
                                </v-row>

                                <v-radio-group
                                    v-model="currentItem.publish"
                                    row
                                    required
                                >
                                    <v-radio
                                        label="Publish"
                                        value="true"
                                    ></v-radio>
                                    <v-radio
                                        label="Do not publish (yet)"
                                        value="false"
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
                                <v-alert v-if="message" color="red" dark dense>
                                    {{ message }}
                                </v-alert>
                                <v-row>
                                    <v-col cols="9">
                                        <v-file-input
                                            :rules="[(v) => !v || v.size < 1000000 || 'Image should be less than 1 MB']"
                                            prepend-icon="mdi-camera"
                                            label="Images"
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
                                
                                <div v-for="(name, i) in imageNames" :key="i">
                                    {{ name }}
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
            currentImage: undefined,
            imageNames: [],
            message: "",
            currentItem: {
                title: "",
                price: 0,
                description: "",
                published: "",
                mainImage: "",
                smallImage1: "",
                smallImage2: "",
                smallImage3: ""
            },
            types: ['broche', 'necklace', 'bague']
        }
    },
    methods: {

        selectFile(file) {
            this.message = "";
            this.currentImage = file;
        },
        upload() {
            if (!this.currentImage) {
                this.message = "Please select a file!";
                return;
            }

            this.message = "";

            imageDataService.upload(this.currentImage)
                .then((response) => {
                    this.message = response.data.message;
                    this.imageNames.push(this.currentImage.name);
                    this.currentItem.mainImage = response.data.id;
                    this.currentImage = undefined;
                    return imageDataService.get(response.data.id);
                })
                .catch((e) => {
                    this.message = "Could not upload the file!";
                    this.currentImage = undefined;
                    console.log(e);
                });
        },
        close () {
            this.$refs.form.reset();
            this.$emit('refreshItems');
            this.imageNames = [];
            this.dialog = false;
        },
        save () {
            itemDataService.create(this.currentItem)
                .then( () => this.close() )
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