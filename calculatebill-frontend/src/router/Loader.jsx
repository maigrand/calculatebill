import React from "react";

import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faSpinner} from "@fortawesome/free-solid-svg-icons";

const Loader = () => {
    return (
        <div className="form__container">
            <div className="form__wrapper loader">
                <FontAwesomeIcon icon={faSpinner} size="2x" color="white" />
                <p>Loading</p>
            </div>
        </div>
    )
}

export default Loader;