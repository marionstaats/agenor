<template>
        <v-container>
            <div class="login">             
                <v-form ref="user">
                    <v-text-field
                    v-model="user.username"
                    label="Username"
                    required
                    ></v-text-field>
                    <v-text-field
                    v-model="user.password"
                    label="Password"
                    type="password"
                    required
                    ></v-text-field>
                    <v-btn
                    color="success"
                    class="mr-4"
                    @click="handleLogin"
                    >
                        Submit
                    </v-btn>
                </v-form>
            </div>
        </v-container>
</template>

<script>
import User from '../models/user'

export default {
    data: () => {
        return {
            user: new User('', ''),
        }
    },
    computed: {
        loggedIn() {
            return this.$store.state.auth.status.loggedIn;
        }
    },
    created() {
        if (this.loggedIn) {
            this.$router.push('/admin');
        }
    },
    methods: {
        handleLogin() {
            if (this.user.username && this.user.password) {
                this.$store.dispatch('auth/login', this.user).then(
                    () => {
                        this.$router.push('/admin');
                    },
                    error => {
                        console.log('Error login: ' + error)                    
                    }
                )
            }
        }
    }
}
</script>

<style scoped>
    .login {
        max-width: 300px;
    }
</style>