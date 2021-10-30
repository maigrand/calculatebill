import React from "react";
import BillItem from "./BillItem";

import "../../../styles/ui/_billsListComp.scss";

const BillsList = (props) => {
    return (
      <div className="bills-list__container">
          <div className="bills-list__wrapper">
              <header>
                  { props.amount && (<strong>Bills amount: {props.amount}</strong>) }
              </header>

              <main>
                  { props.bills.map((bill, index) =>
                      <BillItem
                          key={index}
                          guests={bill.guests.length}
                          name={bill.name}
                          date={bill.date}
                          id={bill.id}
                      />)
                  }
              </main>
          </div>
      </div>
    );
};

export default BillsList;