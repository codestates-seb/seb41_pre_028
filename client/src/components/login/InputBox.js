import { useForm } from "react-hook-form";
import PropTypes from "prop-types";
import styled from "styled-components";
// import { LoginAPI } from "../api/LoginAPI";
// import { SignupAPI } from "../api/SignupAPI";

import { loginUser } from "../../store/loginSlice";
import { SignupUser } from "../../store/signupSlice";

import { useDispatch, useSelector } from "react-redux";
import ClipLoader from "react-spinners/ClipLoader";
import { useNavigate } from "react-router-dom";
import Button from "../button/Button";
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

const InputBox = ({ isSignup }) => {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const { isFetching, isLogined } = useSelector((state) => state.login);
  console.log("isFetching", isFetching);
  console.log("isLogined", isLogined);

  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm();

  const onClickLogin = (data) => {
    if (data.nickname) {
      dispatch(SignupUser(data)).then(() =>
        navigate("/login", { replace: true })
      );
    } else if (!data.nickname) {
      dispatch(loginUser(data));
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
            className="border-[#f1f2f3] border-solid border-2 rounded h-9"
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
        <label htmlFor="email" className="mb-1 font-bold">
          Email
        </label>
        <input
          id="email"
          type="text"
          name="email"
          className="border-[#f1f2f3] border-solid border-2 rounded h-9"
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
        <label htmlFor="password" className="mb-1 font-bold">
          Password
        </label>
        <input
          id="password"
          type="password"
          className="border-[#f1f2f3] border-solid border-2 rounded h-9"
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
        {!isLogined ? (
          <button className="flex justify-center items-center bg-[#0995ff] rounded text-white mt-3  p-1.5 hover:bg-[#0162bf]">
            {isFetching && <ClipLoader className="mr-2" size={20} />}
            {isSignup ? "Sign Up" : "Log in"}
          </button>
        ) : (
          <Button text={"text"}></Button>
        )}
      </form>
    </div>
  );
};

export default InputBox;

InputBox.propTypes = {
  isSignup: PropTypes.bool,
};
