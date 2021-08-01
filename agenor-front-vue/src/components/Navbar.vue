<template>
    <v-app-bar
        app
        fixed
        color="rose"
        
        :max-height="$vuetify.breakpoint.smAndDown ? 50 : '20vh'"
        >

        <!-- Menu for mobile -->
        <v-app-bar-nav-icon @click="drawer = true" class="hidden-md-and-up">
        </v-app-bar-nav-icon>

        <v-navigation-drawer
            v-model="drawer"
            absolute
            app
            temporary
            >
            <v-list nav dense rounded>
                <v-list-item-group color="rose">
                    <v-list-item to="/">
                        <v-list-item-icon>
                            <v-icon>mdi-home</v-icon>
                        </v-list-item-icon>
                        <v-list-item-title>Agenor</v-list-item-title>
                    </v-list-item>

                    <v-list-group
                        prepend-icon="mdi-necklace"
                        color="rose"
                    >
                        <template v-slot:activator>
                            <v-list-item-title>Catalogue historique</v-list-item-title>
                        </template>
                        <v-list-item
                            v-for="item in historiqueMenu"
                            :key="item.title"
                            :to="item.path"
                        >
                            <v-list-item-icon>
                                <v-icon>mdi-chevron-right</v-icon>
                            </v-list-item-icon>

                            <v-list-item-title v-text="item.title"></v-list-item-title>
                        </v-list-item>
                    </v-list-group>

                    <v-list-item>
                        <v-list-item-icon>
                        <v-icon>mdi-string-lights</v-icon>
                        </v-list-item-icon>
                        <v-list-item-title>Catalogue moderne</v-list-item-title>
                    </v-list-item>

<!--                     <v-list-item to="/procesdetravail">
                        <v-list-item-icon>
                        <v-icon>mdi-hammer-wrench</v-icon>
                        </v-list-item-icon>
                        <v-list-item-title>Procès de travail</v-list-item-title>
                    </v-list-item>
 -->                    <v-list-item to="/contact">
                        <v-list-item-icon>
                        <v-icon>mdi-email</v-icon>
                        </v-list-item-icon>
                        <v-list-item-title>Contact</v-list-item-title>
                    </v-list-item>

                </v-list-item-group>
            </v-list>
        </v-navigation-drawer>

        <!-- Menu full screen -->
        <v-tabs align-with-title color="white" class="hidden-sm-and-down" hide-slider>
            <v-tab to="/">Agenor</v-tab>
            <v-menu open-on-hover>
                <template v-slot:activator="{ on, attrs }">
                <v-btn
                    text
                    class="align-self-center mr-4"
                    v-bind="attrs"
                    v-on="on"
                    v-bind:style= "($route.path.includes('/historique/') ? 'color: white;' : '' )"
                >
                    Catalogue historique
                </v-btn>
                </template>

                <v-list class="gold"
                    v-for="item in historiqueMenu"
                    :key="item.title"
                    :to="item.path"
                >
                    <v-list-item :to="item.path">{{item.title}}</v-list-item>
                </v-list>
            </v-menu>

            <v-menu open-on-hover>
                <template v-slot:activator="{ on, attrs }">
                <v-btn
                    text
                    class="align-self-center mr-4"
                    v-bind="attrs"
                    v-on="on"
                    v-bind:style= "($route.path.includes('/moderne/') ? 'color: white;' : '' )"
                >
                    Catalogue moderne
                </v-btn>
                </template>

                <v-list class="gold">
                    <v-list-item>Modern1</v-list-item>
                    <v-list-item>Modenr2</v-list-item>
                </v-list>
            </v-menu>

            <!-- <v-tab to="/procesdetravail">Procès de travail</v-tab> -->
            <v-tab to="/contact">Contact</v-tab>

        </v-tabs>

        <v-spacer></v-spacer>
        
        <!-- Social media -->
        <v-btn icon :href="facebook" target="_blank">
            <v-icon>mdi-facebook</v-icon>
        </v-btn>

        <v-btn icon :href="instagram" target="_blank">
            <v-icon>mdi-instagram</v-icon>
        </v-btn>

        <v-btn icon :href="pinterest" target="_blank">
            <v-icon>mdi-pinterest</v-icon>
        </v-btn>

        <!-- Back to admin menu -->
        <v-btn class="mr-3 gold" v-if="this.$route.path === `/item/${this.$route.params.id}`" to="/admin">
            <v-icon>mdi-arrow-left</v-icon>
            <span>Admin</span>
        </v-btn>

        <!-- Logout -->
        <v-btn class="gold" v-if="(this.$route.path === '/admin') || (this.$route.path === `/item/${this.$route.params.id}`)">
            <span>Logout</span>
            <v-icon>mdi-logout</v-icon>
        </v-btn>


    </v-app-bar>
</template>

<script>
import { urls } from "@/helpers/urls";

export default {
    data: () => ({
        facebook: urls.FACEBOOK,
        pinterest: urls.PINTEREST,
        instagram: urls.INSTAGRAM,
        drawer: false,
        historiqueMenu: [
            { title: 'Bagues', path: '/historique/bagues'},
            { title: 'Broches', path: '/historique/broches'},
            { title: 'Colliers', path: '/historique/colliers'},
            { title: 'Pendentifs', path: '/historique/penditifs'},
            { title: 'Outils de couture', path: '//historique/outilsdecouture'},
            { title: 'Outils de scribe', path: '/historique/outilsdescribe'},
            { title: 'Vie quotidienne', path: '/historique/viequotidienne'}
        ],
    })
}
</script>

<style scoped>
    .v-btn i:hover{
        color: white;
    }
</style>