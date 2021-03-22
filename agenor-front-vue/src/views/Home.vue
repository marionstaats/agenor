<template>
  <div class="home">
    <h1 class="ma-4 title-1 black--text">Agenor</h1>

    <v-container class="my-5">
      <v-row>
        <v-col cols="12" md="6">
          <p class="text-justify">
            Ce site a pour but de proposer des objets historiquement compatibles pour les personnes pratiquant la reconstitution. La periode couverte est centrée entre le XIII et XVI eme siecle, mais sera probablement étendue par la suite. Il est toujours possible de nous contacter pour la fabrication d'objets ne figurant pas au catalogue. 
            J'ai une formation en Bijouterie classique obtenue aux arts et métiers de Bruxelles. S'en est suivi des cours de ferronnerie d'art et une formation en ébenisterie. 
            Pratiquant la reconstitution moi même depuis 2000 et fabriquant pour mon compte les objets dont j'avais besoin, il est devenu évident de lier passion et profession. 
          </p>
          <p class="text-justify">
            Ce site a pour but de proposer des objets historiquement compatibles pour les personnes pratiquant la reconstitution. La periode couverte est centrée entre le XIII et XVI eme siecle, mais sera probablement étendue par la suite. Il est toujours possible de nous contacter pour la fabrication d'objets ne figurant pas au catalogue. 
            J'ai une formation en Bijouterie classique obtenue aux arts et métiers de Bruxelles. S'en est suivi des cours de ferronnerie d'art et une formation en ébenisterie. 
            Pratiquant la reconstitution moi même depuis 2000 et fabriquant pour mon compte les objets dont j'avais besoin, il est devenu évident de lier passion et profession. 
          </p>

        </v-col>
        <v-col cols="12" md="6">
          <v-hover>
            <template v-slot:default="{ hover }">
              <v-img src="../assets/images/bijoutier.jpg" >
                <v-fade-transition>
                  <v-overlay
                    v-if="hover"
                    absolute
                    color="#036358"
                  >
                    More info blabla
                  </v-overlay>
                </v-fade-transition>
              </v-img>
            </template>
          </v-hover>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>

export default {
  name: 'Home',
  components: {
  },
  data: () => {
    return {
      overlay: false,
    }
  }
}
</script>

<style scoped>
  h1 {
    font-family: "oldenglish";
    font-size: 70px;
  }
</style>