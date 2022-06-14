import {gql, useMutation} from "@apollo/client";
import {Button, Container, FormControl, FormGroup, InputGroup, Row} from "react-bootstrap";
import {useState} from "react";
import {useParams} from "react-router-dom";
import FillAWordQuestionForm from "./new-question-form";

const CREATE = gql`
    mutation CreateFillAWordExercise($chapterId: Int, $input: FillAWordExerciseInput) {
        createFillAWordExercise(chapterId: $chapterId, input: $input) {
            id, questions {
                id, content
            }                                                                     
        }
    }
`

function FillAWordExerciseForm() {

    const chapterId = useParams().chapterId;

    const [sendForm, {data, loading, errors}] = useMutation(CREATE);

    const [input, setInput] = useState({title: null, questions: []});

    const submit = () => {
        let vars = {
            chapterId: chapterId,
            input: input
        }
        console.log(vars);
        sendForm({variables: vars
        }).then(c => console.log(c))
    }

    const questionInputToString = (questionInput) => {
        let correctAnswers = [...questionInput.correctAnswers];
        let result = "";
        for (let content of questionInput.content) {
            result += content == null ? correctAnswers.pop() : content;
        }
        return result;
    }

    const setTitle = (title) => {
        setInput({...input, title: title});
    }

    const setQuestion = (newQuestion) => {
        let questions = [...input.questions];
        questions.push(newQuestion);
        setInput({...input, questions: questions})
    }


    //
    // const addQuestionRow = (index) => {
    //     let formQuestions = [...input.questions];
    //     formQuestions.splice(index, 0, {displayValue: "", content: [], correctAnswers: []});
    //     setInput({...input, questions: formQuestions});
    //     console.log("New questions");
    //     console.log(formQuestions)
    // }
    //
    // const removeQuestionRow = (index) => {
    //     if(input.questions.length <= 1) return;
    //     let questions = [...input.questions];
    //     setInput({...input, questions: questions});
    // }

    return (
        <Container>
            <FormGroup>
                <FormControl type="input"
                             onChange={(event) => setTitle(event.target.value)}
                             placeholder="Exercise title"/>
                {
                    input.questions.map((q, i) => {
                        return JSON.stringify(q);
                    })
                }
                <FillAWordQuestionForm setQuestion={setQuestion}/>
            </FormGroup>
            <Button onClick={() => submit()}>SUBMIT</Button>
        </Container>
    )
}

export default FillAWordExerciseForm;

