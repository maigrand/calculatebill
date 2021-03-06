import {createStore, combineReducers, applyMiddleware, compose} from "@reduxjs/toolkit";
import thunk from "redux-thunk";
import userReducer from "./reducers/userReducer";
import billReducer from "./reducers/billReducer";
import errorReducer from "./reducers/errorReducer";

const initialState = {};

const middleware = [thunk];

const composeEnhancers = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;

const rootReducer = combineReducers({
  user: userReducer,
  bill: billReducer,
  error: errorReducer,
});

export default createStore(
    rootReducer,
    initialState,
    composeEnhancers(applyMiddleware(...middleware)),
);
