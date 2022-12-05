import axios from 'axios';

const domainUrl = 'http://localhost:8080/workers';
const hrUrl = 'http://localhost:8080/hr';

let convert = require('xml-js');

export const getListOfWorkers = async (limit, pages, sorters, filters) => {
    const sortExp = (sorters.length > 0 ? "&" : "") + sorters.map(item => "sort_by=" + item).join('&')
    const filtersExp = (filters.length > 0 ? "&" : "") + filters.map(item => "filter=" + item).join('&')
    const getWorkersList = `${domainUrl}?limit=${limit}&page=${pages}${sortExp}${filtersExp}`;
    const res = await axios.get(getWorkersList).catch((err) => alert('error:' + err))
    return convert.xml2json(res.data, {compact: true, spaces: 4})
}
export const getListOfWorkersByStatus = async (limit, pages, status) => {
    const getWorkersList = `${domainUrl}/list-by-status/${status}?limit=${limit}&page=${pages}`;
    const res = await axios.get(getWorkersList).catch((err) => alert('error:' + err))
    return convert.xml2json(res.data, {compact: true, spaces: 4})
}
export const deleteWorker = async (workerId) => {
    const deleteWorkerUrl = `${domainUrl}?workerId=${workerId}`;
    await axios.delete(deleteWorkerUrl).catch((err) => alert('error:' + err))
}
export const deleteWorkerByStatus = async (status) => {
    const deleteWorkerUrl = `${domainUrl}/delete-by-status?status=${status}`;
    await axios.delete(deleteWorkerUrl).catch((err) => alert('error:' + err))
}
export const getWorkerWithMaxOrg = async () => {
    const res = await axios.get(`${domainUrl}/max-organization`).catch((err) => alert('error:' + err))
    return convert.xml2json(res.data, {compact: true, spaces: 4})
}

export const createWorker = (userData) => {
    return axios.put(domainUrl, getWorkerInputXml(userData), {
        headers: {
            "Content-Type": "application/xml"
        }
    })
        .then((res) => res.data)
        .catch((err) => alert('error:' + err))
};
const getWorkerInputXml = (userData) => {
    return `<?xml version="1.0" encoding="UTF-8"?>
<workerInput>
    <name>${userData.name}</name>
    <coordinates>
        <x>${userData.x}</x>
        <y>${userData.y}</y>
    </coordinates><status>HIRED</status>
    <salary>${userData.salary}</salary>
</workerInput>`
}

export const updateWorker = (userData, workerId) => {

    return axios.patch(`${domainUrl}?workerId=${workerId}`, getWorkerInputXml(userData), {
        headers: {
            "Content-Type": "application/xml"
        }
    })
        .then((res) => res.data)
        .catch((err) => alert('error:' + err))
};

export const indexing = async (coefficient, workerId) => {
    return await axios.post(hrUrl + `/index/${workerId}/${coefficient}`)
        .catch((err) => alert('error:' + err))
};
export const fire = async (workerId) => {
    return await axios.post(hrUrl + `/fire/${workerId}`)
        .catch((err) => alert('error:' + err))
};