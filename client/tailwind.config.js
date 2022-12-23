/** @type {import('tailwindcss').Config} */
// eslint-disable-next-line no-undef
module.exports = {
  content: ["./src/**/*.{js,jsx,ts,tsx}"],
  theme: {
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
