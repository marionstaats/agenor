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
                                <v-file-input
                                    v-model="currentItem.mainImage"
                                    :rules="[(v) => !!v || 'Main image is required']"
                                    prepend-icon="mdi-camera"
                                    label="Main image"
                                    accept="image/*"
                                    required
                                ></v-file-input>

                                <v-file-input
                                    v-model="currentItem.smallImage1"
                                    prepend-icon="mdi-camera"
                                    label="Small image 1"
                                    accept="image/*"
                                ></v-file-input>
                                <v-file-input
                                    v-model="currentItem.smallImage2"
                                    prepend-icon="mdi-camera"
                                    label="Small image 2"
                                    accept="image/*"
                                ></v-file-input>
                                <v-file-input
                                    v-model="currentItem.smallImage3"
                                    prepend-icon="mdi-camera"
                                    label="Small image 3"
                                    accept="image/*"
                                ></v-file-input>
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
export default {
    data: () => {
        return {
            dialog: false,
            currentItem: {},
            types: ['broche', 'necklace', 'bague']
        }
    },
    methods: {

        close () {
            this.$refs.form.reset();
            this.dialog = false;
        },
        save () {
            console.log(this.currentItem.mainImage);
            this.close();
        }

    }
}
</script>

<style scoped>
    .myaddbutton:focus::before {
        opacity: 0 !important;
    }
</style>