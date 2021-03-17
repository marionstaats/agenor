import http from "../http-common";

class userService {
    loginUser(user) {
        return http.post("/login", user);
    }

}

export default new userService();