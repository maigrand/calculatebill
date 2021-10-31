import instance from "../../config/axios.config";
import {returnErrors} from "./errorActions";

import {
  BILL_LIST_LOADING,
  BILL_LIST_LOADED,
  BILL_CREATE_SUCCESS,
  BILL_CREATE_FAIL,
  BILL_LOADING,
  BILL_LOADED,
  BILL_FAIL,
  BILL_LIST_FAIL,
} from "./types";

export const createBill = (data) => (dispatch, getState) => {
  const config = tokenConfig(getState);
  const body = {
    name: data.name,
    tips: 0,
  };

  instance.post('/api/v1/bill', body, config)
      .then((res) => dispatch({
        type: BILL_CREATE_SUCCESS,
        payload: res.data,
      }))
      .catch((error) => {
        dispatch(
            returnErrors(error.message, err.response.status, 'BILL_CREATE_FAIL')
        );
        dispatch({
          type: BILL_CREATE_FAIL,
        });
      });
};

export const loadBill = (id) => (dispatch, getState) => {
  dispatch({type: BILL_LOADING});

  const config = tokenConfig(getState);

  instance.get(`/api/v1/bill/${id}`, config)
      .then((res) => dispatch({
        type: BILL_LOADED,
        payload: res.data
      }))
      .catch((error) => {
        dispatch(
            returnErrors(error.message, err.response.status, 'BILL_FAIL')
        );
        dispatch({
          type: BILL_FAIL,
        });
      });
};

export const loadBillsList = () => (dispatch, getState) => {
  dispatch({type: BILL_LIST_LOADING});

  const config = tokenConfig(getState);

  instance.get('/api/v1/bill', config)
      .then((res) => dispatch({
        type: BILL_LIST_LOADED,
        payload: res.data
      }))
      .catch((error) => {
        dispatch(
            returnErrors(error.message, err.response.status, 'BILL_FAIL')
        );
        dispatch({
          type: BILL_LIST_FAIL,
        });
      });
};

export const tokenConfig = getState => {
  const token = getState().user.token;

  const config = {
    headers: {
      "Content-Type": "application/json",
    }
  };

  if (token) {
    config.headers["Authorization"] = 'Bearer ' + token;
  }

  return config;
};