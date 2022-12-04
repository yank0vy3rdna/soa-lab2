import React, {useEffect, useState} from "react"
import {getListOfWorkers} from "../../api/profile/requests";
import styled from "styled-components";
import {CircleContainer, StyledHeading, Worker} from "../worker/Worker";
import ArrowBackIcon from '@mui/icons-material/ArrowBack';
import ArrowForwardIcon from '@mui/icons-material/ArrowForward';

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


export const Workers = () => {
    const [hashSalary, setHashSalary] = useState(0)
    const [hashId, setHashId] = useState(0)
    const [hashName, setHashName] = useState(0)

    const [work, setWork] = useState([])
    const [page, setPage] = useState(1)

    const fetchList = function () {
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
        getListOfWorkers(4, page, sorters).then((data) => {
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
                setWork(data)
            }
        })
    }
    useEffect(() => {
        fetchList()
    }, [page, hashSalary, hashId, hashName])


    return <StyledWrapperWorkers>
        <StyledHashTags>
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
        <StyledWorkers>
            {(work !== undefined) ? work.map((item) =>
                <Worker data={item} key={item.id._text} fetchList={fetchList}/>
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
    </StyledWrapperWorkers>
}
