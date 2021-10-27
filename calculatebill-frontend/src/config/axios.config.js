import axios from 'axios'

const instance = axios.create({
    baseURL: 'https://calculatebill.maigrand.com',
})

export default instance;