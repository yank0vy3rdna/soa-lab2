import axios from 'axios';

const domainUrl = 'http://localhost:8080/workers';
const hrUrl = 'http://localhost:8080/hr';

let convert = require('xml-js');

export const getListOfWorkers = async (limit, pages, sorters) => {
    const sortExp = (sorters.length > 0 ? "&" : "") + sorters.map(item => "sort_by=" + item).join('&')
    const getWorkersList = `${domainUrl}?limit=${limit}&page=${pages}${sortExp}`;
    const res = await axios.get(getWorkersList).catch((err) => alert('error:' + err))
    return convert.xml2json(res.data, {compact: true, spaces: 4})
}
export const deleteWorker = async (workerId) => {
    const deleteWorkerUrl = `${domainUrl}?workerId=${workerId}`;
    await axios.delete(deleteWorkerUrl).catch((err) => alert('error:' + err))
}

export const createWorker = (userData) => {
    const xml = `<?xml version="1.0" encoding="UTF-8"?>
<workerInput>
    <name>${userData.name}</name>
    <coordinates>
        <x>${userData.x}</x>
        <y>${userData.y}</y>
    </coordinates><status>HIRED</status>
    <salary>${userData.salary}</salary>
</workerInput>`
    return axios.put(domainUrl, xml, {
        headers: {
            "Content-Type": "application/xml"
        }
    })
        .then((res) => res.data)
        .catch((err) => alert('error:' + err))
};

//
// export const deleteWorkerByStatus= (status) => {
//     return axios.delete(profileUrls.api, status, {
//         headers: {
//             'content-type': `application/xml`
//         }
//     })
//         .then((res) => res.data)
//         .catch((err) => console.log('error:', err))
// };
//
export const indexing = (coefficient, workerId) => {
    return axios.post(hrUrl + `/index/${workerId}/${coefficient}`)
        .then((res) => res.data)
        .catch((err) => alert('error:' + err))
};
export const fire = (workerId) => {
    return axios.post(hrUrl + `/fire/${workerId}`)
        .then((res) => res.data)
        .catch((err) => alert('error:' + err))
};
//
// export const firing= (id) => {
//     return axios.post(profileUrls.api, id, {
//         headers: {
//             'content-type': `application/xml`
//         }
//     })
//         .then((res) => res.data)
//         .catch((err) => console.log('error:', err))
// };

