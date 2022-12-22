import ButtonBox from "../components/login/ButtonBox";
import Inputbox from "../components/login/InputBox";
const LoginPage = () => {
  return (
    <div className="flex justify-center items-center h-screen w-full bg-[#f1f2f3]">
      <div className="flex flex-col ">
        <div className="flex justify-center items-center mb-4">
          <img
            width={50}
            src="https://upload.wikimedia.org/wikipedia/commons/thumb/e/ef/Stack_Overflow_icon.svg/1200px-Stack_Overflow_icon.svg.png"
            alt=""
          />
        </div>
        <ButtonBox />
        <Inputbox />
        <div className="flex justify-center items-center mt-10">
          Don’t have an account?
          <a href="/signup" className="ml-3 text-sky-600">
            Sign up
          </a>
        </div>
      </div>
    </div>
  );
};
export default LoginPage;
