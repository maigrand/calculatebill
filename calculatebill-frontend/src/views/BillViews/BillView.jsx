import React, {useEffect, useState} from "react";

import { useQuery } from "../../hooks/useQuery";

import BillService from "../../services/BillService";

const BillView = () => {
    const [bill, setBill] = useState({});
    const query = useQuery();

    useEffect(() => {
        BillService.getBill(query.get('id'))
            .then((value) => {
                setBill(value);
                console.log(bill);
            })
            .catch((error) => {
                console.error(error);
            });
    }, [])

    return (
        <div className="form__container">
            <div className="form__wrapper">
                { bill && (<span>{bill.name}</span>) }
            </div>
        </div>
    );
};

export default BillView;