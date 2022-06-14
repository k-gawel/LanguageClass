import {FormControl, FormGroup, InputGroup} from "react-bootstrap";
import {useState} from "react";

function FillAWordQuestion({content, answers, evaluations, setNewInput}) {

    let hoeIndex = 0;

    const [newAnswers, setNewAnswers] = useState(content.content.filter(v => v === null).map(v => null));

    const onInputValueChange = (event, index) => {
        let string = event.target.value;
        let newInput = [...newAnswers];
        if(string != null && string !== "")
            newInput[index] = string;
        else
            newInput[index] = null;
        console.log(newInput);
        setNewAnswers(newInput)
        setNewInput(content.id, newInput);
    }

    const getInputValue = (index) => {
        if(newAnswers[index] != null)
            return newAnswers[index];
        else if(answers && answers[index] != null)
            return answers[index]
        else
            return ""
    }

    const getInputColorStyle = (index) => {
        let style = {};
        if(evaluations.length > 0)
            style.color = evaluations[index] ? "green" : "red";
        else
            style.color = "black";
        return style;
    }

    return (
        <InputGroup>
            {
            content.content.map(c => {
                const index = hoeIndex;
                if(c != null) {
                    return <InputGroup.Text> {c} </InputGroup.Text>
                } else {
                    hoeIndex++;
                    return <FormControl value={getInputValue(index)}
                                        style={getInputColorStyle(index)}
                                        onChange={(event) => onInputValueChange(event, index)}/>
                }
            })
            }
        </InputGroup>
    )

}

export default FillAWordQuestion;