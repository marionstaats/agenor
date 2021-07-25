<template>
        <v-container>
            <div class="login">             
                <v-form ref="user">
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
</template>

<script>
import userService from "@/services/userService";

export default {
    data: () => {
        return {
            email: "",
            password: "",
        }
    },
    methods: {
        login() {
            let user = {
                email: this.email,
                password: this.password
            };

            userService.loginUser(user)
                .then(response => {
                    if (response.data.accessToken) {
                        localStorage.setItem('user', JSON.stringify(response.data));
                    }
                    console.log(response);
                    this.$router.push({ name: "admin" });
                })
                .catch(e => console.log(e))
        }

    }
}
</script>

<style scoped>
    .login {
        max-width: 300px;
    }
</style>