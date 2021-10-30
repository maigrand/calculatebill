import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";

import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faSpinner} from "@fortawesome/free-solid-svg-icons";

import BillsList from "../../components/ui/bills/BillsList";

import BillService from "../../services/BillService";

import "../../styles/_myBillsView.scss";

const BillsLoader = () => {
    return (
        <div className="loader">
            <FontAwesomeIcon icon={faSpinner} size="2x" color="white" />
            <p>Loading</p>
        </div>
    );
};

const MyBillsView = () => {
    const [bills, setBills] = useState([]);

    const [hasLoading, setLoading] = useState(true);

    useEffect(() => {
        BillService.getBillsList()
            .then((value) => {
                setBills(value);
                setLoading(false)
            })
            .catch((error) => {
                setLoading(false)
                console.error(error);
            })
    }, []);

    return (
      <div className="form__container">
          <div className="form__wrapper">
              <header>
                  <strong>
                      My bills
                  </strong>
              </header>

              <main>
                  {
                      !hasLoading ?
                          <BillsList
                              bills={bills}
                              amount={bills.length}
                          />
                          :
                          <BillsLoader />
                  }
              </main>

              <footer>
                  <div className="form__link-block">
                      <Link to="/home">Back</Link>
                  </div>
              </footer>
          </div>
      </div>
    );
};

export default MyBillsView;