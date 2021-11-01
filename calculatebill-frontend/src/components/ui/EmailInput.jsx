import React from "react";

import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faUser} from "@fortawesome/free-solid-svg-icons";

const EmailInput = (props) => {
  const handleChange = (event) => props.onchange(event.target.value);
  return (
      <div className="input__container">
        <input
            type="email"
            placeholder={props.placeholder}
            onChange={handleChange}
            required
        />
        <FontAwesomeIcon icon={faUser} color="white" />
        {props.label && (<label>{props.label}</label>)}
      </div>
  );
};

export default EmailInput;