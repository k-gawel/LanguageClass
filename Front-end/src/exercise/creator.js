import {useParams} from "react-router-dom";
import {Container, Dropdown, Row} from "react-bootstrap";
import DropdownItem from "react-bootstrap/DropdownItem";
import DropdownToggle from "react-bootstrap/DropdownToggle";
import DropdownMenu from "react-bootstrap/DropdownMenu";
import {useState} from "react";
import FillAWordExerciseForm from "../fill-a-word/creator";
import CreateChooseAWordExercise from "../choose-a-word-question/creator";


function ExerciseCreator() {

    const [currentCreator, setCurrentCreator] = useState();

    const setCurrentState = (id) => {
        console.log("GET CURRENT STATE: " + id)
        let current;
        switch (id) {
            case "fill-a-word":
                current = <FillAWordExerciseForm/>;
                break;
            case "choose-a-word":
                current = <CreateChooseAWordExercise/>;
                break;
            default:
                current = null;
        }
        setCurrentCreator(current);
    }

    const chapterId = useParams().chapterId;

    return (
        <Container className="w-50">
            <Row className="mb-3">
                <Dropdown className="w-100">
                    <DropdownToggle className="w-100">
                        Create exercise
                    </DropdownToggle>
                    <DropdownMenu className="w-100">
                        <DropdownItem onClick={() => setCurrentState("choose-a-word")}>Choose a word.</DropdownItem>
                        <DropdownItem onClick={() => setCurrentState("fill-a-word")}>Fill a word</DropdownItem>
                    </DropdownMenu>
                </Dropdown>
            </Row>
            <Row>
                {
                    currentCreator
                }
            </Row>
        </Container>
    )

}

export default ExerciseCreator;