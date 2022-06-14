import {DropdownButton, InputGroup, Row} from "react-bootstrap";
import DropdownItem from "react-bootstrap/DropdownItem";
import {useState} from "react";

function QuestionRowPart({questionPart, questionProps}) {

    if(questionPart === null) {
        return (
            <DropdownWithAnswers wordChoice={questionProps.wordChoice.shift()}
                                 chosenAnswer={questionProps.chosenAnswers[questionProps.index]}
                                 evaluation={questionProps.evaluations[questionProps.index]}
                                 index={questionProps.index}
                                 select={questionProps.select}
            />
        )
    } else {
        return (
            <InputGroup.Text>
                {questionPart}
            </InputGroup.Text>
        )
    }

}

function DropdownWithAnswers({index, chosenAnswer, wordChoice, evaluation, select}) {

    const getDropdownButtonVariant = () => {
        if(evaluation !== undefined)
            return  evaluation ? "success" : "danger";
        else
            return  "outline-secondary";
    }


    return (
        <DropdownButton variant={getDropdownButtonVariant()}
                        title={chosenAnswer ? chosenAnswer : "Choose"}>
            {
                wordChoice.map((c, i) => {
                    return(
                        <DropdownItem onClick={() => select(index, c)}>
                            {c}
                        </DropdownItem>
                    )
                })
            }
        </DropdownButton>
    )
}

function QuestionRow({index, questionContent, questionAnswer, questionEvaluation, setAnswer}) {


    const [chosenAnswers, setChosenAnswers] = useState(questionAnswer ? [...questionAnswer.answers] : []);
    const [evaluations, setEvaluations] = useState(questionEvaluation ? [...questionEvaluation.areAnswersCorrect] : []);

    const wordChoice = [...questionContent.wordChoice];

    const select = (answerIndex, value) => {
        if(chosenAnswers[answerIndex] !== value) {
            let answers = [...chosenAnswers];
            answers[answerIndex] = value;
            setChosenAnswers(answers);
            clearEvaluation(answerIndex);
            setAnswer(index, answerIndex, value);
        }
    }

    const clearEvaluation = (index) => {
        if(evaluations[index] !== undefined) {
            let ev = [...evaluations];
            ev[index] = undefined;
            setEvaluations(ev);
        }
    }


    let hoeIndex = 0;
    return (
        <Row>
            <InputGroup className="mb-3">
                {
                    questionContent.content.map((q) => {
                        const buttonIndex = q === null ? hoeIndex++ : hoeIndex;
                        return <QuestionRowPart questionPart={q} questionProps={
                            {
                                wordChoice: wordChoice,
                                chosenAnswers: chosenAnswers,
                                evaluations: evaluations,
                                select: select,
                                index: buttonIndex
                            }
                        }/>
                    })
                }
            </InputGroup>
        </Row>
    )

}

export default QuestionRow;