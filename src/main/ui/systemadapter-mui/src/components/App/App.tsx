import { useState } from "react";

import Header from '../Header/Header'

import PageContainer from "../PageContainer/PageContainer";

const App = () => {

  const [link, setLink] = useState<number>(0)

  const [userLoggedIn, setUserLoggedIn] = useState<boolean>(false)

  const clickedLinkHandler = (clickedLink : number) => {
    setLink(clickedLink);
  }

  const isUserLoggedInHandler = (data : boolean) => {
    setUserLoggedIn(data);
    setLink(0);
  }

  return (
    <div>
      <Header linkValue={link} isUserLoggedIn={userLoggedIn} onClickedLink={clickedLinkHandler} />
      <PageContainer isUserLoggedInToken={isUserLoggedInHandler} isUserLoggedIn={userLoggedIn} onSelectedLink={link} />
    </div>
  );
  
}

export default App;
