import {useLocation, useNavigate, useParams} from "react-router-dom";
import {gql, useQuery} from "@apollo/client";
import {Button, Container, Row, Table} from "react-bootstrap";
import {useState} from "react";

const FETCH_CHAPTER = gql`
    query GetChapter($id: Int) {
        getChapter(id: $id) {
            title,
            content {
                id,
                title
            }
        }
    }
`

function CreateExercise({chapterId}) {

    const location = useLocation();
    const navigate = useNavigate();

    const goToForm = () => {
        navigate("/createExercise/" + chapterId)
    }

    return (
        <Button onClick={goToForm}>Create exercise</Button>
    )

}

const ChapterContainer = ({id, title, content, addChapterPart}) => {
    return (
        <Container className="w-50">
            <Row>
                <h2>
                    {title}
                </h2>
            </Row>
            <Table>
                <thead>
                    <tr>
                        <td>
                            Title
                        </td>
                    </tr>
                </thead>
                <tbody>
                {content}
                </tbody>
            </Table>
            <Row>
                <CreateExercise chapterId={id}/>
            </Row>
        </Container>
    )
}

function ChapterPartRow({part}) {

    const navigate = useNavigate();

    const getUrl = () => {
        console.log(part.id)
        switch (part.__typename) {
            case "Exercise": return `/exerciseContent/${part.id}`;
        }
    }

    const goTo = () => {
        const url = getUrl();
        navigate(url);
    }

    return (
        <tr onClick={goTo}>
            <td>{part.title}</td>
            <td>{part.type}</td>
        </tr>
    )
}

function Chapter({id}) {

    const [chapter, setChapter] = useState(null);

    const paramsId = useParams().id;

    const chapterId = paramsId ? paramsId : id;


    const {data, loading, errors} = useQuery(FETCH_CHAPTER, {
        variables: {
            id: chapterId
        }
    })

    const addChapterPart = (chapterPart) => {
        let c = chapter;
        c.content.push(chapterPart);
        setChapter(c);
    }

    let content;

    if(loading)
        content = (
            <h1>Loading...</h1>
        )

    if(errors)
        content = (
            <h1 color="red">{errors.message}</h1>
        )

    if(data && !errors) {
        if(!chapter)
            setChapter(data.getChapter)

        if(chapter)
            content = (
                chapter.content.map(c => {
                    return (
                        <ChapterPartRow part={c}/>
                    )
                })
            )
    }

    return <ChapterContainer id={chapterId} title={chapter ? chapter.title : ""} content={content} addChapterPart={addChapterPart}/>
}

export default Chapter;