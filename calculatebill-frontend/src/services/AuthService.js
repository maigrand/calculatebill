import BaseService from './BaseService';
import instance from "../config/axios.config"

class AuthService extends BaseService {
    static login = (user) => new Promise((resolve, reject) => {
        instance.post('/api/v1/user/sign-in', {
            email: user.email,
            password: user.password,
        }).then((res) => {
            resolve(res.data);
        }).catch((err) => {
            reject(err);
        })
    })

    static logout = () => {
        try {
            localStorage.removeItem('token');
        } catch (e) {
            console.error(e.message);
        }
    }
}

export default AuthService
