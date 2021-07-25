import http from "../http-common";

class imageDataService {
    // getAll() {
    //     return http.get("/files");
    // }

    // get(id) {
    //     return http.get(`/files/${id}`);
    // }

    upload(image) {
        let formData = new FormData();

        formData.append("file", image);

        return http.post("/uploadFile", formData, {
            headers: {
                "Content-Type": "multipart/form-data"
            }
        })
    }

    // update(id, data) {
    //     return http.put(`/items/${id}`, data);
    // }

    delete(fileName) {
        return http.delete(`/deleteFile/${fileName}`);
    }

}

export default new imageDataService();