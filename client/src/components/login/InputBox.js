import { useForm } from "react-hook-form";
import PropTypes from "prop-types";
import styled from "styled-components";
import { loginUser } from "../../store/loginSlice";
import { SignupUser } from "../../store/signupSlice";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import Button from "../button/Button";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCircleCheck, faLock } from "@fortawesome/free-solid-svg-icons";
import ClipLoader from "react-spinners/ClipLoader";
import { setCookie } from "../../utils/cookie";
const ErrSign = styled.div`
  background-color: rgba(255, 0, 0, 0.2);
  border: 1px solid red;
  border-radius: 5px;
  color: red;
  font-weight: bold;
  display: flex;
  justify-content: center;
  align-items: center;
`;

const InputBox = ({ isSignup, isLogin }) => {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const { isLogined, isFetching, authorizationToken } = useSelector(
    (state) => state.login
  );
  const { isSignedUp, isSuccess } = useSelector((state) => state.signup);
  console.log(isLogined);
  console.log(authorizationToken);
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm();
  const onClickLogin = (data) => {
    if (data.nickname) {
      dispatch(SignupUser(data)).then((res) => {
        if (!res.error) {
          alert(`${data.nickname}님 환영합니다!`);
          navigate("/login", { replace: true });
        }
      });
    } else if (!data.nickname) {
      dispatch(loginUser(data)).then((res) => {
        if (!res.error) {
          setCookie("Authorization", res.payload.headers.authorization, {
            path: "/",
          });
          setTimeout(() => {
            // navigate("/", { replace: true });
            window.location.replace("/");
          }, 1000); /**setTimeout은 일부러 추가했습니다.서버가 지연될시 로딩중임을 보여주기위해 */
        }
        return;
      });
    }
  };

  const onError = (error) => {
    console.log(error);
  };

  return (
    <div>
      <form
        className="flex flex-col p-5 bg-white rounded mt-5 shadow-lg "
        onSubmit={handleSubmit(onClickLogin, onError)}
      >
        {isSignup ? (
          <label htmlFor="displayname" className="mb-1 font-bold">
            Display name
          </label>
        ) : null}
        {isSignup ? (
          <input
            id="nickname"
            type="text"
            name="nickname"
            className="border-[#dcdfe1] border-solid border-2 rounded h-9 mb-2"
            required
            {...register("nickname", {
              maxLength: 20,
              minLength: 2,
            })}
          ></input>
        ) : null}
        {errors.nickname && (
          <ErrSign>
            {<span>{"닉네임은 2글자이상 20글자 미만으로 작성해주세요."}</span>}
          </ErrSign>
        )}
        <label htmlFor="email" className="mb-1 font-bold ">
          Email
        </label>
        <input
          id="email"
          type="text"
          name="email"
          className="border-[#dcdfe1] border-solid border-2 rounded h-9"
          required
          {...register("email", {
            pattern: {
              value: /[a-z0-9]+@[a-z]+\.[a-z]{2,3}/,
              message: "이메일 형식이 아닙니다.",
            },
          })}
        ></input>
        {errors.email && (
          <ErrSign>{<span>{errors?.email.message}</span>}</ErrSign>
        )}

        <label htmlFor="password" className="mb-1 mt-2 font-bold">
          Password
        </label>
        <input
          id="password"
          type="password"
          className="border-[#dcdfe1] border-solid border-2 rounded h-9"
          required
          {...register("password", {
            pattern: {
              value:
                /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}$/g,
              message: "비밀번호 양식에 맞춰 입력해주세요.",
            },
          })}
        ></input>
        {errors.password && (
          <ErrSign>{<span>{errors?.password.message}</span>}</ErrSign>
        )}
        {isSignup ? (
          <p className="text-[11px]">
            Passwords must contain at least eight characters, including at least
            1 letter and 1 number.
          </p>
        ) : null}

        <div>
          {isLogin && (
            <Button bgColor={isLogined && "green"}>
              {!isLogined && !isFetching ? (
                <FontAwesomeIcon icon={faLock} className="mr-1.5" />
              ) : null}
              {isLogined && (
                <FontAwesomeIcon
                  icon={faCircleCheck}
                  className="mr-1.5"
                  bounce
                />
              )}
              {isFetching && <ClipLoader className="mr-2" size={20} />}
              {isLogined === true ? (
                <span className="font-bold ">Logined</span>
              ) : isFetching === true ? (
                <span className="font-bold ">Waiting...</span>
              ) : (
                <span className="font-bold ">Login</span>
              )}
            </Button>
          )}
          {isSignup && (
            <Button bgColor={isSignedUp && "green"}>
              {isSignedUp && <FontAwesomeIcon icon={faCircleCheck} bounce />}
              {isSuccess && <ClipLoader className="mr-2" size={20} />}
              {isSuccess ? (
                <span className="font-bold ">Waiting...</span>
              ) : (
                <span className="font-bold ">Signup</span>
              )}
            </Button>
          )}
        </div>
      </form>
    </div>
  );
};

export default InputBox;

InputBox.propTypes = {
  isSignup: PropTypes.bool,
};
