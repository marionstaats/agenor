import Vue from 'vue';
import Vuetify from 'vuetify/lib/framework';

Vue.use(Vuetify);

export default new Vuetify({
    theme: {
        themes: {
          light: {
            //https://coolors.co/ddd78d-dcbf85-8b635c-60594d-93a29b
            rose: "#8B635C",
            umber: "#60594D",
            gold: "#DCBF85",
            khaki: "#DDD78D",
            morningblue: "#93A29B"
          },
        },
      },
});
