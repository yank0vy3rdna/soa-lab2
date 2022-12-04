import React, {useEffect, useState} from "react"
import styled from "styled-components";
import DeleteForeverIcon from '@mui/icons-material/DeleteForever';
import ThumbDownIcon from '@mui/icons-material/ThumbDown';
import {deleteWorker, fire, indexing} from "../../api/profile/requests";
import {Button, Input} from "@chakra-ui/react";

const StyledWorker = styled.div`{
  position: relative;
  width: 200px;
  height: 400px;
  background: rgba(255, 140, 0, 0.5);
  border: 2px solid darkorange;;
  text-align: left;
  border-radius: 4px;
  padding: 20px;
  display: flex;
  flex-direction: column;
  justify-content: left;
  align-items: center;
}`

export const StyledHeading = styled.h2`{
  width: 100%;
  font-size: ${props => props.size};
  text-align: ${props => props.align};
  font-family: 'Chalkboard', sans-serif, cursive;
  color: ${props => props.color};
}`

export const CircleContainer = styled.div`{
  border-radius: 50%;
  background: darkorange;
  color: white;
  border: 3px solid white;
  width: 50px;
  height: 50px;
  padding: 10px;
  font-size: 16px;
  text-align: center;
  font-family: 'Chalkboard', sans-serif, cursive;
}`

const IconWrapper = styled.div`{
  position: absolute;
  top: 20px;
  right: 20px;
  font-size: large;
  color: red;

  :hover {
    color: blue;
  }
}`

const IconSecondWrapper = styled.div`{
  position: absolute;
  top: 20px;
  left: 20px;
  font-size: large;
  color: red;

  :hover {
    color: blue;
  }
}`


export const Worker = ({data, fetchList}) => {
    const [coef, setCoef] = useState()
    const [workerData, setWorkerData] = useState({
        id: {
            _text: '',
        },
        name: {
            _text: '',
        },
        salary: {
            _text: '',
        },
        status: {
            _text: '',
        },
        organization: {
            fullName: {
                _text: '',
            },
            type: {
                _text: '',
            },
        }
    })
    useEffect(() => {
        setWorkerData(data)

    }, [])

    return <StyledWorker>
        <IconWrapper onClick={() => deleteWorker(workerData.id._text).then(() => fetchList())}>
            <DeleteForeverIcon/>
        </IconWrapper>
        <IconSecondWrapper onClick={() => fire(workerData.id._text).then(() => fetchList())}>
            <ThumbDownIcon/>
        </IconSecondWrapper>
        <CircleContainer>
            <StyledHeading
                size={'18px'} align={'center'}
            >
                {workerData.id._text}
            </StyledHeading>
        </CircleContainer>
        <StyledHeading size={'16px'} align={'left'} color={'red'}>Name: {workerData.name._text}</StyledHeading>
        <StyledHeading size={'16px'} align={'left'} color={'green'}>Salary: {workerData.salary._text}</StyledHeading>
        <StyledHeading size={'16px'} align={'left'} color={'blue'}>Status: {workerData.status._text}</StyledHeading>
        <StyledHeading size={'16px'} align={'left'}
                       color={'saddlebrown'}>OrgName: {workerData.organization.fullName._text}</StyledHeading>
        <StyledHeading size={'16px'} align={'left'}
                       color={'saddlebrown'}>OrgType: {workerData.organization.type._text}</StyledHeading>
        <StyledHeading size={'16px'} align={'left'} color={'saddlebrown'}>
            <Input m={'5px 0'} placeholder='coef'
                   onChange={(e) => {
                       setCoef(e.target.value)
                   }}/>
            <Button onClick={() => {
                indexing(coef, workerData.id._text).then(() => fetchList())
            }}>Index salary</Button>
        </StyledHeading>
    </StyledWorker>
}
