/** @type {import('tailwindcss').Config} */
// eslint-disable-next-line no-undef
module.exports = {
  content: ["./src/**/*.{js,jsx,ts,tsx}"],
  theme: {
    screens: {
      sm: "640px",
      // => @media (min-width: 640px) { ... }

      md: "980px",
      // => @media (min-width: 980px) { ... }
    },
    extend: {
      spacing: {
        "header-height": "var(--h-header)",
      },
      fontSize: {
        "title-size": "var(--fs-title)",
      },
    },
  },
  plugins: [],
};
