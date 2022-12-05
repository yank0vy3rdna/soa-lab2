import {useState} from "react";
import {Box, Button, Heading, Input} from "@chakra-ui/react";
import {updateWorker} from "../../api/profile/requests";

const UpdateUser = ({workerData, onClose}) => {
    const [name, setName] = useState(workerData.name._text)
    const [salary, setSalary] = useState(workerData.salary._text)
    const [x, setX] = useState(workerData.coordinates.x._text ?? 0)
    const [y, setY] = useState(workerData.coordinates.x._text ?? 0)
    return <Box width={'300px'}>
        <Heading m={0} align={'center'} as={'h2'}>Update user</Heading>
        <Input value={name} onChange={(e) => {
            setName(e.target.value)
        }}
               m={'5px 0'} placeholder='name'/>
        <Input value={salary} m={'5px 0'} placeholder='salary' onChange={(e) => {
            setSalary(e.target.value)
        }}/>
        <Input value={x} m={'5px 0'} placeholder='x' onChange={(e) => {
            setX(e.target.value)
        }}/>
        <Input value={y} m={'5px 0'} placeholder='y' onChange={(e) => {
            setY(e.target.value)
        }}/>
        <Button m={'5px 0'} h={'42px'} w={'100%'} border={'1px solid green'} bg={'green'}
                borderRadius={'4px'} onClick={() => {
            updateWorker({
                name: name,
                salary: salary,
                x: x,
                y: y
            }, workerData.id._text).then(()=> {
                onClose()
            })
        }}>Update</Button>
    </Box>
}
export default UpdateUser