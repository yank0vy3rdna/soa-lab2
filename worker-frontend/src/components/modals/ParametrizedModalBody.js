import AddUser from "./AddUser";
import MaxOrg from "./MaxOrg";
import {Flex} from "@chakra-ui/react";
import UpdateUser from "./UpdateUser";
import DelUserByStatus from "./DelUserByStatus";
import ListType from "./ListType";

const ParametrizedModalBody = ({modalType, onClose, listType, setListType}) => {
    let body;
    switch (modalType) {
        case "addUser":
            body = <AddUser onClose={onClose}/>
            break
        case "maxOrg":
            body = <MaxOrg onClose={onClose}/>
            break
        case "updateUser":
            body = <UpdateUser onClose={onClose}/>
            break
        case "deleteUserByStatus":
            body = <DelUserByStatus onClose={onClose}/>
            break
        case "setListType":
            body = <ListType listType={listType} setListType={setListType} onClose={onClose}/>
            break
        default:
            body = <>Unexpected error</>
            break
    }

    return <Flex
        p={"20px"} zIndex={10000} bg={"orange"} borderRadius={'4px'}
        transform={'translateY(40vh)'}
        flexDirection={'column'}
    >
        {body}
    </Flex>
}
export default ParametrizedModalBody
