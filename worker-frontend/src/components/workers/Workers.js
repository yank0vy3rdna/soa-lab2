import React, {useEffect, useState} from "react"
import {getListOfWorkers, getListOfWorkersByStatus} from "../../api/profile/requests";
import styled from "styled-components";
import {CircleContainer, StyledHeading, Worker} from "../worker/Worker";
import ArrowBackIcon from '@mui/icons-material/ArrowBack';
import ArrowForwardIcon from '@mui/icons-material/ArrowForward';
import {Box, Flex, Heading, Input, Text} from "@chakra-ui/react";

const StyledWrapperWorkers = styled.div`{
  display: flex;
  flex-direction: column;
  justify-content: center;
  width: 100%;
  align-items: center;
}`

const StyledPagination = styled.div`{
  padding: 0 40px;
  max-width: 1000px;
  width: 100%;
  display: flex;
  margin-top: 10px;
  justify-content: space-between;
  align-items: center;
}`

const StyledWorkers = styled.div`{
  width: 100%;
  padding: 40px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}`

const IconWrapper = styled.div`{
  font-size: large;
  color: darkorange;

  :hover {
    color: red;
  }
}`

const HashTag = styled.div`{
  padding: 5px;
  margin: 10px;
  border-radius: 4px;
  font-size: 18px;
  border: 1px solid ${props => {
    if (props.state === 0) {
        return 'gray'
    }
    if (props.state === 1) {
        return 'green'
    }
    if (props.state === 2) {
        return 'red'
    }
}};
  color: ${props => {
    if (props.state === 0) {
        return 'gray'
    }
    if (props.state === 1) {
        return 'green'
    }
    if (props.state === 2) {
        return 'red'
    }
}};
  font-family: 'Chalkboard', sans-serif, bold;
}`

const StyledHashTags = styled.div`{
  padding: 0 20px;
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}`
const makeFiltersUrl = (filters) => {
    const results = []
    for (const filter in filters) {
        if (filters[filter].enabled && filters[filter].value !== '') {
            results.push(`${filter} ${filters[filter].action} ${filters[filter].value}`)
        }
    }
    return results
}
const possibleFilters = [
    {
        'field': 'name',
        'possible_actions': ['eq']
    },
    {
        'field': 'salary',
        'possible_actions': ['eq', 'gt', 'ge', 'lt', 'le']
    }
]

export const Workers = ({isOpen, listType, updateWorker}) => {
    const [hashSalary, setHashSalary] = useState(0)
    const [hashId, setHashId] = useState(0)
    const [hashName, setHashName] = useState(0)
    const [filters, setFilters] = useState({
        "name": {
            "enabled": false,
            "action": "eq",
            "value": ""
        },
        "salary": {
            "enabled": false,
            "action": "eq",
            "value": ""
        }
    })
    const switchFilter = (filter) => {
        const obj = {
            ...filters,
        }
        obj[filter] = {
            ...filters[filter],
            enabled: !filters[filter].enabled
        }
        setFilters(obj)
    }
    const changeFilterValue = (filter, value) => {
        const obj = {
            ...filters,
        }
        obj[filter] = {
            ...filters[filter],
            value: value
        }
        setFilters(obj)
    }

    const changeFilterAction = (filter, action) => {
        const obj = {
            ...filters,
        }
        obj[filter] = {
            ...filters[filter],
            action: action
        }
        setFilters(obj)
    }

    const [workers, setWorkers] = useState([])
    const [page, setPage] = useState(1)

    const parseResults = (data) => {
        data = JSON.parse(data).workers.worker
        if (data === undefined) {
            data = []
        }
        if (!Array.isArray(data)) {
            data = [data]
        }
        if (page > 1 && data.length === 0) {
            setPage(1)
        } else {
            setWorkers(data)
        }
    }
    const fetchList = function () {
        if (listType === "ALL") {
            const sorters = []
            if (hashSalary === 1) {
                sorters.push("salary")
            }
            if (hashSalary === 2) {
                sorters.push("salary desc")
            }
            if (hashId === 1) {
                sorters.push("id")
            }
            if (hashId === 2) {
                sorters.push("id desc")
            }
            if (hashName === 1) {
                sorters.push("name")
            }
            if (hashName === 2) {
                sorters.push("name desc")
            }
            getListOfWorkers(4, page, sorters, makeFiltersUrl(filters)).then(parseResults)
        } else {
            getListOfWorkersByStatus(4, page, listType).then(parseResults)
        }
    }
    useEffect(() => {
        fetchList()
    }, [page, hashSalary, hashId, hashName, isOpen, listType, filters])
    console.log(makeFiltersUrl(filters))
    let hashTags = <></>
    let filtersJsx = <></>
    if (listType === 'ALL') {
        const makeOption = (option) => <option key={option} value={option}>{option}</option>
        const makeFilter = (filter) => <Box p={'20px'} key={filter.field} display={'flex'} flexDir={'column'}>
            {filter.field}
            <div>Enabled: <Input type='checkbox' value={filter.enabled} onInput={() => {
                switchFilter(filter.field)
            }}/></div>
            <select value={filters[filter.field].action} onInput={(e) => {
                changeFilterAction(filter.field, e.target.value)
            }}>
                {filter.possible_actions.map(makeOption)}
            </select>
            <Input value={filters[filter.field].value} onChange={(e) => {
                changeFilterValue(filter.field, e.target.value)
            }}/>
        </Box>
        hashTags = <StyledHashTags>
            <HashTag state={hashSalary} onClick={() => setHashSalary((hashSalary + 1) % 3)}>
                #salary
            </HashTag>
            <HashTag state={hashId} onClick={() => setHashId((hashId + 1) % 3)}>
                #id
            </HashTag>
            <HashTag state={hashName} onClick={() => setHashName((hashName + 1) % 3)}>
                #name
            </HashTag>
        </StyledHashTags>
        filtersJsx = <div>
            <Heading as={'h5'}>Filters</Heading>
            <Flex style={{
                padding: '0 20px',
                display: 'flex',
                justifyContent: 'space-between',
                alignItems: 'center',
            }}>
                {
                    possibleFilters.map(makeFilter)
                }

            </Flex></div>
    }
    let workersList = <>
        <StyledWorkers>
            {(workers !== undefined) ? workers.map((item) =>
                <Worker updateWorker={updateWorker} data={item} key={item.id._text} fetchList={fetchList}/>
            ) : <></>}
        </StyledWorkers>
        <StyledPagination>
            <IconWrapper onClick={() => {
                setPage(page > 1 ? page - 1 : 1)
            }}>
                <ArrowBackIcon/>
            </IconWrapper>
            <CircleContainer>
                <StyledHeading
                    size={'18px'} align={'center'}
                >
                    {page}
                </StyledHeading>
            </CircleContainer>
            <IconWrapper onClick={() => setPage(page + 1)}>
                <ArrowForwardIcon/>
            </IconWrapper>
        </StyledPagination>
    </>
    const noWorkersHere = <Text>No workers here</Text>
    if (workers === undefined) {
        workersList = noWorkersHere
        hashTags = <></>
    } else if (workers.length === 0) {
        workersList = noWorkersHere
        hashTags = <></>
    }
    return <StyledWrapperWorkers>
        <Heading as='h4'>List type: {listType}</Heading>
        {hashTags}
        {filtersJsx}
        {workersList}
    </StyledWrapperWorkers>
}
