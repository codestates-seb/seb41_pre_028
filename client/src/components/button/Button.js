import styled from "styled-components";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCircleCheck } from "@fortawesome/free-solid-svg-icons";

const StyledButton = styled.button`
  display: flex;
  justify-content: center;
  align-items: center;
  color: white;
  background-color: green;
  padding: 8px;
  border-radius: 5px;
  margin-top: 5px;
`;

const Button = () => {
  return (
    <StyledButton>
      <FontAwesomeIcon icon={faCircleCheck} />
      <div className="ml-1.5">Logined!!!</div>
    </StyledButton>
  );
};
export default Button;

//  styled component 를 활용해서 버튼들을 저장하면 좋을 것 같음
/**
 * https://www.google.com/search?q=styled+components+mui&newwindow=1&sxsrf=ALiCzsbFhwq_nS8KPeTv-PacgvRhKpta3Q%3A1671732003831&ei=I5ukY96wMoXahwPI4aTQDQ&oq=styl&gs_lcp=Cgxnd3Mtd2l6LXNlcnAQAxgBMgQIIxAnMgQIABBDMgQIABBDMgUIABCABDILCAAQgAQQsQMQgwEyBQgAEIAEMgUIABCABDIFCAAQgAQyBQgAEIAEMgUIABCABDoKCAAQRxDWBBCwAzoGCCMQJxATOgYIABAKEEM6BwgAEIAEEAo6CwguEIAEEMcBENEDOggIABCABBCxAzoKCAAQsQMQgwEQQzoLCC4QgAQQxwEQrwE6BwguEIAEEApKBAhBGABKBAhGGABQ_QhYyBhg5SpoBHABeACAAdwBiAHABpIBBTAuNS4xmAEAoAEByAEKwAEB&sclient=gws-wiz-serp
 */
