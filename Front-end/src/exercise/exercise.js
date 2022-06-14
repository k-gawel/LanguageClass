import {useParams} from "react-router-dom";
import {Container, Row} from "react-bootstrap";
import {gql, useQuery} from "@apollo/client";
import ChooseAWordExercise from "../choose-a-word-question/exercise";
import FillAWordExercise from "../fill-a-word/exercise";

const FETCH_EXERCISE = gql`
    query GetExercise($exerciseId: Int) {
        exerciseContent(id: $exerciseId) {
            id,
            title,
            type,
            questions {
                ... on ChooseAWordQuestionContent {
                    id,
                    content,
                    correctAnswers,
                    wordChoice
                },
                ... on FillAWordQuestion {
                    id,
                    content
                }
            }
        }
    }
`

function ExerciseContainer({content}) {
    return (
        <Container>
            <Row>
                {content}
            </Row>
        </Container>
    )
}

function Exercise() {

    const exerciseId = useParams().id
    const {data, loading, errors} = useQuery(FETCH_EXERCISE, {
        variables: {
            exerciseId: exerciseId
        }
    });


    if(loading) {
        let content = <h1>Loading</h1>;
        return <ExerciseContainer content={content}/>
    } else if(errors) {
        let content = <h1 color="red">{errors[0].message}</h1>
        return <ExerciseContainer content={content}/>
    } else if(data) {
        let exerciseContent = data.exerciseContent;
        let content;
        switch (exerciseContent.type) {
            case "CHOOSE_A_WORD":
                content = <ChooseAWordExercise exercise={exerciseContent}/>;
                break;
            case "FILL_A_WORD":
                content = <FillAWordExercise content={exerciseContent}/>;
                break;
        }
        return <ExerciseContainer content={content}/>
    }

}

export default Exercise;