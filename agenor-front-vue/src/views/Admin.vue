<template>
    <div class="admin">
        <v-container>
            <div v-if="isLoggedIn">
                <v-data-table
                :headers="headers"
                :items="items"
                >
                    <template v-slot:[`item.mainImage`]="{ item }">
                        <v-img :src="item.mainImage"></v-img>
                    </template>

                    <template v-slot:[`item.smallImage1`]="{ item }">
                        <v-img :src="item.smallImage1"></v-img>
                    </template>
                    <template v-slot:[`item.smallImage2`]="{ item }">
                        <v-img :src="item.smallImage2"></v-img>
                    </template>
                    <template v-slot:[`item.smallImage3`]="{ item }">
                        <v-img :src="item.smallImage3"></v-img>
                    </template>


                    <template v-slot:[`item.actions`]="{ item }">
                    <v-icon small class="mr-2" @click="editItem(item.id)">mdi-pencil</v-icon>
                    <v-icon small @click="deleteItem(item.id)">mdi-delete</v-icon>
                    </template>

                </v-data-table>

            </div>

            <div v-else class="login">             
                <v-form ref="form">
                    <v-text-field
                    v-model="email"
                    label="E-mail"
                    required
                    ></v-text-field>
                    <v-text-field
                    v-model="password"
                    label="Password"
                    type="password"
                    required
                    ></v-text-field>
                     <v-btn
                    color="success"
                    class="mr-4"
                    @click="login"
                    >
                    Submit
                    </v-btn>
                </v-form>
            </div>
        </v-container>
    </div>
</template>

<script>
export default {
    data: () => {
        return {
            isLoggedIn: false,
            headers: [
                { text: "Title", align: "start", sortable: true, value: "title" },
                { text: "Price", value: "price", sortable: true},
                { text: "Type", value: "type", sortable: true},
                { text: "Description", value: "description", sortable: false },
                { text: "Published", value: "published", sortable: true },
                { text: "Main image", value: "mainImage", sortable: false },
                { text: "Small image 1", value: "smallImage1", sortable: false },
                { text: "Small image 2", value: "smallImage2", sortable: false },
                { text: "Small image 3", value: "smallImage3", sortable: false },
                { text: "Actions", value: "actions", sortable: false },
            ],
            items: [
                {id: 1, title: "Jewel", price: "100", type: "Bague", description: "Lorem ipsum...", published: "Yes", mainImage: "https://picsum.photos/100", smallImage1: "https://picsum.photos/100", smallImage2: "https://picsum.photos/100", smallImage3: "https://picsum.photos/100"},
                {id: 1, title: "Jewel", price: "100", type: "Collier", description: "Lorem ipsum...", published: "Yes", mainImage: "https://picsum.photos/100", smallImage1: "https://picsum.photos/100", smallImage2: "https://picsum.photos/100", smallImage3: "https://picsum.photos/100"},
                {id: 1, title: "Bijou", price: "200", type: "Bague", description: "Lorem ipsum...", published: "No", mainImage: "https://picsum.photos/100", smallImage1: "https://picsum.photos/100", smallImage2: "https://picsum.photos/100", smallImage3: "https://picsum.photos/100"},
                {id: 1, title: "Jewel", price: "100", type: "Bague", description: "Lorem ipsum...", published: "Yes", mainImage: "https://picsum.photos/100", smallImage1: "https://picsum.photos/100", smallImage2: "https://picsum.photos/100", smallImage3: "https://picsum.photos/100"},
                {id: 1, title: "Necklace", price: "400", type: "Broche", description: "Lorem ipsum...", published: "No", mainImage: "https://picsum.photos/100", smallImage1: "https://picsum.photos/100", smallImage2: "https://picsum.photos/100", smallImage3: "https://picsum.photos/100"}, 
                {id: 1, title: "Jewel", price: "600", type: "Bague", description: "Lorem ipsum...", published: "Yes", mainImage: "https://picsum.photos/100", smallImage1: "https://picsum.photos/100", smallImage2: "https://picsum.photos/100", smallImage3: "https://picsum.photos/100"}, 
            ]
        }
    },
    methods: {
        login() {
            this.isLoggedIn = true;
        },
        editItem(id) {
            this.$router.push({ name: "item-edit", params: { id: id } });
        },

    }
}
</script>

<style scoped>
.login {
    max-width: 300px;
}
</style>