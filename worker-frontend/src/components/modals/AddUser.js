import {Box, Button, Heading, Input} from "@chakra-ui/react";
import {useState} from "react";
import {createWorker} from "../../api/profile/requests";

const AddUser = ({onClose}) => {
    const [name, setName] = useState("")
    const [salary, setSalary] = useState(0)
    const [x, setX] = useState(0)
    const [y, setY] = useState(0)
    return <Box width={'300px'}>
        <Heading m={0} align={'center'} as={'h2'}>Add user</Heading>
        <Input onChange={(e) => {
            setName(e.target.value)
        }}
               m={'5px 0'} placeholder='name'/>
        <Input m={'5px 0'} placeholder='salary' onChange={(e) => {
            setSalary(e.target.value)
        }}/>
        <Input m={'5px 0'} placeholder='x' onChange={(e) => {
            setX(e.target.value)
        }}/>
        <Input m={'5px 0'} placeholder='y' onChange={(e) => {
            setY(e.target.value)
        }}/>
        <Button m={'5px 0'} h={'42px'} w={'100%'} border={'1px solid green'} bg={'green'}
                borderRadius={'4px'} onClick={() => {
            createWorker({
                name: name,
                salary: salary,
                x: x,
                y: y
            }).then(()=> {
                alert("User created")
                onClose()
            })
        }}>Создать</Button>
    </Box>
}
export default AddUser;
