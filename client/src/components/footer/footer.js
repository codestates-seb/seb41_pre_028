const Footer = () => {
  return (
    <div className="flex-col  bg-neutral-800">
      <div className="flex px-4 pt-8 align-baseline">
        <div className="flex mx-5">{}</div>
        <div className="mt-2 mb-10 grow">
          <div className="font-bold text-gray-300">STACK OVERFLOW</div>
          <div className="m-4 p-5 ext-xxs  text-gray-300">
            Site design / logo © 2022 Stack Exchange Inc; user contributions
            licensed under CC BY-SA. rev 2022.10.28.42999
          </div>
        </div>
        <div className="flex mx-2 mt-2">
          <a href="/" className="mx-1  text-gray-300">
            Questions
          </a>
          <a
            href="https://stackoverflow.com/help"
            className="mx-5  text-gray-300"
          >
            Help
          </a>
        </div>
      </div>
    </div>
  );
};

export default Footer;
