import axios from 'axios'

const instance = axios.create({
    baseURL: 'https://calculatebill.maigrand.com',
    headers: {
        "Authorization": `Bearer ${localStorage.getItem('token')}`
    }
})

export default instance;