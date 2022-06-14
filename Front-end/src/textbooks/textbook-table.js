import {Container, Table} from "react-bootstrap";
import CreateTextbookForm from "./create-textbook-form";
import {useState} from "react";
import {useNavigate} from "react-router-dom"
import {gql, useQuery} from "@apollo/client";

const FETCH_TEXTBOOKS = gql`
    query GetTextbooks {
        getTextbooks {
            id, name
        }
    }
`
const TextbookRow = ({i, t}) => {
    const navigate = useNavigate();

    const goToTextbook = () =>
        navigate("/textbook/" + t.id);

    return (
        <tr key={t} onClick={goToTextbook}>
            <td>{i}</td>
            <td>{t.id}</td>
            <td>{t.name}</td>
        </tr>
    )
}

function TableContainer({content, addNewTextbook}) {
    return (
        <Container>
            <Table striped bordered hover>
                <thead>
                <tr>
                    <th>#</th>
                    <th>ID</th>
                    <th>NAME</th>
                </tr>
                </thead>
                <tbody>
                {content}
                <tr>
                    <td/>
                    <td/>
                    <td>
                        <CreateTextbookForm addFunction={addNewTextbook} />
                    </td>
                </tr>
                </tbody>
            </Table>

        </Container>
    );
}

function TextbooksTable() {

    const [textbooks, setTextbooks] = useState(null);

    const {data, loading, error} = useQuery(FETCH_TEXTBOOKS);

    const addNewTextbook = (textbook) => {
        if(textbooks == null) {
            setTextbooks(textbook);
        }
        else
            setTextbooks([...textbooks, textbook]);
    }

    let content;

    if(loading) {
        content = (
            <tr>
                <h1>LOADING</h1>
            </tr>
        );
    }

    if(error) {
        content = (
            <tr>
                <h1 color="red">{error.message}</h1>
            </tr>
        )
    }

    if(data) {
        if(!textbooks)
            addNewTextbook(data.getTextbooks);

        if(textbooks)
            content = (
                textbooks.map((t, i) => {
                    return (
                        <TextbookRow i={i} t={t}/>
                    )
                })
            )
        else
            content = ( <h1>LOADING</h1> )
    }

    return <TableContainer addNewTextbook={addNewTextbook} content={content}/>


}
export default TextbooksTable;