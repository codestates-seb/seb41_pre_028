import styled from "styled-components";

const PrimaryButton = styled.button`
  background-color: #0091ff;
  border: 1px solid #0091ff;
  color: white;
  font-size: 13px;
  padding: 0.4rem;
  border-radius: 3px;
`;

const SecondaryButton = styled.button`
  background-color: #e1ecf4;
  border: 1px solid #7aa7c7;
  color: #39739d;
  font-size: 13px;
  padding: 0.4rem 0.5rem;
  border-radius: 3px;
`;

const TabButton = styled.button`
  color: #525960;
  padding: 6px 12px;
  border-radius: 1000px;
  border: none;
  line-height: 1.2;
  &:hover {
    background-color: #e3e6e8;
  }
`;

export { PrimaryButton, SecondaryButton, TabButton };
