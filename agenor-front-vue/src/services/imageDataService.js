import http from "../http-common";

class imageDataService {
    getAll() {
        return http.get("/images");
    }

    get(id) {
        return http.get(`/images/${id}`);
    }

    upload(image) {
        let formData = new FormData();

        formData.append("image", image);

        return http.post("/upload", formData, {
            headers: {
                "Content-Type": "multipart/form-data"
            }
        })
    }

    // update(id, data) {
    //     return http.put(`/items/${id}`, data);
    // }

    // delete(id) {
    //     return http.delete(`/items/${id}`);
    // }

}

export default new imageDataService();