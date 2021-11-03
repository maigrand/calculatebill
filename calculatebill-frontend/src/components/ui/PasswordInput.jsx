import React from "react";

import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faKey} from "@fortawesome/free-solid-svg-icons";

const PasswordInput = (props) => {
  const handleChange = (event) => props.onchange(event.target.value);
  return (
      <div className="input__container">
        <input
            type="password"
            placeholder={props.placeholder}
            onChange={handleChange}
            autoComplete="on"
            required
        />
        <FontAwesomeIcon icon={faKey} color="white"/>
        {props.label && (<label>{props.label}</label>)}
      </div>
  );
};

export default PasswordInput;
