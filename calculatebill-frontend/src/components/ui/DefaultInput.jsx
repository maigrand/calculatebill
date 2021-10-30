import React from "react";

const DefaultInput = (props) => {
    const handleChange = (event) => {
      props.onchange(event.target.value);
    };

    return (
      <div className="input__container">
          <input
              type="text"
              placeholder={props.placeholder}
              onChange={handleChange}
          />
          { props.label && (<label>{ props.label }</label>) }
      </div>
    );
};

export default DefaultInput;