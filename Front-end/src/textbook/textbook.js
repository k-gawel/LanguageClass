import {useState} from "react";
import {useNavigate, useParams} from "react-router-dom";
import {gql, useQuery} from "@apollo/client";
import {ChapterRow, ChaptersTable} from "./chapters-table";
import * as PropTypes from "prop-types";

const FETCH_TEXTBOOK = gql`
    query FetchChapters($textbookId: Int) {
        getTextbook(id: $textbookId) {
            name, chapters {
                id, title
            }
        }
    }
`;


function Textbook() {

    const [chapters, setChapters] = useState(null);

    const id = useParams().id;

    const {data, loading, errors} = useQuery(FETCH_TEXTBOOK, {
        variables: {
            textbookId: id
        }
    });

    const addNewChapter = (chapter) => {
        if(chapters == null)
            setChapters(chapter)
        else
            setChapters([...chapters, chapter]);
    }

    let content;

    if(loading)
        content = {
            type: "loading",
            content: "LOADING"
        }

    if(errors)
        content = {
            type: "errors",
            content: errors.message
        }

    if(data) {
        if(!chapters)
            addNewChapter(data.getTextbook.chapters);
        if(chapters)
            content = {
                type: "chapters",
                content: chapters
            }
    }

    return <ChaptersTable key="chapters-table" content={content} addNewChapter={addNewChapter} textbookId={id}/>
}

export default Textbook;