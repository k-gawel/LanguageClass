import {gql, useMutation} from "@apollo/client";
import {Button, Container, FormControl, FormGroup} from "react-bootstrap";

const ADD_CHAPTER = gql`
    mutation AddChapter($cName: String, $tId: Int) {
        addChapter(input: {
                        textbookId: $tId,
                        title: $cName
                    }) {
            id, title
        }
    }
`;

function AddChapterForm({textbookId, addNewChapter}) {
    let input;

    const [addChapter, {data, loading, error}] = useMutation(ADD_CHAPTER);

    const createChapter = () => {
        addChapter({
            variables: {
                cName: input,
                tId: textbookId,
                number: 1
            }
        }).then(c => c.data.addChapter).then(c => addNewChapter(c));
    }

    if(loading) return (
        <div>Loading...</div>
    )

    if(error) {
        console.log(error);
        return (
            <div>Error...</div>
        )
    }

    return(
        <div>
            <FormGroup className="flex-d">
                <FormControl type="input"
                             onChange={(e) =>{
                                 input = e.target.value;
                             }}
                             placeholder="New textbook name"/>
            </FormGroup>
            <Button  onClick={() => createChapter()}
                     type="primary">
                Add textbook
            </Button>
        </div>
    );
}

export default AddChapterForm;