import styled from "styled-components";

export const PageContainer = styled.div`
  width: 100vw;
  display: flex;
  justify-content: center;
`;

export const MainContainer = styled.div`
  padding-top: var(--h-header);
  display: flex;
  max-width: 1264px;
  width: 100%;
  > .side-bar {
    width: 164px;
  }
  > .content {
    width: calc(100% - 164px);
    max-width: 1100px;
    background-color: red;
    padding: 24px;
  }
`;
