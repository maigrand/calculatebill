import BaseService from "./BaseService";
import instance from "../config/axios.config";

class BillService extends BaseService {
    static createBill = (data) => new Promise((resolve, reject) => {
        instance.post('/api/v1/bill', {
            name: data.name,
        }).then((res) => {
            resolve(res.data);
        }).catch((error) => {
            reject(error);
        })
    });

    static addMemberToBill = (data, id) => new Promise((resolve, reject) => {
       instance.post(`/api/v1/bill/${id}/member`, {
           name: data.name,
       }).then((res) => {
           resolve(res.data);
       }).catch((error) => {
           reject(error);
       })
    });

    static addPositionToMember = (data, id, memberId) => new Promise((resolve, reject) => {
        instance(`/api/v1/bill/${id}/member/${memberId}`, {
            cost: data.cost,
            name: data.name,
        }).then((res) => {
            resolve(res.data);
        }).catch((error) => {
            reject(error);
        })
    })

    static deleteMemberFromBill = (id, memberId) => new Promise((resolve, reject) => {
        instance.delete(`/api/v1/bill/${id}/member/${memberId}`)
            .then((res) => {
                resolve(res.data);
            })
            .catch((error) => {
                reject(error);
            })
    })
}

export default BillService;