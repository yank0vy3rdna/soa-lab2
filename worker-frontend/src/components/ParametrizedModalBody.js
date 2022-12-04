import AddUser from "./AddUser";
import MaxOrg from "./MaxOrg";
import {Box, Flex} from "@chakra-ui/react";

const ParametrizedModalBody = ({modalType}) => {
    let body;
    if (modalType === "addUser") {
        body = <AddUser/>
    } else {
        body = <MaxOrg/>
    }

    return <Box h={"200px"} w={"200px"} bg={"orange"} borderRadius={'4px'} transform={'translateY(40vh)'} p={'20px'}>
        <Flex
            flexDirection={'column'}
            w={'100%'}
        >
            {body}
        </Flex>
    </Box>
}
export default ParametrizedModalBody
