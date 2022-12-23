// import { useState } from "react";
import { useForm } from "react-hook-form";
import PropTypes from "prop-types";
import styled from "styled-components";
import axios from "axios";
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
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm();

  const onClickLogin = (data) => {
    console.log(data);
    if (data.nickName) {
      axios
        .post("/users/signup", {
          nickname: data.nickName,
          password: data.password,
          email: data.email,
        })
        .then((res) => {
          if (res.status === 200) {
            console.log("회원가입 성공");
          }
        });
    } else if (!data.nickName) {
      axios
        .post("/users/login", {
          email: data.email,
          password: data.password,
        })
        .then((res) => {
          if (res.status === 200) {
            console.log("로그인 성공");
          }
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
            id="nickName"
            type="text"
            name="nickName"
            className="border-[#f1f2f3] border-solid border-2 rounded h-9"
            required
            {...register("nickName", {
              maxLength: 20,
              minLength: 2,
            })}
          ></input>
        ) : null}
        {errors.nickName && (
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
        <button className="bg-[#0995ff] rounded text-white mt-3 p-1.5 hover:bg-[#0162bf]">
          {isSignup ? "Sign Up" : "Log in"}
        </button>
      </form>
    </div>
  );
};

export default InputBox;

InputBox.propTypes = {
  isSignup: PropTypes.bool,
};
