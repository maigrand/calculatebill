import React, {useState} from "react";
import {Link} from "react-router-dom";

import DefaultInput from "../../components/ui/DefaultInput.jsx";

import {createBill} from "../../store/actions/billActions";
import {connect} from "react-redux";

import "../../styles/_createBillView.scss";

const CreateBillView = ({createBill}) => {
  const [name, setName] = useState('');

  const handleBillName = (value) => setName(value);

  const handleCreateBill = async (e) => {
    e.preventDefault();
    createBill({name});
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

const mapStateToProps = state => ({
  error: state.error,
});

export default connect(mapStateToProps, {createBill})(CreateBillView);