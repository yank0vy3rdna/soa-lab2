import React, {useState} from 'react';
import styled from "styled-components";
import {Button} from "../components/button/Button";
import {Workers} from "../components/workers/Workers";
import {
    Box,
    Modal,
    ModalBody,
    ModalCloseButton,
    ModalContent, ModalFooter,
    ModalHeader,
    ModalOverlay,
    useDisclosure
} from "@chakra-ui/react";
import AddUser from "../components/AddUser";
import * as PropTypes from "prop-types";
import ParametrizedModalBody from "../components/ParametrizedModalBody";

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


ParametrizedModalBody.propTypes = {modalType: PropTypes.any};
export const AdminPanel = () => {
    const {isOpen, onOpen, onClose} = useDisclosure()
    const [modalType, setModalType] = useState("addUser");


    return <StyledPanelWrapper>
        <Box position={'absolute'}>
            <Modal isOpen={isOpen} onClose={onClose} isCentered
            >
                <ModalOverlay style={{opacity: "25%"}}
                              backdropFilter='auto'
                              backdropBlur='2px'/>
                <ModalContent style={{
                    background: 'black',
                    width: '0px',
                    zIndex: 10000,
                    left: '45%',
                }}>
                    <ParametrizedModalBody modalType={modalType}/>}
                </ModalContent>
            </Modal>
        </Box>
        <StyledPanel>
            <Heading>Панель управления</Heading>
            <ButtonWrapper>
                <div onClick={() => {
                    setModalType("addUser");
                }}>
                    <Button text={'Add user'} onClick={onOpen} color={'white'} bgColor={'red'}/>
                </div>
                <Button text={'Del user by status'} color={'darkgreen'} bgColor={'green'}/>
                <div onClick={() => {
                    setModalType("maxOrg");
                }}>
                <Button text={'Get with Max Org'} onClick={onOpen} color={'white'} bgColor={'blue'}/></div>
            </ButtonWrapper>
            <Workers/>
        </StyledPanel>
    </StyledPanelWrapper>
}
