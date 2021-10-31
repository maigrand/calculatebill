import {
  BILL_LIST_LOADING,
  BILL_LIST_LOADED,
  BILL_CREATE_SUCCESS,
  BILL_CREATE_FAIL,
  BILL_LOADING,
  BILL_LOADED,
  BILL_FAIL,
  BILL_LIST_FAIL,
} from "../actions/types";

const initialState = {
  isLoading: false,
  billList: null,
  bill: null,
};

export default function (state = initialState, action) {
  switch (action.type) {
    case BILL_LIST_LOADING:
      return {
        ...state,
        isLoading: true,
      }
    case BILL_LIST_LOADED:
      return {
        ...state,
        isLoading: false,
        billList: action.payload,
      }
    case BILL_LOADING:
      return {
        ...state,
        isLoading: true,
      }
    case BILL_CREATE_SUCCESS:
    case BILL_LOADED:
      return {
        ...state,
        isLoading: false,
        bill: action.payload,
      }
    case BILL_CREATE_FAIL:
    case BILL_FAIL:
      return {
        ...state,
        isLoading: false,
        bill: null,
      }
    case BILL_LIST_FAIL:
      return {
        ...state,
        isLoading: false,
        billList: null,
      }
    default:
      return state;
  }
}