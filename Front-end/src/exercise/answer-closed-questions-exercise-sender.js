import {useNavigate} from "react-router-dom";
import {gql, useMutation} from "@apollo/client";
import {Button} from "react-bootstrap";

const ANSWER_QUESTION = gql`
    mutation AnswerQuestion($exerciseId: Int, $answers: [ClosedQuestionAnswerInput]) {
        answerClosedQuestionExercise(exerciseId: $exerciseId, answers: $answers) {
            answer {
                id
            }
        }
    }
`

function AnswerClosedQuestionExercise({exerciseId, formInput}) {

    const navigate = useNavigate();

    const [sendForm, {data, loading, errors}] = useMutation(ANSWER_QUESTION);

    const send = () => {
        sendForm({
            variables: {
                exerciseId: exerciseId,
                answers: formInput
            }
        }).then(c => console.log(c));
    }

    if(loading) {
        return (
            <h1>
                Answering...
            </h1>
        )
    } else if(errors) {
        return (
            <h1>
                {errors.message}
            </h1>
        )
    } else if(data) {
        navigate(`/exercise/${exerciseId}/answer/${data.answerClosedQuestionExercise.answer.id}`)
    } else {
        return (
            <Button className="w-100" variant="primary" onClick={() => send()}>
                Send    form
            </Button>
        )
    }

}

export default AnswerClosedQuestionExercise;