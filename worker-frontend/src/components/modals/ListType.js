import {Box, Button, Menu, MenuButton, MenuItem, MenuList} from "@chakra-ui/react";

const ListType = ({onClose, listType, setListType}) => {
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
                    {listType}
                </MenuButton>
                <MenuList>
                    <MenuItem onClick={() => {
                        setListType('ALL')
                        onClose()
                    }}>ALL</MenuItem>
                    <MenuItem onClick={() => {
                        setListType('HIRED')
                        onClose()
                    }}>HIRED</MenuItem>
                    <MenuItem onClick={() => {
                        setListType('RECOMMENDED_FOR_PROMOTION')
                        onClose()
                    }}>RECOMMENDED_FOR_PROMOTION</MenuItem>
                    <MenuItem onClick={() => {
                        setListType('FIRED')
                        onClose()
                    }}>FIRED</MenuItem>
                </MenuList>
            </Menu>
        </Box>
    </Box>
}
export default ListType