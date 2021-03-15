<template>
    <div class="vieux">
        <h3 class="ma-4 black--text">Bagues</h3>
        <v-container>
            <v-row>
                <v-col cols="12" sm="6" xl="4" v-for="item in items" :key="item.title">
                    <v-card>
                        <v-row no-gutters>
                            <v-col cols="12" md="6" class="align-self-center">
                                <v-img :src="item.img" @click="dialog=true"></v-img>
                            </v-col>
                            <v-col cols="12" md="6">
                                <v-card-title
                                    class="headline"
                                    v-text="`€ ${item.price}`"
                                ></v-card-title>
                                <v-card-subtitle>{{item.text}}</v-card-subtitle>

                                <v-row class="ma-3">
                                    <v-col cols="4">
                                        <v-img :src="item.img2" @click="dialog=true"></v-img>
                                    </v-col>
                                    <v-col cols="4">
                                        <v-img :src="item.img2"></v-img>
                                    </v-col>
                                    <v-col cols="4">
                                        <v-img :src="item.img2"></v-img>
                                    </v-col>
                                </v-row>
                            </v-col>
                        </v-row>          
                    </v-card>
                </v-col>
            </v-row>
        </v-container>

        <v-dialog v-model="dialog" width="600">
            <v-carousel v-model="model">
                <v-carousel-item
                v-for="(color, i) in colors"
                :key="color"
                >
                <v-sheet
                    :color="color"
                    height="100%"
                    tile
                >
                    <v-row
                    class="fill-height"
                    align="center"
                    justify="center"
                    >
                    <div class="display-3">
                        Slide {{ i + 1 }}
                    </div>
                    </v-row>
                </v-sheet>
                </v-carousel-item>
            </v-carousel>
        </v-dialog>

    </div>
</template>

<script>
export default {
    data: () => {
        return {
            items: [
                {title: "Some title", price:"250", img: "https://picsum.photos/100", img2: "https://picsum.photos/50", text: "bague polylobée en argent et kyanite d'apres de nombreuse sources graphiques et pieces de fouilles (exemple Gerard David 1500)"},
                {title: "Some title2", price:"250", img: "https://picsum.photos/100", img2: "https://picsum.photos/50", text: "bague polylobée en argent et kyanite d'apres de nombreuse sources graphiques et pieces de fouilles (exemple Gerard David 1500)"},
                {title: "Some title3", price:"250", img: "https://picsum.photos/100", img2: "https://picsum.photos/50", text: "bague polylobée en argent et kyanite d'apres de nombreuse sources graphiques et pieces de fouilles (exemple Gerard David 1500)"},
                {title: "Some title4", price:"250", img: "https://picsum.photos/100", img2: "https://picsum.photos/50", text: "bague polylobée en argent et kyanite d'apres de nombreuse sources graphiques et pieces de fouilles (exemple Gerard David 1500)"},
                {title: "Some title5", price:"250", img: "https://picsum.photos/100", img2: "https://picsum.photos/50", text: "bague polylobée en argent et kyanite d'apres de nombreuse sources graphiques et pieces de fouilles (exemple Gerard David 1500)"},
                {title: "Some title6", price:"250", img: "https://picsum.photos/100", img2: "https://picsum.photos/50", text: "bague polylobée en argent et kyanite d'apres de nombreuse sources graphiques et pieces de fouilles (exemple Gerard David 1500)"}
            ],
            dialog: false,
            model: 0,
            colors: [
                'primary',
                'secondary',
                'yellow darken-2',
                'red',
                'orange',
            ],
        }
    }
}
</script>

<style>

</style>