import axios from 'axios';

/*realizando a conexão a API */
const api = axios.create({
    baseURL: "http://192.168.15.103:8083/agfp/"
})

export default api