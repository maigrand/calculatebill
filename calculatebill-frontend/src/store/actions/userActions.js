import instance from "../../config/axios.config";
import {returnErrors} from "./errorActions";

import {
  USER_LOADED,
  USER_LOADING,
  AUTH_ERROR,
  LOGIN_SUCCESS,
  LOGIN_FAIL,
  LOGOUT_SUCCESS,
  REGISTER_SUCCESS,
  REGISTER_FAIL,
} from "./types";

export const login = (data) => dispatch => {
  const body = {
    email: data.email,
    password: data.password
  };

  instance.post('/api/v1/user/sign-in', body)
      .then((res) => dispatch({
        type: LOGIN_SUCCESS,
        payload: res.data,
      }))
      .catch((error) => {
        dispatch(
            returnErrors(error.message, err.response.status, 'LOGIN_FAIL')
        );
        dispatch({
          type: LOGIN_FAIL,
        });
      })
};

export const register = (data) => dispatch => {
  const body = {
    active: true,
    email: data.email,
    password: data.password,
  };

  instance.post('/api/v1/user/sign-up', body)
      .then((res) => dispatch({
        type: REGISTER_SUCCESS,
        payload: res.data,
      }))
      .catch((error) => {
        dispatch(
            returnErrors(error.message, err.response.status, 'REGISTER_FAIL')
        );
        dispatch({
          type: REGISTER_FAIL,
        })
      })
};

export const loadUser = () => (dispatch, getState) => {
  dispatch({type: USER_LOADING});

  const config = tokenConfig(getState);

  instance.get('/api/v1/user', config)
      .then(res => dispatch({
        type: USER_LOADED,
        payload: res.data,
      }))
      .catch((error) => {
        dispatch(returnErrors(error.response.data, error.response.status));
        dispatch({
          type: AUTH_ERROR,
        });
      });
}

export const logout = () => {
  return {
    type: LOGOUT_SUCCESS
  };
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