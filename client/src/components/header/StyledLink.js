import { Link } from "react-router-dom";
import styled from "styled-components";

const PrimaryLink = styled(Link)`
  background-color: #0091ff;
  border: 1px solid #0091ff;
`;

const SecondaryLink = styled(Link)`
  background-color: #e1ecf4;
  border: 1px solid #7aa7c7;
`;

export { PrimaryLink, SecondaryLink };
