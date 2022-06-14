import {Button, Container, FloatingLabel, Form, FormControl, FormGroup, InputGroup, Row} from "react-bootstrap";
import {useState} from "react";
import {resolveObjMapThunk} from "graphql";
import {useParams} from "react-router-dom";
import {useMutation} from "@apollo/client";

const {gql} = require("@apollo/client");

const ADD_EXERCISE = gql`
    mutation CreateChooseAWordExercise($input: ChooseAWordExerciseInput, $chapterId: Int) {
        createChooseAWordExercise(token: "Token", input: $input, chapterId: $chapterId) {
            id, title, questions {
                ... on ChooseAWordQuestionContent {
                    id, content, correctAnswers, wordChoice
                }
            }
        }
    }
`

function CreateExercise({chapterId, input}) {

    const [createExercise, {data, loading, errors}] = useMutation(ADD_EXERCISE)

    if(data) {
        return (
            <h1>CREATED</h1>
        )
    } else if(loading) {
        return (
            <h1>LOADING</h1>
        )
    } else if(errors) {
        return (
            <h1 color="red">{errors.message}</h1>
        )
    } else {
        createExercise({
            variables: {
                input: input,
                chapterId: chapterId
            }
        }).then(c => console.log(c));
    }

}

function CreateChooseAWordExercise() {

    const chapterId = useParams().id;

    const [formValues, setFormValues] = useState([{}])
    const [submitted, setSubmitted] = useState(false);
    const [formInput, setFormInput] = useState();
    const [commonChoice, setCommonChoice] = useState();
    const [title, setTitle] = useState();

    const addSentenceForm = (index) => {
        let values = [...formValues]
        values.splice(index + 1, 0, {});
        setFormValues(values);
    }

    const submitForm = () => {
        setFormInput({
            questions: formValues,
            title: title,
            commonChoice: commonChoice
        })
    }

    const onCommonChoiceChange = (value) => {
        setCommonChoice(value.split(/ *, */g))
    }

    const onSentenceChange = (index, value) => {
        let input = parseSentence(value);
        let values = [...formValues];
        values[index] = input;
        setFormValues(values);
    }

    const parseSentence = (sentence) => {
        let input = {
            content: [], correctAnswers: [], wordChoice: []
        };

        let group = sentence.split(/(\[|\])/g)
        for(let i = 0; i < group.length; i++) {
                let string = group[i];
            if(string === "[") {
                input.content.push(null);
                let wordChoice = group[i+1].split(", ");
                if(wordChoice.length > 1)
                    input.wordChoice.push(wordChoice);
                input.correctAnswers.push(wordChoice[0]);
                i = i + 2;
            } else {
                input.content.push(string);
            }
        }
        return input;
    }

    if(formInput) {
        return (
            <CreateExercise chapterId={chapterId} input={formInput}/>
        )
    }

    return (
        <Container>
            <Row>
                <h3>
                    Create exerciseContent (choose a word)
                </h3>
            </Row>
                <FormGroup>
                    <FormControl type="input"
                                 onChange={(event) => setTitle(event.target.value)}
                                 placeholder="Exercise ttile."/>
                    <>
                        <FloatingLabel controlId="commonChoice" label="Common choice words." className="mb-3">
                            <FormControl  as="textarea"
                                          onChange={(event) => onCommonChoiceChange(event.target.value)}
                                          placeholder="Write down common choice of words."/>
                        </FloatingLabel>
                    </>
                </FormGroup>

            {
                formValues.map((e, i) => {
                    return (
                        <InputGroup className="mb-3" key={i}>
                            <FormControl onChange={(event) => onSentenceChange(i, event.target.value)} type="text" placeholder="Type sentence."/>
                            <Button onClick={() => addSentenceForm(i)}>+</Button>
                        </InputGroup>
                    )
                })
            }
                <Button onClick={submitForm} className="w-100" variant="primary">Send</Button>
        </Container>
    )

}

export default CreateChooseAWordExercise;