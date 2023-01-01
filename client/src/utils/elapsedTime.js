export default function elapsedTime(date) {
  const start = new Date(date);
  const end = new Date(); // 현재 날짜

  const diff = (end - start) / 1000;

  const times = [
    { name: "years", milliSeconds: 60 * 60 * 24 * 365 },
    { name: "months", milliSeconds: 60 * 60 * 24 * 30 },
    { name: "days", milliSeconds: 60 * 60 * 24 },
    { name: "hours", milliSeconds: 60 * 60 },
    { name: "mins", milliSeconds: 60 },
  ];

  for (const value of times) {
    const betweenTime = Math.floor(diff / value.milliSeconds);

    if (betweenTime > 0) {
      return `${betweenTime} ${value.name} ago`;
    }
  }
  return "";
}
