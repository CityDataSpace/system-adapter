import React from "react";

import { useState } from "react";

import Header from '../Header/Header'

import PageContainer from "../PageContainer/PageContainer";

const App = () => {

  const [link, setLink] = useState(0)

  const [userLoggedIn, setUserLoggedIn] = useState(false)

  const clickedLinkHandler = (clickedLink) => {
    setLink(clickedLink);
  }

  const isUserLoggedInHandler = (data) => {
    setUserLoggedIn(data);
    setLink(0);
  }

  return (
    <div>
      <Header isUserLoggedIn={userLoggedIn} onClickedLink={clickedLinkHandler} />
      <PageContainer isUserLoggedInToken={isUserLoggedInHandler} isUserLoggedIn={userLoggedIn} onSelectedLink={link} />
    </div>
  );
  
}

export default App;
