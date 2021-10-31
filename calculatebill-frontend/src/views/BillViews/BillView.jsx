import React, {useEffect} from "react";

import {useQuery} from "../../hooks/useQuery";

import {loadBill} from "../../store/actions/billActions";
import {connect} from "react-redux";

const BillView = ({loadBill, bill}) => {
  const query = useQuery();

  useEffect(() => {
    loadBill(query.get('id'));
  }, [])

  return (
      <div className="form__container">
        <div className="form__wrapper">
          {bill && (<span>{bill.name}</span>)}
        </div>
      </div>
  );
};

const mapStateToProps = state => ({
  bill: state.bill.bill,
  error: state.error,
});

export default connect(mapStateToProps, {loadBill})(BillView);