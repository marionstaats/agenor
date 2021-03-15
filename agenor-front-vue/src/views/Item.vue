<template>
    <v-row justify="center">
        <!-- Image -->
        <v-col cols="10" md="5" class="ma-md-3" >
            <v-img v-bind:src="currentitem.image" max-height="500">
            </v-img>
            <v-btn id="upload_widget" color="accent" class="mt-5" @click="openUploadModal">Change image</v-btn>
        </v-col>

        <!-- Info -->
        <v-col cols="12" md="6" align="center">
            <div v-if="currentitem" class="edit-form pa-5 ma-3">
                <p class="headline">Edit your item</p>

                <v-form ref="form" lazy-validation>
                <v-text-field
                    v-model="currentitem.title"
                    :rules="[(v) => !!v || 'Title is required']"
                    label="Title"
                    required
                    color="secondary"
                ></v-text-field>

                <v-textarea
                    v-model="currentitem.description"
                    :rules="[(v) => !!v || 'Description is required']"
                    label="Description"
                    required
                    color="secondary"
                ></v-textarea>

                <label><strong>Status:</strong></label>
                {{ currentitem.published ? "Published" : "Pending" }}

                <v-divider class="my-5"></v-divider>

                <v-btn v-if="currentitem.published"
                    @click="updatePublished(false)"
                    color="blue" small class="mr-2"
                >
                    Unpublish
                </v-btn>

                <v-btn v-else
                    @click="updatePublished(true)"
                    color="secondary" small class="mr-2 black--text"
                >
                    Publish
                </v-btn>

                <v-btn color="error" small class="mr-2" @click="deleteitem">
                    Delete
                </v-btn>

                <v-btn color="accent" small @click="updateitem">
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
        currentitem: null,
        message: "",
        };
    },
    methods: {
        getitem(id) {
        itemDataService.get(id)
            .then((response) => {
            this.currentitem = response.data;
            })
            .catch((e) => {
            console.log(e);
            });
        },

        updatePublished(status) {
            var data = {
                id: this.currentitem.id,
                title: this.currentitem.title,
                description: this.currentitem.description,
                published: status,
                image: this.currentitem.image
            };

            itemDataService.update(this.currentitem.id, data)
                .then(() => {
                this.currentitem.published = status;
                })
                .catch((e) => {
                console.log(e);
                });
        },

        updateitem() {
        itemDataService.update(this.currentitem.id, this.currentitem)
            .then((response) => {
            console.log(response.data);
            this.message = "The item was updated successfully!";
            })
            .catch((e) => {
            console.log(e);
            });
        },

        deleteitem() {
        itemDataService.delete(this.currentitem.id)
            .then(() => {
            this.$router.push({ name: "items" });
            })
            .catch((e) => {
            console.log(e);
            });
        },

        openUploadModal() {
            window.cloudinary.openUploadWidget(
                { cloud_name: 'dfqpsmhyl',
                upload_preset: 'ml_default_preset'
                },
                (error, result) => {
                if (!error && result && result.event === "success") {
                    this.currentitem.image = result.info.url;
                    this.updateImage(); 
                    }
                }).open();
        },

        updateImage() {
            var data = {
                id: this.currentitem.id,
                name: this.currentitem.name,
                gender: this.currentitem.gender,
                description: this.currentitem.description,
                published: this.currentitem.status,
                image: this.currentitem.image
            };

            itemDataService.update(this.currentitem.id, data)
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
        this.getitem(this.$route.params.id);
    },
}
</script>

<style>

</style>