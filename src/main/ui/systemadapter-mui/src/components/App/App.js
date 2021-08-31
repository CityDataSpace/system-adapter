import React from "react";

import { useState } from "react";

import Header from '../Header/Header'

const App = () => {

  const [link, setLink] = useState(0);

  const clickedLinkHandler = (clickedLink) => {

    setLink(clickedLink);

    console.log(clickedLink);
  }

  return (
    <Header onClickedLink={clickedLinkHandler} />
  );
  
}

export default App;
