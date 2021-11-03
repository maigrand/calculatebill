import React, {useEffect} from "react";
import {Link} from "react-router-dom";

import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faSpinner} from "@fortawesome/free-solid-svg-icons";

import BillsList from "../../components/bills/BillsList";

import {loadBillsList} from "../../store/actions/billActions";
import {connect} from "react-redux";

import "../../styles/_myBillsView.scss";

const BillsLoader = () => {
  return (
      <div className="loader">
        <FontAwesomeIcon icon={faSpinner} size="2x" color="white"/>
        <p>Loading</p>
      </div>
  );
};

const MyBillsView = ({loadBillsList, billList, isLoading}) => {
  useEffect(() => {
    loadBillsList();
  }, []);

  return (
      <div className="list__container">
        <div className="list__wrapper column">
          <header>
            <strong>
              My bills
            </strong>
          </header>

          <main>
            {
              !isLoading ?
                  <BillsList
                      bills={billList}
                      amount={billList?.length}
                  />
                  :
                  <BillsLoader/>
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

const mapStateToProps = state => ({
  billList: state.bill.billList,
  isLoading: state.bill.isLoading,
  error: state.error,
});

export default connect(mapStateToProps, {loadBillsList})(MyBillsView);