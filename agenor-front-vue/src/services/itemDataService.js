import http from "../http-common";

class itemDataService {
    getAll() {
        return http.get("/items");
    }

    findByType(type) {
        return http.get(`/items?type=${type}`);
    }

    get(id) {
        return http.get(`/items/${id}`);
    }

    create(data) {
        return http.post("/items", data);
    }

    update(id, data) {
        return http.put(`/items/${id}`, data);
    }

    delete(id) {
        return http.delete(`/items/${id}`);
    }

}

export default new itemDataService();