import React, { useState } from "react";
import { Link } from "react-router-dom";

import BillService from "../../services/BillService";

import "../../styles/_createBillView.scss";
import DefaultInput from "../../components/ui/DefaultInput";

const CreateBillView = () => {
    const [billName, setBillName] = useState('');

    const handleBillName = (value) => {
        setBillName(value);
    };

    const handleCreateBill = async (e) => {
        e.preventDefault();

        await BillService.createBill({
            name: billName
        });
    };

    return (
      <div className="form__container">
          <form className="form__wrapper" onSubmit={handleCreateBill}>
              <header>
                  <strong>Create bill</strong>
              </header>

              <main>
                  <DefaultInput
                      placeholder="Enter bill name"
                      label="Bill name"
                      onchange={(value) => handleBillName(value)}
                  />
              </main>

              <footer>
                  <button>Create</button>
                  <div className="create-bill__footer-link">
                      <Link to="/home">Back</Link>
                  </div>
              </footer>
          </form>
      </div>
    );
};

export default CreateBillView;