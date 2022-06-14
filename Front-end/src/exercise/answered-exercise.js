import {Container, Row} from "react-bootstrap";
import {useParams} from "react-router-dom";
import {gql, useQuery} from "@apollo/client";
import ChooseAWordExercise from "../choose-a-word-question/exercise";

const FETCH = gql`
    query GetAnswerAndEvaluation($exerciseId: Int, $answerId: Int) {
        getExerciseAnswerAndEvaluation(answerId: $answerId) {
            answer {
                id, answers {
                    answers, question {
                        id
                    }
                }
            }, 
            evaluation {
                id, answer {
                    id
                },
                questionEvaluations {
                    id, 
                    answer {
                        id
                    },
                    ...on ClosedQuestionEvaluation {
                        areAnswersCorrect
                    }
                },
                rating
            }
        }
        exerciseContent(id: $exerciseId) {
            id,
            title,
            questions {
                ... on ChooseAWordQuestionContent {
                    id,
                    content,
                    correctAnswers,
                    wordChoice
                }
            }
        }
    }
`

function AnsweredExercise() {

    const answerId = useParams().answerId;
    const exerciseId = useParams().exerciseId;

    const {data, loading, errors} = useQuery(FETCH, {
        variables: {
            answerId: answerId,
            exerciseId: exerciseId
        }
    })

    if(loading) {
        return (
            <Container>
                <Row>
                    LOADING
                </Row>
            </Container>
        )
    } if(data) {
        let content = data.exerciseContent;
        return (
            <Container className="w-50">
                <Row>
                    <ChooseAWordExercise exercise={data.exerciseContent}
                                         answer={data.getExerciseAnswerAndEvaluation.answer}
                                         evaluation={data.getExerciseAnswerAndEvaluation.evaluation}
                    />
                </Row>
            </Container>
        )
    }

}

export default AnsweredExercise;