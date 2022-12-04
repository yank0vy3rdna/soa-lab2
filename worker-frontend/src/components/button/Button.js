import React from 'react';
import styled from "styled-components";

const StyledButton = styled.button`{
  color: aliceblue;
  width: 160px;
  height: 50px;
  border: 1px solid ${props => props.color};
  background: ${props => props.bgColor};
  font-family: 'Chalkboard', sans-serif;
  font-size: 16px;
  border-radius: 4px;
}`



export const Button = ({text, color, bgColor, ...props}) => {
    return <StyledButton color={color} bgColor={bgColor} {...props}>
        {text}
    </StyledButton>
}
