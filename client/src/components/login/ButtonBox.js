const ButtonBox = () => {
  return (
    <div className="flex flex-col gap-2 ">
      <button className="flex justify-center items-center bg-white p-1  rounded-md border-[#dadde0] border hover:bg-[#dadde0] ">
        <svg width="18" height="18" className="mr-1">
          <image
            href="https://upload.wikimedia.org/wikipedia/commons/thumb/5/53/Google_%22G%22_Logo.svg/120px-Google_%22G%22_Logo.svg.png"
            height="18"
            width="18"
          />
        </svg>
        Log in with Google
      </button>
      <button className="flex justify-center items-center bg-[#2f3337] text-white p-1 border-none rounded-md hover:bg-black">
        <svg width="18" height="18" className="mr-1">
          <image
            href="https://user-images.githubusercontent.com/59932098/104577259-8ea22080-5659-11eb-8efe-43e03c3b490f.png"
            height="18"
            width="18"
          />
        </svg>
        Log in with GitHub
      </button>
      <button className="flex justify-center items-center bg-[#385499] text-white p-1 rounded-md hover:bg-[#314a86]">
        <svg width="18" height="18" className="mr-1">
          <image
            href="https://upload.wikimedia.org/wikipedia/commons/thumb/4/4d/F_icon_reversed.svg/516px-F_icon_reversed.svg.png?20170226172710"
            width="18"
            height="18"
          />
        </svg>
        Log in with FaceBook
      </button>
    </div>
  );
};
export default ButtonBox;
