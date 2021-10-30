import React from "react";

import { useHistory } from "react-router-dom";

import "../../../styles/ui/_billItemComp.scss";

const BillItem = (props) => {
    const history = useHistory();

    const openBill = () => {
      history.push(`/bill?id=${props.id}`);
    };

    return (
      <div className="bill-item__container" onClick={openBill}>
          <header>
              {props.name && (<span>{props.name}</span>)}
              {props.date && (<span>{props.date}</span>)}
          </header>

          <main>
              {props.guests ? (<span>Guests: {props.guests}</span>) : ''}
          </main>
      </div>
    );
};

export default BillItem;