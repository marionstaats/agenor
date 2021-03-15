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
                            <v-list-item-title>Vieux</v-list-item-title>
                        </template>
                        <v-list-item
                            v-for="item in vieuxmenu"
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
                        <v-list-item-title>Modern</v-list-item-title>
                    </v-list-item>

                    <v-list-item to="/procesdetravail">
                        <v-list-item-icon>
                        <v-icon>mdi-hammer-wrench</v-icon>
                        </v-list-item-icon>
                        <v-list-item-title>Procès de travail</v-list-item-title>
                    </v-list-item>
                    <v-list-item to="/contact">
                        <v-list-item-icon>
                        <v-icon>mdi-email</v-icon>
                        </v-list-item-icon>
                        <v-list-item-title>Contact</v-list-item-title>
                    </v-list-item>

                </v-list-item-group>
            </v-list>
        </v-navigation-drawer>

        <!-- Menu full screen -->
        <v-tabs align-with-title color="white" class="hidden-sm-and-down">
        <v-tab to="/">Agenor</v-tab>
        <v-menu open-on-hover>
            <template v-slot:activator="{ on, attrs }">
            <v-btn
                text
                class="align-self-center mr-4"
                v-bind="attrs"
                v-on="on"
            >
                Catalogue vieux
            </v-btn>
            </template>

            <v-list class="gold"
                v-for="item in vieuxmenu"
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
            >
                Catalogue modern
            </v-btn>
            </template>

            <v-list class="gold">
                <v-list-item>Modern1</v-list-item>
                <v-list-item>Modenr2</v-list-item>
            </v-list>
        </v-menu>

        <v-tab to="/procesdetravail">Procès de travail</v-tab>
        <v-tab to="/contact">Contact</v-tab>

        </v-tabs>

        <v-spacer></v-spacer>

        <!-- Social media -->
        <v-btn icon>
            <v-icon>mdi-facebook</v-icon>
        </v-btn>

        <v-btn icon>
            <v-icon>mdi-instagram</v-icon>
        </v-btn>

        <v-btn icon v-if="loggedin">
            <v-icon>mdi-logout</v-icon>
        </v-btn>


    </v-app-bar>
</template>

<script>
export default {
    data: () => ({
        loggedin: false,
        drawer: false,
        vieuxmenu: [
            { title: 'Bagues', path: '/vieux'},
            { title: 'Broches', path: '/'},
            { title: 'Colliers', path: '/'},
            { title: 'Pendentifs', path: '/'},
            { title: 'Outils de couture', path: '/'},
            { title: 'Outils de scribe', path: '/'},
            { title: 'Vie quotidienne', path: '/'}
        ],
    }),
}
</script>

<style>

</style>