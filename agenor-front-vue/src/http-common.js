import axios from "axios";

export default axios.create({
    baseURL: "https://agenorbe.herokuapp.com/api", //"http://localhost:8081/api",
    headers: {
        "Content-type": "application/json",
    }
});