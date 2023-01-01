import { Link } from "react-router-dom";
import styled from "styled-components";
import { media } from "../utils/style-utils";

const PrimaryLink = styled(Link)`
  background-color: #0091ff;
  border: 1px solid #0091ff;
  color: white;
  font-size: 13px;
  padding: 5px;
  border-radius: 3px;

  ${media.mobile`
    font-size:11px;
    padding: 5px 3px;
  `}
`;

const SecondaryLink = styled(Link)`
  background-color: #e1ecf4;
  border: 1px solid #7aa7c7;
  color: #39739d;
  font-size: 13px;
  padding: 5px;
  border-radius: 3px;
  ${media.mobile`
    font-size:11px;
    padding: 5px 3px;
  `}
`;

export { PrimaryLink, SecondaryLink };
