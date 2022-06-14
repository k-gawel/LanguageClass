import {gql, useMutation, useQuery} from "@apollo/client";
import React, {useState} from 'react';
import button from "bootstrap/js/src/button";
import {Button, FormControl, FormGroup} from "react-bootstrap";
import FormContext from "react-bootstrap/FormContext";
import {render} from "react-dom";


const GET_DOGS = gql`
    mutation GetDogs($tName: String) {
        addTextbook(input: {name: $tName}) {
            id, name
        }
    }
`;


const CreateTextbookForm = ({addFunction}) => {

    let input;

    const [addTextbook, {data, loading, error}] = useMutation(GET_DOGS);

    const createTextbook = () => {
        addTextbook({
            variables: {tName: input}
        }).then(r => r.data.addTextbook)
            .then(t => addFunction(t));
    }

    if(loading) return "Loading";
    if(error) {
        console.log("ERROR:" +  error)
        return "Error";
    }

    return (
        <div>
            <FormGroup className="flex-d">
                <FormControl type="input"
                             onChange={(e) =>{
                                 input = e.target.value;
                             }}
                             placeholder="New textbook name"/>
            </FormGroup>
            <Button  onClick={() => createTextbook()}
                     type="primary">
                Add textbook
            </Button>
        </div>
    )

}


export default CreateTextbookForm;