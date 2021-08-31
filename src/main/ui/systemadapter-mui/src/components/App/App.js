import React from "react";

import { useState } from "react";

import Header from '../Header/Header'

import PageContainer from "../PageContainer/PageContainer";

const App = () => {

  const [link, setLink] = useState(0)

  

  const clickedLinkHandler = (clickedLink) => {

    setLink(clickedLink);

  }

  return (
    <div>
      <Header onClickedLink={clickedLinkHandler} />
      <PageContainer onSelectedLink={link} />
    </div>
  );
  
}

export default App;
