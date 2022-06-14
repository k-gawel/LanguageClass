import {Container, Table} from "react-bootstrap";
import AddChapterForm from "./add-chapter-form";
import {useNavigate} from "react-router-dom";



export function ChapterRow({chapter}) {


    const navigate = useNavigate();

    const goToChapter = () => navigate("/chapter/" + chapter.id);

    return (
        <tr key={chapter.id} onClick={goToChapter}>
            <td>
                {chapter.title}
            </td>
        </tr>
    )
}

function Content({content}) {
    if(content.type === "loading") {
        return (
            <tr>
                <td>
                    LOADING
                </td>
            </tr>
        )
    }
    if(content.type === "error") {
        return (
            <tr>
                <td color="red">
                    {content.content}
                </td>
            </tr>
        )
    }
    if(content.type === "chapters") {
        console.log(content.content)
        return (
            content.content.map((c, i) => {
                return <ChapterRow key="i" chapter={c}/>
            })
        )
    }
}

export function ChaptersTable({content, addNewChapter, textbookId}) {


    return (
        <Container>
            <Table striped>
                <thead>
                    <tr key="header"    >
                        <td>
                            Name
                        </td>
                    </tr>
                </thead>
                <tbody>
                <Content key="content" content={content}/>
                <tr key="addNewChapter">
                    <td>
                        <AddChapterForm addNewChapter={addNewChapter} textbookId={textbookId}/>
                    </td>
                </tr>
                </tbody>
            </Table>
        </Container>
    )
}

