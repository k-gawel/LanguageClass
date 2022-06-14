import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import StudyMaterials from './StudyMaterials';
import reportWebVitals from './reportWebVitals';
import 'bootstrap/dist/css/bootstrap.min.css';
import {ApolloClient, ApolloProvider, InMemoryCache} from "@apollo/client";
import {render} from "react-dom";

const client = new ApolloClient({
    uri: 'http://localhost:8080/graphql',
    cache: new InMemoryCache()
});
const root = ReactDOM.createRoot(document.getElementById("root"));

root.render(
    <ApolloProvider client={client}>
        <StudyMaterials />
    </ApolloProvider>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
