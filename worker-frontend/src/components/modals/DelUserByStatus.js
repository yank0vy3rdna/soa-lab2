import {Box, Button, Menu, MenuButton, MenuItem, MenuList} from "@chakra-ui/react";
import {useState} from "react";
import {Button as MyButton} from '../button/Button'
import {deleteWorkerByStatus} from "../../api/profile/requests";

const DelUserByStatus = ({onClose}) => {
    const [statusToDelete, setStatusToDelete] = useState('HIRED')
    return <Box>
        <Box m={'10px 0'}>
            <Menu>
                <MenuButton as={Button} px={4}
                            py={2}
                            transition='all 0.2s'
                            borderRadius='md'
                            borderWidth='1px'
                            width={'300px'}
                            _hover={{bg: 'gray.400'}}
                            _expanded={{bg: 'blue.400'}}
                            _focus={{boxShadow: 'outline'}}>
                    {statusToDelete}
                </MenuButton>
                <MenuList>
                    <MenuItem onClick={() => {
                        setStatusToDelete('HIRED')
                    }}>HIRED</MenuItem>
                    <MenuItem onClick={() => {
                        setStatusToDelete('RECOMMENDED_FOR_PROMOTION')
                    }}>RECOMMENDED_FOR_PROMOTION</MenuItem>
                    <MenuItem onClick={() => {
                        setStatusToDelete('FIRED')
                    }}>FIRED</MenuItem>
                </MenuList>
            </Menu>
        </Box>
        <Box m={'10px 0'}>
            <MyButton text={"Delete random user with selected status"} onClick={()=>{
                deleteWorkerByStatus(statusToDelete).then(r => {onClose()})
            }} color={'black'} bgColor={'red'}/>
        </Box>
    </Box>
}
export default DelUserByStatus