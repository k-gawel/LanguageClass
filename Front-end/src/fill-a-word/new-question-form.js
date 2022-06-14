import {useState} from "react";
import {Button, Container, FormControl, InputGroup, Row} from "react-bootstrap";

function FillAWordQuestionForm({setQuestion}) {

    const [displayValue, setDisplayValue] = useState("");

    const submit = () => {
        let input = parseSentence(displayValue)
        if(validateInput(input)) {
            setQuestion(input);
            setDisplayValue("");
        }
    }

    const validateInput = (input) => {
        if(input != null && input.content != null && input.correctAnswers != null);
        let blankPartsCount = input.content.filter(v => v === null).length;
        let nonNullCorrectAnswersCount = input.correctAnswers.filter(c => c.length > 0).length;
        let containsNonBlank = input.content.some(v => v !== null);

        return blankPartsCount !== 0 && blankPartsCount === nonNullCorrectAnswersCount && containsNonBlank;
    }

    const parseSentence = (sentence) => {
        let newInput = {correctAnswers:[], content: []}

        let group = sentence.split(/(\[|\])/g);
        for(let i = 0; i < group.length; i++) {
            let string = group[i];
            if(string === "[") {
                newInput.content.push(null);
                let correctAnswers = group[i + 1].split(", ");
                newInput.correctAnswers.push(correctAnswers);
                i += 2;
            } else {
                newInput.content.push(string);
            }
        }

        return newInput;
    }

    return (
        <Container>
            <Row>
                <InputGroup key="question-form">
                    <FormControl type="text"
                                 placeholder="Type sentence."
                                 value={displayValue}
                                 onChange={(event) => setDisplayValue(event.target.value)}/>
                    <Button onClick={() => submit()}>+</Button>
                </InputGroup>
            </Row>
        </Container>
    )
}

export default FillAWordQuestionForm;