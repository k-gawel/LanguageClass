import {Button, Container, Row} from "react-bootstrap";
import FillAWordQuestionForm from "./new-question-form";
import FillAWordQuestion from "./question";
import {useState} from "react";
import {gql, useMutation} from "@apollo/client";

const ANSWER = gql`
    mutation AnswerAnExercise($exerciseId: Int, $answers: [ClosedQuestionAnswerInput]) {
        answerClosedQuestionExercise(exerciseId: $exerciseId, answers: $answers) {
            evaluation {
                id, questionEvaluations {
                    id, answer {
                        id
                    },
                    ... on ClosedQuestionEvaluation {
                        id, areAnswersCorrect, answer {
                            id
                        }
                    }
                }
            }
        }
    }
`

function FillAWordExercise({content, answer, evaluation}) {

    const [submit, {data, loading, errors}] = useMutation(ANSWER);

    const [input, setInput] = useState({
        exerciseId: content.id,
        answers: content.questions.map(q => { return {
            questionId: q.id,
            answers: []
        }})
    });

    const setAnswer = (questionId, newAnswers) => {
        let answers = [...input.answers];
        answers.filter(q => q.questionId === questionId).answers = newAnswers;
        setInput({...input, answers: answers});

    }

    const onSubmit = () => {
        const variables = {
            exerciseId: input.exerciseId,
            answers: input.answers
        }
        console.log(variables)
        submit({variables: variables}).then(console.log);
    }

    return (
        <Container>
            <Row>
                <h1>{content.title}</h1>
                {
                    content.questions.map((q, i) => {
                        return <FillAWordQuestion
                                           content={q}
                                           setNewInput={setAnswer}
                                           answer={answer != null ? answer.answers[i] : []}
                                           evaluations={evaluation != null ? evaluation.evaluations[i] : []}
                        />
                    })
                }
            </Row>
            <Row>
                <Button variant="primary" onClick={() => onSubmit()}>
                    SUBMIT
                </Button>
            </Row>
        </Container>
    )

}

export default FillAWordExercise;