import './App.css';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import TextbookTable from "./textbooks/textbookEntity-table";
import Textbook from "./textbookEntity/textbookEntity";
import Chapter from "./chapter/Chapter";
import CreateChooseAWordExercise from "./choose-a-word-question/creator";
import Exercise from "./exercise/exercise";
import AnsweredExercise from "./exercise/answered-exercise";
import ExerciseCreator from "./exercise/creator";

function StudyMaterials() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="textbooks" element={<TextbookTable/>} />
                <Route path="textbookEntity/:id" element={<Textbook/>} />
                <Route path="chapter/:id" element={<Chapter/>}/>
                <Route path="createExercise/:chapterId" element={<ExerciseCreator/>}/>
                <Route path="exerciseContentEntity/:id" element={<Exercise/>}/>
                <Route path="exercise/:exerciseId/answer/:answerId" element={<AnsweredExercise/>}/>
            </Routes>
        </BrowserRouter>
    );
}

export default StudyMaterials;
