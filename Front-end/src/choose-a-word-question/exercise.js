import {Container, DropdownButton, InputGroup, Row} from "react-bootstrap";
import DropdownItem from "react-bootstrap/DropdownItem";
import {useState} from "react";
import AnswerClosedQuestionsExerciseSender from "../exercise/answer-closed-questions-exercise-sender";
import QuestionRow from "./question";


function ChooseAWordExercise({exercise, answer, evaluation}) {

    const [formInput, setFormInput] = useState(
        exercise.questions.map((q) => {
            return {
                questionId: q.id,
                answers: answer ?
                    [...answer.answers.find(a => a.question.id === q.id).answers] :
                    q.content.filter((c) => c === null).map(() => null)
            }
        })
    )

    const setAnswer = (questionIndex, answerIndex, answer) => {
        let input = [...formInput];
        input[questionIndex].answers[answerIndex] = answer;
        setFormInput(input);
    }

    return (
        <Container >
            <Row className="mb-3">
                <h1>{exercise.title}</h1>
            </Row>
            {
                exercise.questions.map((q, i) =>
                    <QuestionRow questionContent={q}
                                 index={i}
                                 questionAnswer={answer ? answer.answers[i] : undefined}
                                 questionEvaluation={evaluation ? evaluation.questionEvaluations[i] : undefined}
                                 setAnswer={setAnswer}/>
                )
            }
            <Row>
                <AnswerClosedQuestionsExerciseSender exerciseId={exercise.id} formInput={formInput}/>
            </Row>
        </Container>
    )

}

export default ChooseAWordExercise;