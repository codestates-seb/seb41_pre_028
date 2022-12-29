import InputPrimary from "../components/input/inputPrimary";
import { BufferMd5, BufferMr5 } from "../components/buffer/Buffer";
// import Editor from "../components/editor/Editor";
import MDEditor from "@uiw/react-md-editor";

import { Button } from "@mui/material";
import { PageContainer, MainContainer } from "../components/StyledContainer";
import { useState } from "react";
import { fetchCreateQuestion } from "../utils/api/api";
import { useNavigate } from "react-router-dom";
import { isCookieExist } from "../utils/cookie";

const CreateQuestionPage = () => {
  const navigate = useNavigate();
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const [tag, setTag] = useState("");

  const onChangeTitle = (e) => {
    e.preventDefault();
    setTitle(e.target.value);
  };

  /** editor */
  // const onChangeContent = (e) => {
  //   e.preventDefault();
  //   setContent(e.target.value);
  // };

  const Editor = () => {
    return (
      <div className="flex flex-col z-0 container">
        {/* 크기를 최대로 조정해야함 */}
        <MDEditor
          className="flex min-w-max"
          data-color-mode="light"
          value={content}
          onChange={setContent}
          preview="edit"
        />
        <BufferMd5 />
        <div> {content}</div>
      </div>
    );
  };

  const onChangeTag = (e) => {
    e.preventDefault();
    setTag(e.target.value);
  };

  const onClickDiscard = () => {
    navigator("/questions");
  };

  const onClickSubmit = async () => {
    console.log("title, content, tag", { title, content, tag });
    console.log("isCookieExist", isCookieExist);
    if (title.length < 10) {
      console.log("Minimum 10 characters.");
    } else if (content.length < 50) {
      console.log("Minimum 50 characters.");
    } else {
      await fetchCreateQuestion({ title, content, tag }).then((questionId) => {
        console.log(questionId);
        navigate(`/questions/${questionId}`);
      });
    }
  };
  return (
    <PageContainer className="bg-[#f1f2f3]">
      <MainContainer>
        <div className="p-2 bg-[#f1f2f3] w-screen">
          <h1 className="p-2 my-8 font-bold text-3xl">Ask a public question</h1>
          <div className="bg-[#EBF4FA] border border-[#A5CFED] p-6 rounded-md">
            <h2 className="mb-3 text-2xl">Writing a good question</h2>
            <p className="text-md">
              You’re ready to{" "}
              <a
                href="https://stackoverflow.com/help/how-to-ask"
                className="text-secondary-400"
              >
                ask
              </a>
              a{" "}
              <a
                href="https://stackoverflow.com/help/on-topic"
                className="text-secondary-400"
              >
                programming-related question
              </a>{" "}
              and this form will help guide you through the process.
            </p>
            <p className="mb-2 text-md">
              Looking to ask a non-programming question? See{" "}
              <a
                href="https://stackexchange.com/sites#technology-traffic"
                className="text-secondary-400"
              >
                the topics here
              </a>{" "}
              to find a relevant site.
            </p>
            <h5 className="mb-2 font-bold">Steps</h5>
            <ul className="mb-0 leading-tight list-disc list-inside">
              <li>Summarize your problem in a one-line title.</li>
              <li>Describe your problem in more detail.</li>
              <li>Describe what you tried and what you expected to happen.</li>
              <li>
                Add “tags” which help surface your question to members of the
                community.
              </li>
              <li>Review your question and post it to the site.</li>
            </ul>
          </div>

          <BufferMd5 />

          {/* title */}
          <div className="mb-8 p-6 bg-white border rounded-md border-soGray-light">
            <h2 className="mb-0 font-bold text-xl">Title</h2>
            <p className="mb-2 text-sm">
              Be specific and imagine you’re asking a question to another
              person.
            </p>
            <InputPrimary
              value={title}
              onChange={onChangeTitle}
              placeholder="e.g. Is there an R function for finding the index of an element in a vector?"
            />
          </div>

          {/* content */}
          <div className="mb-8 p-6 bg-white border rounded-md border-soGray-light">
            <h2 className="mb-0 font-bold text-xl ">
              What are the details of your problem?
            </h2>
            <p className="mb-2 text-sm">
              Introduce the problem and expand on what you put in the title.
              Minimum 20 characters.
            </p>
            <Editor />
          </div>

          {/* tag */}
          <div className="mb-8 p-6 bg-white border rounded-md border-soGray-light">
            <h2 className="mb-0 font-bold text-xl">Tags </h2>
            <p className="mb-2 text-sm">
              Add up to 5 tags to describe what your question is about. Start
              typing to see suggestions.
            </p>
            <InputPrimary
              value={tag}
              onChange={onChangeTag}
              placeholder="e.g. Is there an R function for finding the index of an element in a vector?"
            />
          </div>
          <div className="flex flex-row">
            <Button
              variant="contained"
              sx={{ fontSize: 12 }}
              size="large"
              onClick={onClickSubmit}
            >
              Post your question
            </Button>

            <BufferMr5 />
            <Button
              color="error"
              sx={{ fontSize: 12 }}
              size="large"
              onClick={onClickDiscard}
            >
              Discard draft
            </Button>
          </div>
        </div>
      </MainContainer>
    </PageContainer>
  );
};

export default CreateQuestionPage;
