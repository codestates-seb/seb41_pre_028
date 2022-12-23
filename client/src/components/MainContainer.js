import styled from "styled-components";

const MainContainer = styled.div`
  padding-top: var(--h-header);
  display: flex;
  max-width: 1264px;
  width: 100%;
  justify-content: space-between;
  background: none;
  > .side-bar {
    width: 164px;
  }
  > main {
    width: calc(100% - 164px);
    max-width: 1100px;
    background-color: red;
  }
`;

export default MainContainer;
