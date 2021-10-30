import React from "react";

import "../../styles/ui/_inputComp.scss";

const EmailInput = (props) => {
    const handleChange = (event) => {
      props.onchange(event.target.value);
    };

    return (
      <div className="input__container">
          <input
              type="email"
              placeholder={props.placeholder}
              onChange={handleChange}
          />
          { props.label && (<label>{props.label}</label>) }
      </div>
    );
};

export default EmailInput;