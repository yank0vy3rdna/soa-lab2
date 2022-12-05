import {useEffect, useState} from "react";
import {getWorkerWithMaxOrg} from "../../api/profile/requests";
import {Worker} from "../worker/Worker";

const MaxOrg = () => {
    const [worker, setWorker] = useState({})
    const updateWorker = () => {
        getWorkerWithMaxOrg().then((workerJson) => {
            const workerParsed = JSON.parse(workerJson)
            setWorker(workerParsed)
        })
    }
    useEffect(() => {
        updateWorker()
    }, [])
    return <Worker data={worker.worker} fetchList={updateWorker}/>
}
export default MaxOrg;
