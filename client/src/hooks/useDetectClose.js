import { useEffect, useState, useRef } from "react";
const useDetectClose = (initialState) => {
  const [isOpen, setIsOpen] = useState(initialState);
  const ref = useRef(null);

  /**초기에 버튼을 직접눌렀을떄 작동하는 핸들러 */
  const visibleHandler = () => {
    setIsOpen(!isOpen);
  };

  /**메뉴창or화면다른곳을눌럿을때 작동되는 핸들러 */
  useEffect(() => {
    const onClick = (e) => {
      if (ref.current !== null && !ref.current.contains(e.target)) {
        setIsOpen(!isOpen);
      }
    };

    if (isOpen) {
      window.addEventListener("click", onClick);
    }
    return () => {
      window.removeEventListener("click", onClick);
    };
  }, [isOpen]);

  return [isOpen, ref, visibleHandler];
};
export default useDetectClose;

/**드랍다운메뉴 클릭및 다른곳 클릭시 메뉴를 닫게 만들기 위한 훅 */
