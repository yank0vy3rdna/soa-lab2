import React, {useState} from 'react';
import styled from "styled-components";
import {Button} from "../components/button/Button";
import {Workers} from "../components/workers/Workers";
import {
    Box, Modal,
    ModalContent,
    ModalOverlay,
    useDisclosure
} from "@chakra-ui/react";
import * as PropTypes from "prop-types";
import ParametrizedModalBody from "../components/modals/ParametrizedModalBody";

const StyledPanelWrapper = styled.section`{
  position: relative;
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}`

const StyledPanel = styled.div`{
  position: relative;
  margin-top: 100px;
  display: flex;
  flex-direction: column;
  width: 1200px;
}`

const Heading = styled.h1`{
  font-family: "Al Bayan", sans-serif;
  font-size: 28px;
  font-weight: 700;
  text-align: center;
}`

const ButtonWrapper = styled.div`{
  display: flex;
  max-width: 1200px;
  justify-content: space-between;
  padding: 20px 50px;
  align-items: center;
}`

const buttonColor = '#abcaff'

ParametrizedModalBody.propTypes = {modalType: PropTypes.any};
export const AdminPanel = () => {
    const {isOpen, onOpen, onClose} = useDisclosure()
    const [modalType, setModalType] = useState("addUser");
    const [listType, setListType] = useState('ALL')

    const [workerToUpdate, setWorkerToUpdate] = useState(null)

    return <StyledPanelWrapper>
        <Box position={'absolute'} w={'100vw'} h={'100vh'}>
            <Modal isOpen={isOpen} onClose={onClose} isCentered>
                <ModalOverlay zIndex={5000} style={{opacity: "25%"}}
                              backdropFilter='auto'
                              backdropBlur='2px' onClick={onClose}>
                    <ModalContent style={{
                        zIndex: 10000,
                        position: 'relative',
                        width: '100vw',
                        height: '0px',
                        display: 'flex',
                        alignItems: 'center',
                        justifyContent: 'center'
                    }}>
                        <ParametrizedModalBody modalType={modalType} onClose={onClose} listType={listType}
                                               setListType={setListType} workerToUpdate={workerToUpdate}/>
                    </ModalContent></ModalOverlay>
            </Modal>
        </Box>
        <StyledPanel>
            <Heading>Панель управления</Heading>
            <ButtonWrapper>
                <div onClick={() => {
                    setModalType("addUser");
                }}>
                    <Button text={'Add user'} onClick={onOpen} bgColor={buttonColor}/>
                </div>
                <div onClick={() => {
                    setModalType("deleteUserByStatus");
                }}>
                    <Button text={'Del user by status'} onClick={onOpen} bgColor={buttonColor}/>
                </div>
                <div onClick={() => {
                    setModalType("maxOrg");
                }}>
                    <Button text={'Get with Max Org'} onClick={onOpen} bgColor={buttonColor}/>
                </div>
                <div onClick={() => {
                    setModalType("setListType");
                }}>
                    <Button text={'Set list type'} onClick={onOpen} bgColor={buttonColor}/>
                </div>
            </ButtonWrapper>
            <Workers isOpen={isOpen} listType={listType} updateWorker={(workerData) => {
                setWorkerToUpdate(workerData)
                setModalType("updateUser")
                onOpen()
            }}/>
        </StyledPanel>
    </StyledPanelWrapper>
}
