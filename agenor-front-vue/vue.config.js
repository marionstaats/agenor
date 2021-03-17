module.exports = {
  devServer: {
    port: 8082,
    proxy: 'https://localhost:8081'
  
  },
  transpileDependencies: [
    'vuetify'
  ]
}
