import React from 'react';
import styled from 'styled-components';

const PlusBtn1 = styled.button`
  font-size: 1rem;
  align-items: center;
  justify-content: center;
  display: flex;
  color: blue;
  width: 1.5rem;
  height: 1.5rem;
  border: 0.1rem solid black;
  border-radius: 100%;
  background-color: white;
`;

export default function PlusBtn() {
  return <PlusBtn1>+</PlusBtn1>;
}
