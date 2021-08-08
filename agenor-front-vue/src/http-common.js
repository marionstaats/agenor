import axios from "axios";

export default axios.create({
    baseURL: "https://agenorbe.herokuapp.com/", //"http://localhost:8081/api",
    headers: {
        "Content-type": "application/json",
    }
});