<template>
  <div class="contact">
    <v-snackbar v-model="snackbar" :timeout="3000" top color="khaki">
      <span>Merci de votre message!</span>
      <v-btn text right color="white" @click="snackbar=false" class="ml-4">Close</v-btn>
    </v-snackbar>

    <v-container>
      <v-row class="ma-5">
        <v-col cols="12" sm="6">
          <p>
            Toutes demandes de devis ou commande se fait par mail. Il n'y a volontairement pas de boutiques en ligne afin de garder un contact personnel avec chaque client.
          </p>
          <div v-for="(item, i) in items" :key="i">
            <v-list-item>
              <v-list-item-icon>
                <v-icon>{{item.icon}}</v-icon>
              </v-list-item-icon>
              <v-list-item-content>
                <v-list-item-title>{{item.text}}</v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </div>
        </v-col>
        <v-col cols="12" sm="6">
          <h3>Formulaire de contact</h3>
        <v-form
            ref="form"
            v-model="valid"
          >
            <v-text-field
              v-model="name"
              :rules="nameRules"
              label="Votre nom"
              color="black"
              required
            ></v-text-field>

            <v-text-field
              v-model="email"
              :rules="emailRules"
              label="Votre addresse mail"
              color="black"
              required
            ></v-text-field>

            <v-textarea
                  name="input-7-1"
                  v-model="message"
                  label="Message"
                  :rules="messageRules"
                  color="black"
                  required
            ></v-textarea>

            <v-btn
              :disabled="!valid"
              color="khaki"
              class="mr-4"
              @click="sendMail"
            >
              Envoie
            </v-btn>


          </v-form>

        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
export default {
  data: () => ({
        items: [
          {
            icon: 'mdi-email',
            text: 'info@agenor.be',
          },
          {
            icon: 'mdi-phone',
            text: '0482829292',
          },
          {
            icon: 'mdi-facebook',
            text: 'Link to facebook',
          },
          { icon: 'mdi-instagram',
            text: 'Link to instagram',
          }
        ],
        valid: false,
        firstname: '',
        lastname: '',
        nameRules: [
          v => !!v || 'Nom est requise',
        ],
        email: '',
        emailRules: [
          v => !!v || 'E-mail est requise',
          v => /.+@.+/.test(v) || 'Email doit être valide',      
        ],
        message: '',
        messageRules: [
          v => !!v || 'Message est requise',
        ],
        snackbar: false
      }),
  methods: {
    sendMail() {
      this.snackbar = true;
    }
  }
}
</script>

