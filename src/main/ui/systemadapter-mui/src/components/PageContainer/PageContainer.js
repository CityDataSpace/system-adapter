import React from 'react';

import Container from '@material-ui/core/Container';
import PageContainerArray from '../../container/PageContainerArray';

const PageContainer = (props) => {

    const pageContainer = PageContainerArray(props)

    return (
        <Container minWidth={"lg"}>
            {pageContainer[props.onSelectedLink]}
        </Container>
    );
}

export default PageContainer;