import React from 'react';
import PageContainerArray from '../../container/PageContainerArray';

const PageContainer = (props) => {

    const pageContainer = PageContainerArray(props)

    return (
        <div>
            {pageContainer[props.onSelectedLink]}
        </div>
    );
}

export default PageContainer;