import React from "react";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";

const DefaultInput = (props) => {
  const handleChange = (event) => props.onchange(event.target.value);
  return (
      <div className="input__container">
        <input
            type="text"
            placeholder={props.placeholder}
            onChange={handleChange}
        />
        {props.icon && (<FontAwesomeIcon icon={props.icon} color="white"/>)}
        {props.label && (<label>{props.label}</label>)}
      </div>
  );
};

export default DefaultInput;